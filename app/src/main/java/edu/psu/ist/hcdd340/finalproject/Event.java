package edu.psu.ist.hcdd340.finalproject;

// Event.java
public class Event {
    private String title;
    private String time;
    private String place;
    private String description;

    public Event(String title, String time, String place, String description) {
        this.title = title;
        this.time = time;
        this.place = place;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }
}
