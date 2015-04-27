package com.shems.mobile;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.shems.control.Controller;
import com.shems.model.Consumption;


public class MainActivity extends ActionBarActivity {

    Controller controller;

    Spinner spinnerMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Controller
        controller = Controller.getInstance();

        //Initialize TabHost
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("general");
        tabSpec.setContent(R.id.tabGeneral).setIndicator("General");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("objects");
        tabSpec.setContent(R.id.tabObjects).setIndicator("Objects");
        tabHost.addTab(tabSpec);

        //Initialize Spinner Month
        spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.months, R.layout.spinner_item);
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textGoal = (TextView) findViewById(R.id.textGoal);
                TextView textConsumed = (TextView) findViewById(R.id.textConsumed);

                String selectedMonth = ((TextView) view).getText().toString();

                Consumption consumption = controller.getConsumptionByMonth(selectedMonth);
                textGoal.setText(consumption.getGoal());
                textConsumed.setText(consumption.getConsumed());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
