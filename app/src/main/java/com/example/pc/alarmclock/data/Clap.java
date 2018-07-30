package com.example.pc.alarmclock.data;

/**
 * Created by dell on 3/8/2018.
 */

public class Clap {
    private String name;
    private String time;

    public Clap(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
