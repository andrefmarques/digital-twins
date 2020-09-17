Feature: Notifications about bus passing.

  Scenario:

  Given Paula, is at the Aliados bus station waiting for a bus.

  When The bus stops by the station before the station she is.

  Then The app must send a notification to Paula that her bus is coming in 4 minutes.