package com.example.juegosapp.LO;

public class LOCell {
    private boolean isOn;
    private boolean isClicked;
    private boolean isHint;

    public LOCell(boolean on) {
        this.isOn = on;
    }

    public LOCell(){
        this.isOn = false;
        this.isClicked = false;
        this.isHint = false;
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

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isHint() {
        return isHint;
    }

    public void setHint(boolean hint) {
        isHint = hint;
    }
}
