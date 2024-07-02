package Swetha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Swetha.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {// the arguments scope is within this method so in order to
		super(driver);
		this.driver=driver;	//retains the scope outside the method we initialize the driver.
		PageFactory.initElements(driver, this);
	}
	
	/*@FindBy(css="placeholder='Select Country'")
	WebElement country; */
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
    WebElement selectCountry;
	
	/*@FindBy(css = "[placeholder='Select Country']")

	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")

	WebElement selectCountry;

	//private By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

	Actions a = new Actions(driver);

	a.sendKeys(country, countryName).build().perform();

	waitForElementToAppear(By.cssSelector(".ta-results"));

	selectCountry.click();
	}*/
		
	By results = By.cssSelector(".ta-results");
	
	
	public void SelectCountry(String countryName) {
		Actions a =new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
	

}
