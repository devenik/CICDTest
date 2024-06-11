
@tag
Feature: VAlidating error messages for the logging.
  
  
  @ErrorValidation
  Scenario: Verifying a bad login
    Given I landed on E-commerce Page
    When logged in with a bad username and password
    Then "Incorrect email or password." message is displayed
