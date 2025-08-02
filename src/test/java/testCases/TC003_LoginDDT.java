package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*
 * data is valid-login success -test case pass -logout
 * data is valid -login failed- test case failed
 * 
 *  data is invalid- login success-test case failed- logout
 *  data is invalid -login failed -test case pass
 * */

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")//getting data provider from different class
	public void veryfy_loginDDT (String email,String pwd, String exp) {
		//

		logger.info("****Starting TC003_LoginDDT****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}else {
			Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("****Finished TC003_LoginDDT****");
		
	}
	
	

}
