package com.shems.mobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.shems.control.Controller;
import com.shems.model.HouseObject;


public class RegisterObjectActivity extends ActionBarActivity {

    Controller controller;

    EditText editSerial, editName, editDescription;
    Spinner spinnerRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_object);

        //Initialize Controller
        controller = Controller.getInstance();

        editSerial = (EditText) findViewById(R.id.editSerial);
        editName = (EditText) findViewById(R.id.editName);
        editDescription = (EditText) findViewById(R.id.editDescription);

        //Initialize Spinner Room
        spinnerRoom = (Spinner) findViewById(R.id.spinnerAddRoom);
        ArrayAdapter roomAdapter = ArrayAdapter.createFromResource(this, R.array.add_rooms, R.layout.spinner_item);
        spinnerRoom.setAdapter(roomAdapter);

        //Initialize Register Button
        Button register = (Button) findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String serial = editSerial.getText().toString();
                String title = editName.getText().toString();
                String description = editDescription.getText().toString();
                String room = spinnerRoom.getSelectedItem().toString();

                HouseObject newObject = new HouseObject(title,serial,room,description);
                controller.addNewObject(newObject);

                finish();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_object, menu);
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
