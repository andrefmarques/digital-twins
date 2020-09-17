Feature: Change a bus route.

  Scenario: Changing bus route

  Given Belmiro needs to change the route of bus number 1 to make a new test.

  When He accesses the already created bus, taps on it and redefines a new route.

  Then The app must change the bus number 1’s route and display the newly updated route.