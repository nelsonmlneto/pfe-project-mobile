package com.shems.control;

import com.shems.model.Consumption;
import com.shems.model.HouseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by nelson on 27/04/15.
 */
public class Controller {

    private static Controller _instance;

    private String currentMonth;
    private HashMap<String,Consumption> consumptionList;
    private HashMap<Integer, HouseObject> objectList;

    public Consumption getConsumptionByMonth(String month){
        this.currentMonth = month;
        return consumptionList.get(month);
    }

    public String getCurrentMonth(){
        return this.currentMonth;
    }

    public List<HouseObject> getObjectList(){
        return new ArrayList<HouseObject>(objectList.values());
    }

    public static Controller getInstance(){
        if(_instance == null)
            _instance = new Controller();
        return _instance;
    }

    private Controller (){
        initializeConsumptionList();
        initializeObjectList();
    }

    private void initializeConsumptionList(){
        consumptionList = new HashMap<>();
        consumptionList.put("January", new Consumption("January", "100", "77"));
        consumptionList.put("February", new Consumption("February", "100", "89"));
        consumptionList.put("March", new Consumption("March", "100", "84"));
    }

    private void initializeObjectList(){
        objectList = new HashMap<>();
        objectList.put(1, new HouseObject("Air Conditioner",1,"1","","off"));
        objectList.put(2, new HouseObject("Clothes Iron",2,"2","","on"));
        objectList.put(3, new HouseObject("Hair Straightener",3,"3","","on"));
        objectList.put(4, new HouseObject("Couple Bedroom Light",4,"4","","off"));
        objectList.put(5, new HouseObject("Oven",5,"5","","off"));
        objectList.put(6, new HouseObject("Kitchen Light",6,"6","","on"));
        objectList.put(7, new HouseObject("TV",7,"7","","off"));
        objectList.put(8, new HouseObject("Garage Light",8,"8","","on"));
        objectList.put(9, new HouseObject("Shaving Machine",9,"9","","off"));
    }
}
