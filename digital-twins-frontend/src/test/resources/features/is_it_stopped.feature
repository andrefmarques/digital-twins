Feature: Check if a bus is stopped.

  Scenario: Is bus 3 stopped?

    Given bus 3's average speed is 0.

    When  Belmiro checks if it is moving.

    Then  the system must return it is stopped.
