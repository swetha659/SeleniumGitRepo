package Swetha.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Swetha.TestComponents.BaseTest;
import Swetha.pageobjects.CartPage;
import Swetha.pageobjects.CheckoutPage;
import Swetha.pageobjects.ConfirmationPage;
import Swetha.pageobjects.LandingPage;
import Swetha.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	// mention(.+) meaning regular expn ie takes arugument of any type // ^ mention at start $ mention at last // ^ $ means, @Given has some regular expression
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage= cartPage.goToCheckout();
		checkoutPage.SelectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on the confirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfrimationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then ("^\"([^\"]*)\" message is displayed$")
	public void message_displayed(String strArg1)
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
	
	
	
	
	
}
