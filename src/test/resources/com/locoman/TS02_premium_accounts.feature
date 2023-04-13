Feature: Premium accounts

  @todo
  Scenario: BUG #2789
    Given "Sean" has bought 30 credits
    When "Sean" shouts "buy, buy buy!"
    Then "Sean" should have 25 credits
    #  Expected: 25
    #      Got: 15