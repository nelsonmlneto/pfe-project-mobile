package com.shems.mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.shems.control.Controller;
import com.shems.model.Consumption;
import com.shems.model.HouseObject;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    Controller controller;

    Spinner spinnerMonth;
    ImageButton buttonGeneralChart;
    ImageButton buttonObjectsChart;
    Spinner spinnerRoom;

    List<HouseObject> objects;
    ListView listViewObjects;

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

        //Initialize General and Objects Chart Button
        buttonGeneralChart = (ImageButton) findViewById(R.id.buttonGeneralChart);
        buttonGeneralChart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GeneralChartActivity.class));
            }
        });
        buttonObjectsChart = (ImageButton) findViewById(R.id.buttonObjectsChart);
        buttonObjectsChart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ObjectsChartActivity.class));
            }
        });

        //Initialize Object List
        this.objects = controller.getObjectList();
        listViewObjects = (ListView) findViewById(R.id.listViewObj);
        populateHouseObjectList();
        listViewObjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HouseObject listItem = objects.get(position);
                Toast.makeText(getApplicationContext(), "CLICOOU : " + listItem.getId(),
                        Toast.LENGTH_LONG).show();
            }
        });

        //Initialize Spinner Room
        spinnerRoom = (Spinner) findViewById(R.id.spinnerRoom);
        monthAdapter = ArrayAdapter.createFromResource(this, R.array.rooms, R.layout.spinner_item);
        spinnerRoom.setAdapter(monthAdapter);
        spinnerRoom.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRoom = ((TextView) view).getText().toString();
                objects = controller.getObjectListByRoom(selectedRoom);
                populateHouseObjectList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    private void populateHouseObjectList(){
        HouseObjectAdapter adapter = new HouseObjectAdapter(this.objects);
        listViewObjects.setAdapter(adapter);
    }

    private class HouseObjectAdapter extends ArrayAdapter<HouseObject>{

        public HouseObjectAdapter(List<HouseObject> objects){
            super (MainActivity.this, R.layout.object_list_item, objects);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.object_list_item, null);

            HouseObject current = objects.get(position);
            TextView title = (TextView) view.findViewById(R.id.textObjName);
            ImageView status = (ImageView) view.findViewById(R.id.imageStatus);

            if(current.getTurned().equals("on")){
                status.setBackgroundColor(Color.parseColor("#70b500"));
            }

            title.setText(current.getTitle());

            return view;
        }

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
