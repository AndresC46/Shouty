package com.locoman.shouty.unittests;

import com.locoman.shouty.ShoutyNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TS01_ShoutNetworkTests {
    //boolean debug = false;
    ShoutyNetwork network;

    @BeforeEach
    public void setup(){
        System.out.println("Setting up test");
        network = new ShoutyNetwork();
    }

    @Test
    void shoutyNetwork_setShoutRange_verifyRange() {
        network.setShoutRange(75);
        assertEquals(75, network.getShoutRange());
    }

    @Test
    void shoutyNetwork_setShoutInRange_verifyIfInRange(){
        network.setShoutRange(100);
        assertTrue(network.getInRange(50));
    }

    @Test
    void shoutyNetwork_setShoutOutRange_verifyIfOutRange(){
        network.setShoutRange(75);
        assertFalse(network.getInRange(100));
    }

    @Test
    void shoutyNetwork_addPerson_verifySinglePersonCount(){
        network.AddToNetwork("Albertico", 45);
        assertEquals(1, network.getPeopleCount(), "Unexpected number of People in Network");
    }

    @Test
    void shoutyNetwork_add3People_verifyPersonCount(){
        network.AddToNetwork("Albertico", 45);
        network.AddToNetwork("Anna Gabriel", 45);
        network.AddToNetwork("Caonabo", 45);
        assertEquals(3, network.getPeopleCount(), "Unexpected number of People in Network");
    }

    @Test
    void shoutyNetwork_addPerson_verifyPersonDistance(){
        network.AddToNetwork("Juanita", 56);
        assertEquals(56, network.getPersonDistance("Juanita"), "Unexpected distance in Network");
    }

    @Test
    void shoutyNetwork_addPersonInRange_verifyInRange(){
        network.AddToNetwork("Fefita", 0);
        network.AddToNetwork("Maria", 60);
        network.setShoutRange(75);
        String shout = "Hay Empanadas de res para el almuerzo";
        network.Shout("Fefita", shout);
        assertTrue(network.wasShoutHeardBy("Maria", shout));
    }

    @Test
    void shoutyNetwork_addPersonOutOfRange_verifyOutRange(){
        network.AddToNetwork("Fefita", 0);
        network.AddToNetwork("Caonabo", 160);
        network.setShoutRange(100);
        String shout = "Vamos a ir al mercado a las 3:00 pm";
        network.Shout("Fefita", shout);
        assertFalse(network.wasShoutHeardBy("Caonabo", shout));
    }

    @Test
    void shoutyNetwork_messsageeOver180Chars_verifyNotShouted(){
        network.AddToNetwork("Fefita", 0);
        network.AddToNetwork("Caonabo", 60);
        network.setShoutRange(100);

        // Create large message
        char[] chars = new char[181];
        Arrays.fill(chars, 'x');
        String longMessage = String.valueOf(chars);

        // SHout the message and checck if heard
        network.Shout("Fefita", longMessage);
        assertFalse(network.wasShoutHeardBy("Caonabo", longMessage));
    }

}
