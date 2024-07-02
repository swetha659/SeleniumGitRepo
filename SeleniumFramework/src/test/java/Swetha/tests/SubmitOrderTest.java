package Swetha.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Swetha.TestComponents.BaseTest;
import Swetha.pageobjects.CartPage;
import Swetha.pageobjects.CheckoutPage;
import Swetha.pageobjects.ConfirmationPage;
import Swetha.pageobjects.OrderPage;
import Swetha.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName ="ZARA COAT 3";
    @Test(dataProvider = "getData", groups={"Purchase"})    
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
    {
				//LandingPage landingPage = launchApplication();
				ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"), input.get("password"));
				List<WebElement> products = productCatalogue.getProductList();
  				productCatalogue.addProductToCart(input.get("product"));
  				CartPage cartPage = productCatalogue.goToCartPage();
  				Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
				Assert.assertTrue(match);
				CheckoutPage checkoutPage= cartPage.goToCheckout();
				//checkoutPage.selectCountry("india");
				checkoutPage.SelectCountry("india");
				ConfirmationPage confirmationPage = checkoutPage.submitOrder();
				String confirmMessage = confirmationPage.getConfrimationMessage();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			}
    //To verify our order values are displaying in the orders section
    
    @Test(dependsOnMethods= {"submitOrder"})
    public void orderHistoryTest()
    {
    	ProductCatalogue productCatalogue= landingPage.loginApplication("jannu.saishveta@gmail.com", "Khanishk@11");
    	OrderPage orderPage= productCatalogue.goToOrdersPage();
    	//OrderPage orderPage = productCatalogue.goToOrdersPage();
    	Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    	
    }
    
    
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	
    	List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Swetha//Data//PurchaseOrder.json");    	
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}
    
    //Another way
   /* public Object[][] getData()
    {
    	return new Object[][] {{"jannu.saishveta@gmail.com","Khanishk@11","ZARA COAT 3"},{"jannu.saishveta@gmail.com","Khanishk@11","ADIDAS ORIGINAL"}};
    }
    HashMap<String,String> map=new HashMap<String,String>();
    	map.put("email", "jannu.saishveta@gmail.com");
    	map.put("password", "Khanishk@11");
    	map.put("product", "ZARA COAT 3");
    	
    	HashMap<String,String> map1=new HashMap<String,String>();
    	map1.put("email", "jannu.saishveta@gmail.com");
    	map1.put("password", "Khanishk@11");
    	map1.put("product", "ADIDAS ORIGINAL");
    */



