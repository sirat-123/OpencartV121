package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	void verify_account_registration() {
		
		logger.info("****Starting TC001_AccountRegistrationTest***");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("clicked on Register Link");
		
		
		AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		logger.info("providing customer details");
//		regPage.setFirstName("john");
//		regPage.setLastName("david");
//		regPage.setEmail("xyzjohndavif@gmail.com");
//		regPage.setTelephone("5565565645");
//		regPage.setPassword("xyz123");
//		regPage.setConfirmPassword("xyz123");
		//generating data rendomly
		regPage.setFirstName(randomeString().toUpperCase());
		
		regPage.setLastName(randomeString().toUpperCase());
		
		regPage.setEmail(randomeString()+"@gmail.com");//rendomly generated email
		
        regPage.setTelephone(randomeNumber());

		
		String Password=randomeAlphaNumberic();
		
		regPage.setPassword(Password);

		regPage.setConfirmPassword(Password);
		
		regPage.setPrivacy();
		
		regPage.setCountinue();
		
		logger.info("validating expected message..");
		String confmsg=regPage.getConfirmationMsg();
		
		if (confmsg.equals("Your Account Has Been Created!")){
			Assert.assertTrue(true);
			
		}else {
			logger.error("test failed");
			logger.debug("debug logs..");
			Assert.assertTrue(false);
		}
		
//		Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e) {
//			logger.error("test failed");
//			logger.debug("debug logs..");
			Assert.fail();
		}
		logger.info("****Finshed TC001_AccountRegistrationTest****");
		
	}
	
	 
	 
	 
	

}
