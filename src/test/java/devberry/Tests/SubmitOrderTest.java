package devberry.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import devberry.TestComponents.BaseTest;
import devberry.pageobjects.CartPage;
import devberry.pageobjects.CheckOutPage;
import devberry.pageobjects.OrderPage;
import devberry.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.LoginAction(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.matchProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.fillCardDetails();
		checkOutPage.selectCountry("Ind");
		String confirm = checkOutPage.orderConfirmation();
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrdersHistoryTest() {

		
		ProductCatalogue productCatalogue = landingPage.LoginAction("sagarindurepatil@gmail.com", "55@iPatil");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();

		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "sagarindurepatil@gmail.com");
		map.put("password", "55@iPatil");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "indurepatil@gmail.com");
		map1.put("password", "55@iPatil");
		map1.put("product", "ADIDAS ORIGINAL");
		*/
		
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//devberry//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	
	
	
	
	
}
