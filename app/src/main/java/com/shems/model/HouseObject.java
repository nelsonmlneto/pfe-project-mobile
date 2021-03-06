package com.shems.model;

/**
 * Created by nelson on 28/04/15.
 */
public class HouseObject {

    private String title;
    private Integer id;
    private String serial;
    private String description;
    private String turned;
    private String room;

    public HouseObject(String title, String serial, String room, String description) {
        this.title = title;
        this.serial = serial;
        this.turned = "off";
        this.room = room;
        this.description = description;
    }


    public HouseObject(String title, Integer id, String serial, String turned, String room, String description) {
        this.title = title;
        this.id = id;
        this.serial = serial;
        this.turned = turned;
        this.room = room;
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTurned() {
        return turned;
    }

    public void setTurned(String turned) {
        this.turned = turned;
    }
}
