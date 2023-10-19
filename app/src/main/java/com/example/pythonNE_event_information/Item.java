package com.example.pythonNE_event_information;

//import androidx.annotation.RequiresApi;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

    String title, type, language, start, duration, strRoom, url, strAbstract, personName, personBiography;
    int placeholder;

    Date date;

    public Item(String title, String type, String language, String start, String duration, String strRoom, String url, String strAbstract, String personName, String personBiography, Date date) {
        this.title = title;
        this.type = type;
        this.language = language;
        this.start = start;
        this.duration = duration;
        this.strRoom = strRoom;
        this.url = url;
        this.strAbstract = strAbstract;
        this.personName = personName;
        this.personBiography = personBiography;
        this.date = date;
        this.placeholder = placeholder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStrRoom() {
        return strRoom;
    }

    public void setStrRoom(String strRoom) {
        this.strRoom = strRoom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStrAbstract() {
        return strAbstract;
    }

    public void setStrAbstract(String strAbstract) {
        this.strAbstract = strAbstract;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonBiography() {
        return personBiography;
    }

    public void setPersonBiography(String personBiography) {
        this.personBiography = personBiography;
    }

    public String getDateStr() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

}
