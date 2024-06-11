
@tag
Feature: Purchase a product from e-commerce site
  I want to use this template for my feature file

	Background:
	Given I landed on E-commerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given logged in with username <user> and password <pass>
    When I add the product <productName> from catalogue to the cart
    And Checkout <productName> and submit the order
    Then I verify "THANKYOU FOR THE ORDER." message is displayed.

    Examples: 
      | user  						| pass 				| productName  		 |
      | devenik@email.com | Password123 | ZARA COAT 3 		 |
      | devenik2@email.com| Password456 | ADIDAS ORIGINAL  |
