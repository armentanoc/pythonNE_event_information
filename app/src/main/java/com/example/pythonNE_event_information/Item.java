package com.example.pythonNE_event_information;

import android.os.Build;

//import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Item {

    String name;
    String type;
    String date;
    //LocalDate date;
    String local;
    int image;

    //Constructor

    public Item(String name, String type, String local, String date, int image) {
        this.type = type;
        this.date = date;
        this.name = name;
        this.local = local;
        this.image = image;
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Item(String name, String type, String local, String date, int image) {
        this.type = type;
        LocalDate localDate = LocalDate.parse(date);
        this.date = localDate;
        this.name = name;
        this.local = local;
        this.image = image;
    }*/

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }
    */

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
