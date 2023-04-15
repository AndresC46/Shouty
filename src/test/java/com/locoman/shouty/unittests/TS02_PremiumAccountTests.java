package com.locoman.shouty.unittests;

import com.locoman.shouty.ShoutyNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TS02_PremiumAccountTests {
    //boolean debug = false;
    ShoutyNetwork network;

    @BeforeEach
    public void setup(){
        network = new ShoutyNetwork();
    }

    @Test
    public void newUser_give30Credits_verify30Credits() {
        network.AddToNetwork("Cesar", 45);
        network.setupCreditsForAccount("Cesar", 30);
        assertEquals(30, network.getUserCredits("Cesar"));
    }

    @Test
    public void shoutCharger_shortMessage_verifyNoCharge(){
        String shortMessage = "Hay bunuellos, de maiz!";
        assertEquals(0, network.shoutCharge(shortMessage));
    }

    @Test
    public void shoutCharger_longMessage_verifyCharge(){
        String longMessage = "Lorem6 ipsum dolor sit, consectetuer adipiscing elit. Bibendum fusce lectus commodo fames. Lobortis vivamus\n" +
                "       leo orci. Faucibus nulla nunc bibendum. Hendrerit quis mus eros. Finibus vel ef190 ficitur phasellus tincidunt.\n" +
                "       Nulla penatibus viverra pulvinar.\n" +
                "\n" +
                "       Cubilia non est. Natoque orci lobortis vulputate facilisi. Dis ultrices curae. Id aenean in a litora. Libero\n" +
                "       aliquet ac to380 rtor";
        assertEquals(2, network.shoutCharge(longMessage));
    }

    @Test
    public void shoutCharger_oneBuyMessage_verifyCharge(){
        String oneBuyMessage = "Hey,  who you can buy bagels for under a buck!";
        assertEquals(5, network.shoutCharge(oneBuyMessage));
    }

    @Test
    public void shoutCharger_theBuyMessage_verifyCharge(){
        String oneBuyMessage = "Time to buy, buy, buy Bachmann Pretzels";
        assertEquals(15, network.shoutCharge(oneBuyMessage));
    }

    @Test
    public void shoutCharger_longMessagetwoBuys_verifyCharge(){
        String longTwoBuyMessages = "Lorem6 ipsum dolor sit, consectetuer adipiscing elit. Bibendum fusce lectus commodo fames. Lobortis vivamus\n" +
                "       leo orci. Faucibus nulla nunc bibendum. Hendrerit quis buy eros. Finibus vel ef190 ficitur phasellus tincidunt.\n" +
                "       Nulla penatibus viverra pulvinar.\n" +
                "\n" +
                "       Cubilia non est. Natoque orci lobortis vulputate facilisi. buy ultrices curae. Id aenean in a litora. Libero\n" +
                "       aliquet ac to380 rtor";
        assertEquals(12, network.shoutCharge(longTwoBuyMessages));
    }




    @Test
    public void userWith30Credits_longShout_affordsShout(){
        String name = "Hugo";
        network.AddToNetwork(name, 45);
        network.setupCreditsForAccount(name, 30);
        String longMessage = "Lorem6 ipsum dolor sit, consectetuer adipiscing elit. Bibendum fusce lectus commodo fames. Lobortis vivamus\n" +
                "       leo orci. Faucibus nulla nunc bibendum. Hendrerit quis mus eros. Finibus vel ef190 ficitur phasellus tincidunt.\n" +
                "       Nulla penatibus viverra pulvinar.\n" +
                "\n" +
                "       Cubilia non est. Natoque orci lobortis vulputate facilisi. Dis ultrices curae. Id aenean in a litora. Libero\n" +
                "       aliquet ac to380 rtor";
        assertTrue(network.enoughCreditsForShout(name, longMessage));
    }

    @Test
    public void userWith10Credits_mutipleBuyMessages_cannotAffordsShout(){
        String name = "Marielle";
        network.AddToNetwork(name, 45);
        network.setupCreditsForAccount(name, 10);
        String threeBuys = "You must buy buy buy Cartablanca Cigars";
        assertFalse(network.enoughCreditsForShout(name, threeBuys));
    }



//    @Test
//    public void userWith30Credits_oneValidMessage_verifyCharge(){
//        network.AddToNetwork("Fefa", 75);
//        network.setupCreditsForAccount("Fefa", 30);
//
//        String shout = "Nos esta llevando el Diablo!";
//        network.Shout("Fefa", shout);
//        assertEquals(30, network.getUserCredits("Fefa") );
//    }



}  // end class
