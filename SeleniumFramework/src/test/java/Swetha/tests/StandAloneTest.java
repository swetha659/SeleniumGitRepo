package Swetha.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Swetha.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				String productName ="ZARA COAT 3";
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				WebDriver driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get("https://rahulshettyacademy.com/client");
				LandingPage landingPage = new LandingPage(driver);
				driver.findElement(By.id("userEmail")).sendKeys("jannu.saishveta@gmail.com");
				driver.findElement(By.id("userPassword")).sendKeys("Khanishk@11");
				driver.findElement(By.id("login")).click();
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
				List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
				WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
				prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
				//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("[routerlink*='cart']")));
				
				List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
				Boolean match= cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
				Assert.assertTrue(match);
				//css with parent child traverse method
				driver.findElement(By.cssSelector(".totalRow button")).click();
				driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
				Actions a =new Actions(driver);
				a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
				//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
				//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
				//driver.findElement(By.xpath("//button[2]/span[@innertext=' India']")).click();
				driver.findElement(By.cssSelector(".action__submit")).click();
				String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				driver.close();
			}	

		


	}


