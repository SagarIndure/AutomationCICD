package devberry.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import devberry.TestComponents.BaseTest;
import devberry.TestComponents.Retry;
import devberry.pageobjects.CartPage;
import devberry.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		landingPage.LoginAction("sagarindure@gmail.com", "55iPatil");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.LoginAction("indurepatil@gmail.com", "55@iPatil");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.matchProduct("Zara Coat 3");
		Assert.assertFalse(match);

	}
}
