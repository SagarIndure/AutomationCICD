package devberry.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import devberry.TestComponents.BaseTest;
import devberry.pageobjects.CartPage;
import devberry.pageobjects.CheckOutPage;
import devberry.pageobjects.LandingPage;
import devberry.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckOutPage checkOutPage;
	
	@Given("I landed on Ecommerce application")
	public void I_Landed_On_Ecommerce_Application() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^I Logged in with (.+) and (.+)$")
	public void logged_In_With_UserName_And_Password(String userName, String password) {
		productCatalogue = landingPage.LoginAction(userName, password);
	}
	
	@When("I Want to add product (.+) into the cart$")
	public void i_Want_To_Add_Product_Into_Cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
		cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.matchProduct(productName);
		Assert.assertTrue(match);
	}
	
	@When("^I want to check the product (.+) and submit the order$")
	public void i_Want_To_Check_The_Product(String productName) {
		
		checkOutPage = cartPage.goToCheckOut();
		checkOutPage.fillCardDetails();
		checkOutPage.selectCountry("Ind");
		
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_Is_Displayed_On_ConfirmationPage(String string) {
		String confirm = checkOutPage.orderConfirmation();
		Assert.assertEquals(confirm, string);
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
