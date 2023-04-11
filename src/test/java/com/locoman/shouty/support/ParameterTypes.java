package com.locoman.shouty.support;

import com.locoman.shouty.Person;
import io.cucumber.java.ParameterType;

public class ParameterTypes {
    @ParameterType("Lucy|Sean")
    public Person person(String name){
        return new Person(name);
    }
}
