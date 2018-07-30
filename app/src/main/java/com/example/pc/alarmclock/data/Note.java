package com.example.pc.alarmclock.data;

public class Note {
    private int id;
    private int day;
    private int mouth;
    private int year;
    private String content;

    public Note() {
    }

    public Note(int day, int mouth, int year, String content) {
        this.day = day;
        this.mouth = mouth;
        this.year = year;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMouth() {
        return mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
