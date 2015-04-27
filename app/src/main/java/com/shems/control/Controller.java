package com.shems.control;

import com.shems.model.Consumption;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by nelson on 27/04/15.
 */
public class Controller {

    private static Controller _instance;

    private HashMap<String,Consumption> consumptionList;

    public Consumption getConsumptionByMonth(String month){
        return consumptionList.get(month);
    }

    public static Controller getInstance(){
        if(_instance == null)
            _instance = new Controller();
        return _instance;
    }

    private Controller (){
        initializateConsumptionList();
    }

    private void initializateConsumptionList(){
        consumptionList = new HashMap<String, Consumption>();
        consumptionList.put("January", new Consumption("January", "100", "77"));
        consumptionList.put("February", new Consumption("February", "100", "89"));
        consumptionList.put("March", new Consumption("March", "100", "84"));
    }

}
