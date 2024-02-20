package devberry.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devberry.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPass;

	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMsg;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

	public ProductCatalogue LoginAction(String userID, String password) {

		userEmail.sendKeys(userID);
		userPass.sendKeys(password);
		loginBtn.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
