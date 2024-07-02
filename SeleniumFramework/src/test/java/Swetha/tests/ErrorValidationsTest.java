package Swetha.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Swetha.TestComponents.BaseTest;
import Swetha.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
    @Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
    
    public void LoginErrorValidation() throws IOException, InterruptedException
    {	
		landingPage.loginApplication("jannu.s66aishveta@gmail.com", "Kha66nishk@11");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());			
	}
}


