package devberry.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devberry.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='payment__cc']/form/div/div[1]/div/input")
	WebElement cardNumber;

	@FindBy(xpath = "//select[@class='input ddl'][1]/option")
	List<WebElement> expiryMonths;

	@FindBy(xpath = "//select[@class='input ddl'][2]/option")
	List<WebElement> expiryDates;

	@FindBy(xpath = "//div[@class='form__cc']/div[3]/div/input")
	WebElement cardHolderName;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(css = ".ta-item span")
	List<WebElement> countries;

	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	@FindBy(css = ".hero-primary")
	WebElement confirmationMsg;

	@FindBy(xpath = "//td/label[@class='ng-star-inserted']")
	WebElement orderID;

	By CCOption = By.xpath("//div[@class='payment__cc']/form/div/div[1]/div/input");
	By countrySelection = By.cssSelector(".hero-primary");

	public void fillCardDetails() {
		waitForElementToAppear(CCOption);
		cardNumber.clear();
		cardNumber.sendKeys("9527575550914541");
		WebElement desiredMonth = expiryMonths.stream().filter(expiryMonth -> expiryMonth.getText().equals("03"))
				.findFirst().orElse(null);
		desiredMonth.click();
		WebElement desiredDate = expiryDates.stream().filter(expiryDate -> expiryDate.getText().equals("24"))
				.findFirst().orElse(null);
		desiredDate.click();
		cardHolderName.sendKeys("Sagar Indure Patil");

		System.out.println();
	}

	public void selectCountry(String desiredCountry) {
		countryInput.sendKeys(desiredCountry);
		WebElement requireCountry = countries.stream().filter(country -> country.getText().equalsIgnoreCase("India"))
				.findFirst().orElse(null);
		requireCountry.click();
		submitBtn.click();
		waitForElementToAppear(countrySelection);

	}

	public String orderConfirmation() {
		
		String confirmation = confirmationMsg.getText();
		
		return confirmation;
	}

}
