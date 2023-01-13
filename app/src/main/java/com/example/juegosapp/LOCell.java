package com.example.juegosapp;

public class LOCell {
    private boolean isOn;

    public LOCell(boolean on) {
        this.isOn = on;
    }

    public LOCell(){
        this.isOn = false;
    }

    public void light(){
        if(isOn){
            setOn(false);
        } else {
            setOn(true);
        }
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
    }
}
