Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given the app is launched
    When the user enters credentials from the file
    And the user selects date of birth from the file
    And the user taps on the login button
    Then the user should see the home screen with "All", "Hotels", and "Holidays" tabs