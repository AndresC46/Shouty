package com.locoman.shouty.support;

public class WhereAbouts {
    public String name;
    public Integer location;

    public WhereAbouts(String name, Integer location) {
        this.name = name;
        this.location = location;
    }

    // Setter and Getters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getLocation() { return location; }
    public void setLocation(Integer location) { this.location = location; }
}
