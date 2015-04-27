package com.shems.model;

/**
 * Created by nelson on 27/04/15.
 */
public class Consumption {

    private String month;
    private String goal;
    private String consumed;

    public Consumption(String month, String goal, String consumed) {
        this.month = month;
        this.goal = goal;
        this.consumed = consumed;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getConsumed() {
        return consumed;
    }

    public void setConsumed(String consumed) {
        this.consumed = consumed;
    }
}
