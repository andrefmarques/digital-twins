Feature: Check what is a bus' average speed.

  Scenario: Bus 2's average speed check.

    Given bus 2's expected average speed is 30.

    When  Belmiro checks its average speed.

    Then  the system must return it's 30.
