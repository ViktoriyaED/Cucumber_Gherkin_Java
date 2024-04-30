Feature: Elements

  Background:
    Given User opens main page
    And User clicks on the side menu Elements Page

  Scenario: Elements submenus are displayed
    Then Elements Page is opened
    And List of 9 Elements submenus is shown
    And List of Elements submenus contains the following elements:
      | Text Box              |
      | Check Box             |
      | Radio Button          |
      | Web Tables            |
      | Buttons               |
      | Links                 |
      | Broken Links - Images |
      | Upload and Download   |
      | Dynamic Properties    |

  Scenario: Text Box form is displayed
    When User clicks on the Text Box side menu
    Then Text Box header is displayed
    And Full Name input field is displayed
    And Email input field is displayed
    And Current Address input field is displayed
    And Permanent Address input field is displayed

  Scenario: User fills out form and submits successfully
    When User clicks on the Text Box side menu
    And User fill out the Full Name field with "John Doe"
    And User fill out the Email field with "john@example.com"
    And User fill out the Current Address field with "123 Main St"
    And User fill out the Permanent Address field with "456 Oak Ave"
    And User clicks on the Submit button
#    Then User should see a confirmation message containing "Name:John Doe"
#    And User should see a confirmation message containing "Email:john@example.com"
#    And User should see a confirmation message containing "Current Address:123 Main St"
#    And User should see a confirmation message containing "Permanent Address:456 Oak Ave"

