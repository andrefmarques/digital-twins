Feature: Create a virtual persona to test the bus network
  Scenario: Creating vitual persona
    Given Belmiro needs to create virtual personas to test the bus line.
    When He accesses the digital persona creation section and he creates a virtual person with name, age, background learning skills and request that will be used in a virtual bus line test.
    Then The app must generate the digital person and confirm its creation.