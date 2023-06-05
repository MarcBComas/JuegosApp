package com.example.juegosapp.data;

public class Scores {
    private int id;
    private String name;
    private String time;
    private String other;

    public Scores() {}

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
