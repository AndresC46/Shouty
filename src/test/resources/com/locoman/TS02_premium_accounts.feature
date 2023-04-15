Feature: Premium accounts
  Rules:
    * Mention the word "buy" and you lose 5 credits.
    * Over Long messages cost 2 credits
  Questions:
  * What about the one where the same message is both over-long and contains the word "buy"
  * What happens if Sean runs out of credits?

  Background:
    Given shout range is 100 metres
    And people are located at:
      | name     | Sean | Lucy |
      | location | 0    | 80   |

  Rule: Mention the word "buy" and you lose 5 credits.

  Scenario: Single Shout contains the word buy 3 times
    Given "Sean" has bought 50 credits
    When "Sean" shouts "buy, buy buy!"
    Then "Sean" should have 45 credits

    Scenario: Shout several messages containing the word “buy”
      Given "Sean" has bought 30 credits
      When "Sean" shouts "I want to buy a car!"
      And "Sean" shouts "Who wants to buy my warm delicious bagels!"
      And "Sean" shouts "Who wants to buy my old car!"
      Then "Lucy" hears the following messages:
        | I want to buy a car!                       |
        | Who wants to buy my warm delicious bagels! |
        | Who wants to buy my old car!               |
      And "Sean" should have 15 credits

  Rule: Over-long messages cost 2 credits

#    Scenario: Sean shouts some over-long messages
#      Given Sean has bought 30 credits
#      When Sean shouts 2 over-long messages
#      Then Lucy hears all Sean's messages
#      And Sean should have 26 credits
