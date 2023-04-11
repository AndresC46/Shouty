package com.locoman.shouty;

import java.util.ArrayList;
import java.util.List;

public class Person {
    List<String> messages = new ArrayList<>();
    Integer distanceFromShout;
    String lastShoutedMessage;
    String lastHeardMessage;
    String name;


    public Person(String name) {
        distanceFromShout = 0;
        lastShoutedMessage = "";
        lastHeardMessage = "";
        this.name = name;
    }

    public Person(String name, int range) {
        distanceFromShout = range;
        lastShoutedMessage = "";
        this.name = name;
    }

    // Setters and Getter
    public Integer getDistanceFromShout() { return distanceFromShout; }
    public void setDistanceFromShout(Integer distanceFromShout) {this.distanceFromShout = distanceFromShout;}

    public String getLastShoutedMessage() { return lastShoutedMessage;}
    public void setLastShoutedMessage(String lastShoutedMessage) { this.lastShoutedMessage = lastShoutedMessage;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getMessagesHeard() { return messages; }
    public void setResult(List<String> message) { this.messages = messages; }


    public void shout(String message) {
        setLastShoutedMessage(message);
        messages.add(getLastShoutedMessage());
    }


}
