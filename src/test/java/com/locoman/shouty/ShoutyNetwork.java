package com.locoman.shouty;

import java.util.*;

public class ShoutyNetwork {
    final static int MAX_MESSAGE = 180;
    int shoutRange = 100;
    Boolean inRange = true;
    HashMap<String, Person> people = new HashMap<>();

    /**
     * Constructs Shouty Network
     */
    public ShoutyNetwork(){
    }

    // Setters and Getters
    public int getShoutRange() { return this.shoutRange; }
    public void setShoutRange(int shoutRange) { this.shoutRange = shoutRange; }

    /**
     * Determine if Shout listener is in range of Shouter
     * @param rangeDistance Distance between Shouter and Listener
     * @return true if in range, false otherwise
     */
    public Boolean getInRange(int rangeDistance) {
        this.inRange = (shoutRange >= rangeDistance);
        return inRange;
    }
    public void setInRange(Boolean inRange) { this.inRange = inRange; }

    /**
     * Add a new Person to a Shouty Network
     * @param name      Name of person
     * @param distance  Distance form Shout
     */
    public void AddToNetwork(String name, int distance){
        people.put(name, new Person(name.toLowerCase(Locale.ROOT), distance));
    }

    /**
     * A Shout is sent
     * @param name of the shouter
     * @param message shouted
     */
    public void Shout(String name, String message) {
        people.forEach( (key, value) -> {
            if(wasShoutHeardBy(key, message)){
                people.put(key, people.get(key)).shout(message);
            }
        });
    }

    /**
     * Get number of People in Network
     * @return number of people in network
     */
    public int getPeopleCount() {
        return people.size();
    }

    /**
     * Get Person's distance from Shout
     * @param name of Person
     * @return distance
     */
    public int getPersonDistance(String name) {
        return people.get(name).getDistanceFromShout();
    }

    /**
     * Verifies if a Shout can be heard by person
     * @param name of Person
     * @return true if shout is valid, false otherwise
     */
    public boolean wasShoutHeardBy(String name, String message) {
        return (isWithinRange(name) && isMessageWithValidSize(message));
    }

    /**
     * Verifies that a  message  is that Max message size
     * @param message shoyt string
     * @return true if message length is within limits, false otherwise
     */
    private boolean isMessageWithValidSize(String message) {
        return message.length() <= MAX_MESSAGE;
    }

    private boolean isWithinRange(String name) {
        return ( getShoutRange() >= getPersonDistance(name) );
    }





    /**
     * Return list of Shouts heard by Person
     * @param name person
     * @returnList of Shouts heard
     */
    public List<List<String>> getMessagesHeard(String name) {
        List<List<String>> actualMessages = new ArrayList<List<String>>();
        List<String> heard = people.get(name).getMessagesHeard();
        for(String message : heard){
            actualMessages.add(Collections.singletonList(message));
        }
        return actualMessages;
    }



}
