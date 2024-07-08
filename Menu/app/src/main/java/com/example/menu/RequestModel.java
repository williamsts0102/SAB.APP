package com.example.menu;

public class RequestModel {
    private int pintIdPersonal;

    public RequestModel(int pintIdPersonal) {
        this.pintIdPersonal = pintIdPersonal;
    }

    // Getter y Setter
    public int getPintIdPersonal() {
        return pintIdPersonal;
    }

    public void setPintIdPersonal(int pintIdPersonal) {
        this.pintIdPersonal = pintIdPersonal;
    }
}
