
@tag
Feature: Purchase the product from ecommerce application
 





	Background:
	Given I landed on Ecommerce application
	

  @Regression
  Scenario Outline: Positive test of purchasing product
    Given I Logged in with <userName> and <password>
    When I Want to add product <productName> into the cart
    And I want to check the product <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | userName   										| password 			| productName				 |
      | sagarindurepatil@gmail.com 		|	55@iPatil 		| ZARA COAT 3 			 |
      