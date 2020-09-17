Feature: Find out the best bus route.

  Scenario: Get the best bus route

  Given Paula, is at the Arrabida bus station waiting for a bus and wants to travel to Aliados.

  When She checks the buses that stop by her station.

  Then The app must show her that the bus that has the shortest route to her destination is bus number 2.