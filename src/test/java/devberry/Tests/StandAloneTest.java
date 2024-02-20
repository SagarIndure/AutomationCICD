package devberry.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import devberry.TestComponents.BaseTest;
import devberry.pageobjects.CartPage;
import devberry.pageobjects.CheckOutPage;
import devberry.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.LoginAction("sagarindurepatil@gmail.com", "55@iPatil");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.matchProduct(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.fillCardDetails();
		checkOutPage.selectCountry("Ind");
		String confirm = checkOutPage.orderConfirmation();
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
