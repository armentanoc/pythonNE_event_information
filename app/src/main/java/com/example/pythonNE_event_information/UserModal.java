package com.example.pythonNE_event_information;

import java.time.LocalDate;

public class UserModal {

    String title;
    String type;
    String language;
    LocalDate date;
    String start;
    String duration;
    String room;
    String url;
    String strAbstract;
    String personPublicName;
    String personBiography;

    public UserModal(String title, String type, String language, LocalDate date, String start, String duration, String room, String url, String strAbstract, String personPublicName, String personBiography) {
        this.title = title;
        this.type = type;
        this.language = language;
        this.date = date;
        this.start = start;
        this.duration = duration;
        this.room = room;
        this.url = url;
        this.strAbstract = strAbstract;
        this.personPublicName = personPublicName;
        this.personBiography = personBiography;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public String getPersonPublicName() {
        return personPublicName;
    }

    public void setPersonPublicName(String personPublicName) {
        this.personPublicName = personPublicName;
    }

    public String getPersonBiography() {
        return personBiography;
    }

    public void setPersonBiography(String personBiography) {
        this.personBiography = personBiography;
    }
}
