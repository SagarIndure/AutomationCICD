
@tag
Feature: Error Validation
  I want to use this template for my feature file






  @ErrorValidation
  Scenario Outline: Error Validation of Login to Application
    Given I landed on Ecommerce application
    When I Logged in with <userName> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | userName  						| password 			|
      | indurepatil@gmail.com |     @iPatil 	|
     
