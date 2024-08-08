# TendableTest

Feature: Tendable website testing

  Scenario: Confirm accessibility of top-level menus
    Given I open the Tendable website
    Then I should see the top-level menu items

  Scenario: Verify "Request a Demo" button presence and activity
    Given I open the Tendable website
    Then I should see the "Request a Demo" button active on each top-level menu page

  Scenario: Contact Us form submission error
    Given I open the Tendable website
    And I navigate to the Contact Us section
    And I choose "Marketing"
    And I check the T&C checkbox
    And I fill out the Contact Us form excluding the "Message" field
    When I submit the form
    Then I should see an error message
