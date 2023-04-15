package com.locoman.shouty;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is Network Class for the Shouty BDD Demo Project
 * from IO Cucumber School
 */
public class ShoutyNetwork {
    final static int MAX_MESSAGE = 180;
    final static int SHOUT_RANGE = 100;
    public static final Pattern BUY_PATTERN = Pattern.compile("buy", Pattern.CASE_INSENSITIVE);
    int shoutRange = SHOUT_RANGE;
    Boolean inRange = true;
    HashMap<String, Person> people = new HashMap<>();

    /**
     * Constructs Shouty Network
     */
    public ShoutyNetwork(){
    }

    //
    //   Setters and Getters for Class
    //
    public int getShoutRange() { return this.shoutRange; }
    public void setShoutRange(int shoutRange) { this.shoutRange = shoutRange; }

    /**
     * Return Person's current credit amount
     * @param name of Shouty account user
     * @return  current credit amount
     */
    public int getUserCredits(String name) {
        return people.get(name).getCredits();
    }

    /**
     * Get number of People in Network
     * @return number of people in network
     */
    public int getPeopleCount() {
        return people.size();
    }

    /**
     * Determine if Shout listener is in range of Shouter
     * @param rangeDistance Distance between Shouter and Listener
     * @return true if in range, false otherwise
     */
    public Boolean getInRange(int rangeDistance) {
        this.inRange = (shoutRange >= rangeDistance);
        return inRange;
    }

    /**
     * Add a new Person to a Shouty Network with default range
     * @param name Name of person
     */
    public void AddToNetwork(String name){
        people.put(name, new Person(name.toLowerCase(Locale.ROOT), MAX_MESSAGE));
    }

    /**
     * Add a new Person to a Shouty Network
     * @param name      Name of person
     * @param distance  Distance form Shout
     */
    public void AddToNetwork(String name, int distance){
        people.put(name, new Person(name.toLowerCase(Locale.ROOT), distance));
    }

    /**
     * A Shout is sent, checked for balance and
     * associated with in range hearers
     * @param name of the shouter
     * @param message shouted
     */
    public void Shout(String name, String message) {
        if (enoughCreditsForShout(name, message)) {
            people.forEach((key, value) -> {
                if (wasShoutHeardBy(key, message)) {
                    people.put(key, people.get(key)).shout(message);
                }
            });
        }
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
        return ( isWithinRange(name) && isMessageWithValidSize(message) );
    }


    /**
     * Verifies that a  message  is that Max message size
     * @param message shoyt string
     * @return true if message length is within limits, false otherwise
     */
    private boolean isMessageWithValidSize(String message) {
        return message.length() <= MAX_MESSAGE;
    }

    /**
     * Check if user is within shouting range
     * @param name of user
     * @return true if within range, false otherwise
     */
    private boolean isWithinRange(String name) {
        return ( getShoutRange() >= getPersonDistance(name) );
    }


    /**
     * Return list of Shouts heard by Person
     * @param name of person
     * @return list of SHouts heard
     */
    public List<List<String>> getMessagesHeard(String name) {
        List<List<String>> actualMessages = new ArrayList<>();
        List<String> heard = people.get(name).getMessagesHeard();
        for(String message : heard){
            actualMessages.add(Collections.singletonList(message));
        }
        return actualMessages;
    }


    /**
     * Setup Person with a credit amount
     * @param name of Shouty account user
     * @param creditAmount amount of credit to give user
     */
    public void setupCreditsForAccount(String name, int creditAmount) {
        people.get(name).setCredits(creditAmount);
    }


    /**
     * Check if User has enough credit to cover shout and deduct if amount covers
     * @param name of user
     * @param message shout
     * @return true if user has enough balance and charge is successful, false otherwise
     */
    public boolean enoughCreditsForShout(String name, String message) {
        boolean enoughCredits = false;
        int chargeAmt = shoutCharge(message);
        int creditAmt = people.get(name).getCredits();
         if(creditAmt >= chargeAmt){
             people.get(name).setCredits(creditAmt - chargeAmt);  // Update balance with charges
             enoughCredits = true;
         }
        return enoughCredits;
    }


    /**
     * Calculate Shout Charge according to these rules:
     *    Check if buy message is in Shout, Charge 5 credits once if word 'buy' is used
     *    If word 'buy' is repeated in message charge once
     *    Check if message is within allowed size, Charge 2 credits to send
     *
     * @param message Shouted message
     * @return charge for shout
     */
    public int shoutCharge(String message ) {
        int charge = 0;

        if (!isMessageWithValidSize(message)) {
            charge = 2;
        }

        Matcher matcher = BUY_PATTERN.matcher(message);
        // If we use a while here it will charge each time the word buy is mentioned
        if(matcher.find()) {
            charge += 5;
        }

        return charge;
    }

} // end class
