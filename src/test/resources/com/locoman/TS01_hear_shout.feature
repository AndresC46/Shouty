Feature: Hear Shout
  As a Shouty user I want to hear Shouts from another user
  as long as they are close enough

  To do:
    - only shout to people within a certain distance

  Scenario: Shout between in range users can be heard
    Given shout range is 100 metres
    And people are located at:
      | name     | Sean | Lucy |
      | location | 0    | 50   |
    When "Sean" shouts "free bagels at Sean's"
    Then "Lucy" should hear message

  Scenario: Listener hears while standing one meter away
    Given shout range is 100 metres
    And a person named "Miguel" is at 0 metres
    And a person named "Amanda" is at 1 metre
    Then "Amanda" should hear message

  Scenario: Shout for out of range use is not heard
    Given shout range is 100 metres
    And people are located at:
      | name     | Anacaona | Caonabo |
      | location | 0        | 150     |
    When "Anacaona" shouts "hay empanadas en la cocina"
    Then "Caonabo" should not hear message

    @focus
    Scenario: Multiple Shouts
      Given shout range is 100 metres
      And people are located at:
        | name     | Fefita | Marisol |
        | location | 0      | 75      |
      When "Fefita" shouts "habra moro este Sabado!"
      And "Fefita" shouts "no traigan al Tigre de Milciades!"
      Then "Marisol" hears the following messages:
        | habra moro este Sabado!           |
        | no traigan al Tigre de Milciades! |

  Scenario: Long Shouts can be heard
    Given shout range is 100 metres
    And people are located at:
      | name     | Fefita | Marisol |
      | location | 0      | 75      |
    When "Fefita" shouts
     """
       Lorem6 ipsum dolor sit, consectetuer adipiscing elit.
       Bibendum fusce lectus commodo fames.
       Lobortis vivamus leo orci. Faucibus nulla nunc bibendum.
       Hendrerit quis mus eros.
     """
    Then "Marisol" should hear message

  Scenario: Over the limit Shout for is not heard
    Given shout range is 100 metres
    And people are located at:
      | name     | Fefita | Marisol |
      | location | 0      | 75      |
    When "Fefita" shouts
     """
       Lorem6 ipsum dolor sit, consectetuer adipiscing elit. Bibendum fusce lectus commodo fames. Lobortis vivamus
       leo orci. Faucibus nulla nunc bibendum. Hendrerit quis mus eros. Finibus vel ef190 ficitur phasellus tincidunt.
       Nulla penatibus viverra pulvinar.

       Cubilia non est. Natoque orci lobortis vulputate facilisi. Dis ultrices curae. Id aenean in a litora. Libero
       aliquet ac to380 rtor
     """
    Then "Marisol" should not hear message

  Scenario: User within Shout range hears message, user outside does not
    Given shout range is 100 metres
    And people are located at:
      | name     | Fefita | Marisol | Caonabo |
      | location | 0      | 75      |150      |
    When "Fefita" shouts
    """
    Por favor no traigas a Caonabo, ese tipo es un Tigre!
    """
    Then "Marisol" should hear message
    Then "Caonabo" should not hear message