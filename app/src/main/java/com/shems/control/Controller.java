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
    private HouseObject currentObject;
    private HashMap<String,Consumption> consumptionList;
    private HashMap<Integer, HouseObject> objectList;
    private ArrayList<String> rooms;

    public Consumption getConsumptionByMonth(String month){
        this.currentMonth = month;
        return consumptionList.get(month);
    }

    public String getCurrentMonth(){
        return this.currentMonth;
    }

    public List<HouseObject> getObjectList(){
        return new ArrayList<>(objectList.values());
    }

    public List<HouseObject> getObjectListByRoom(String room){
        List<HouseObject> selected;

        if(room.equals("All")){
            selected = getObjectList();
        }else{
            selected = new ArrayList<>();
            for(HouseObject obj : getObjectList()){
                if(obj.getRoom().equals(room))
                    selected.add(obj);
            }
        }
        return selected;
    }

    public HouseObject getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(HouseObject currentObject) {
        this.currentObject = currentObject;
    }

    public static Controller getInstance(){
        if(_instance == null)
            _instance = new Controller();
        return _instance;
    }

    private Controller (){
        initializeConsumptionList();
        initializeObjectList();
        initializeRoomList();
    }

    private void initializeConsumptionList(){
        consumptionList = new HashMap<>();
        consumptionList.put("January", new Consumption("January", "100", "77"));
        consumptionList.put("February", new Consumption("February", "100", "89"));
        consumptionList.put("March", new Consumption("March", "100", "84"));
    }

    private void initializeObjectList(){
        objectList = new HashMap<>();
        objectList.put(1, new HouseObject("Air Conditioner",1,"1","off","Living Room","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        objectList.put(2, new HouseObject("Clothes Iron",2,"2","on","Couple Bedroom","Quisque sed ornare augue, non imperdiet quam. Donec sollicitudin mauris fringilla aliquet congue. Curabitur ante ipsum, pretium in luctus id, tempor in quam"));
        objectList.put(3, new HouseObject("Hair Straightener",3,"3","on","Bathroom","Curabitur fringilla dolor eget velit viverra, nec scelerisque nunc iaculis. Quisque sed ultrices lacus, vel maximus lacus. Nulla pulvinar suscipit faucibus."));
        objectList.put(4, new HouseObject("Couple Bedroom Light",4,"4","off","Couple Bedroom","Suspendisse quis erat leo. Vestibulum eu rutrum nisl. Cras nec nulla ipsum. Pellentesque nulla metus, sagittis nec diam eget, tristique facilisis nunc."));
        objectList.put(5, new HouseObject("Oven",5,"5","off","Kitchen","Duis diam tortor, pellentesque ut pretium ac, hendrerit vehicula felis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas"));
        objectList.put(6, new HouseObject("Kitchen Light",6,"6","on","Kitchen","Fusce sed scelerisque metus, a sodales enim. Ut in risus sit amet justo efficitur ultricies. Nulla sit amet elit sed odio pretium imperdiet. Aenean nulla elit."));
        objectList.put(7, new HouseObject("TV",7,"7","off","TV Room","Praesent finibus laoreet tellus non eleifend. Sed dictum quis quam vitae venenatis. Mauris rutrum lectus ut sapien congue molestie. Nunc ultricies efficitur nibh placerat rhoncus."));
        objectList.put(8, new HouseObject("Garage Light",8,"8","on","Garage","Donec nec elit at est pretium scelerisque eget a dui. Integer a tempus justo, ac condimentum mauris. Duis tincidunt blandit ex sed fringilla. Ut placerat dolor quis est elementum volutpat."));
        objectList.put(9, new HouseObject("Shaving Machine",9,"9","off","Bathroom","Vivamus ut luctus sapien. Quisque finibus justo non mauris condimentum mattis sit amet vitae purus. Integer blandit fermentum orci. Nullam lectus nibh."));
    }

    private void initializeRoomList(){
        rooms = new ArrayList<>();
        rooms.add("All");
        rooms.add("Kitchen");
        rooms.add("Living Room");
        rooms.add("TV Room");
        rooms.add("Couple Bedroom");
        rooms.add("Kids Bedroom");
        rooms.add("Garage");
        rooms.add("Bathroom");
    }
}
