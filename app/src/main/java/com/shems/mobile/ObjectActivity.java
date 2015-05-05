package com.shems.mobile;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.shems.control.Controller;
import com.shems.mobile.R;
import com.shems.mobile.SingleObjectChartActivity;
import com.shems.model.HouseObject;

public class ObjectActivity extends ActionBarActivity {

    Controller controller;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        //Initialize Controller
        controller = Controller.getInstance();

        final HouseObject currentObject = controller.getCurrentObject();

        TextView objectName = (TextView) findViewById(R.id.textObjName);
        objectName.setText(currentObject.getTitle());
        TextView objectDescription = (TextView) findViewById(R.id.textObjDescription);
        objectDescription.setText(currentObject.getDescription());
        Switch switchOnOff = (Switch) findViewById(R.id.switchOnOff);
        if(currentObject.getTurned().equals("on")){
            switchOnOff.setChecked(true);
        }

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    currentObject.setTurned("on");
                }else{
                    currentObject.setTurned("off");
                }
            }
        });

        ImageButton chartButton = (ImageButton) findViewById(R.id.buttonObjChart);
        chartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SingleObjectChartActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_object, menu);
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
