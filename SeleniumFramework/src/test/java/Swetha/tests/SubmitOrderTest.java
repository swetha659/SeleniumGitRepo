package Swetha.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Swetha.TestComponents.BaseTest;
import Swetha.pageobjects.CartPage;
import Swetha.pageobjects.CheckoutPage;
import Swetha.pageobjects.ConfirmationPage;
import Swetha.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
    @Test
    
    public void submitOrder() throws IOException, InterruptedException
    {
		
				String productName ="ZARA COAT 3";
				//LandingPage landingPage = launchApplication();
				ProductCatalogue productCatalogue= landingPage.loginApplication("jannu.saishveta@gmail.com", "Khanishk@11");
				List<WebElement> products = productCatalogue.getProductList();
  				productCatalogue.addProductToCart(productName);
  				CartPage cartPage = productCatalogue.goToCartPage();
  				Boolean match = cartPage.VerifyProductDisplay(productName);
				Assert.assertTrue(match);
				CheckoutPage checkoutPage= cartPage.goToCheckout();
				//checkoutPage.selectCountry("india");
				checkoutPage.SelectCountry("india");
				ConfirmationPage confirmationPage = checkoutPage.submitOrder();
				String confirmMessage = confirmationPage.getConfrimationMessage();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
			}
}


