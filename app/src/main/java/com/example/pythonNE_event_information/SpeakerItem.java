package com.example.pythonNE_event_information;

public class SpeakerItem {

    private String name;
    private String bio;

    public SpeakerItem(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }
}
