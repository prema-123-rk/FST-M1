@activity3
Feature: Testing with Tags

  Scenario: Testing with Simple Alert
    Given the user is on the alerts page
    When the user clicks the simple alert button
    Then the alert message should be displayed
    And the user accepts the alert

  Scenario: Testing with Confirm Alert
    Given the user is on the alerts page
    When the user clicks the confirm alert button
    Then the confirm alert message should be displayed
    And the user dismisses the alert

  Scenario Outline: Testing with Prompt Alert
    Given the user is on the alerts page
    When the user clicks the prompt alert button
    And enters "<text>" into the prompt
    Then the result text should show "<text>"

    Examples:
      | text     |
      | Hello    |
      | Selenium |
