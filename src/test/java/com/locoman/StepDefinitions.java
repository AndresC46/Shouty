package com.locoman;

import com.locoman.shouty.ShoutyNetwork;
import com.locoman.shouty.support.WhereAbouts;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    private ShoutyNetwork network;
    private static final int DEFAULT_RANGE = 100;
    String lastShout = "";

    @Before
    public void createNetwork() {
        network = new ShoutyNetwork();
        network.setShoutRange(DEFAULT_RANGE);
        lastShout = "";
    }

    @DataTableType
    public WhereAbouts defineWhereAbouts(Map<String, String> entry) {
        return new WhereAbouts(entry.get("name"), Integer.parseInt(entry.get("location")));
    }

    @Given("shout range is {int} metres")
    public void shoutRangeIsMetres(int shoutingRange) {
        network.setShoutRange(shoutingRange);
    }

    @And("a person named {string} is at {int} metre(s)")
    public void aPersonNamedIsAtMetres(String name, int distance) {
        network.AddToNetwork(name, distance);
    }

    @And("people are located at:")
    public void people_are_located_at(@Transpose List<WhereAbouts> whereAbouts) {
        for (WhereAbouts whereAbout : whereAbouts) {
            network.AddToNetwork(whereAbout.getName(), whereAbout.getLocation());
        }
    }

    @Given("{string} has bought {int} credits")
    public void seanHasBoughtCredits(String name, int credits) {
        network.AddToNetwork(name);
        network.setupCreditsForAccount(name, credits);
    }

    @When("{string} shouts {string}")
    public void shouts(String name, String message) {
        lastShout = message;
        network.Shout(name, message);
    }

    @When("{string} shouts")
    public void shoutsDocString(String name, String docMessage) {
        lastShout = docMessage;
        network.Shout(name, docMessage);
    }

    @Then("{string} should hear message")
    public void shouldHearSeanSMessage(String name) {
        assertTrue(network.wasShoutHeardBy(name, lastShout));
    }

    @Then("{string} should not hear message")
    public void shouldNotHearMessage(String name) {
        assertFalse(network.wasShoutHeardBy(name, lastShout));
    }

    @Then("{string} hears the following messages:")
    public void hears_the_following_messages(String name, DataTable expectedMessages) {
        expectedMessages.diff(DataTable.create(network.getMessagesHeard(name)));
    }


    @Then("{string} should have {int} credits")
    public void shouldHaveCredits(String name, int expectedCreditBalance) {
            assertEquals(expectedCreditBalance, network.getUserCredits(name));
    }
}
