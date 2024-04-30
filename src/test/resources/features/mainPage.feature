Feature: Main Page

  Scenario: Open Main Page
    When User opens main page
    Then Main Page is opened

  Scenario: Open Elements page
    When User opens main page
    When User clicks on the side menu Elements Page
    Then Elements Page is opened