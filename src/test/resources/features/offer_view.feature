Feature: Offer view

  Scenario: User navigates through the "All" tab, "Holidays" tab, and "Hotels" tab
    Given the user is logged in
    When the user navigates to the "All" tab
    Then offers are displayed
    When the user navigates to the "Holidays" tab
    Then offers are displayed
    When the user navigates to the "Hotels" tab
    Then offers are displayed