
@tag
Feature: Purchase the order from Ecommerce website
I want to use this template for my feature file

Background:
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmationPage

    Examples: 
      | name  										| password 		| productName |
      | jannu.saishveta@gmail.com |Khanishk@11	|ZARA COAT 3 	|
      