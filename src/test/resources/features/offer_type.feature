Feature: Offer Type

  Scenario: "All" tab contains both holiday and hotel offers
    Given the user is logged in
    When the user navigates to the "All" tab
    Then there should be at least one "Holiday" offer and one "Hotel" offer on the "All" tab


