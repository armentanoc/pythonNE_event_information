package com.example.semoc_bes_droid_builders;

public class Item {

    String name;
    String eventType;
    String date;
    String local;
    int image;

    //Constructor
    public Item(String name, String eventType, String local, String date, int image) {
        this.eventType = eventType;
        this.date = date;
        this.name = name;
        this.local = local;
        this.image = image;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
