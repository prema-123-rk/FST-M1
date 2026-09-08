@activity4
Feature: Login Test

  Scenario: Testing Login without Examples
    Given the user is on the login page
    When the user enters "testuser" and "password123"
    And clicks the submit button
    Then get the confirmation message and verify it
