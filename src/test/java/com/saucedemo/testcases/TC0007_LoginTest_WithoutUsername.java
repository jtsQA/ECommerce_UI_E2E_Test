package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class TC0007_LoginTest_WithoutUsername extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String user, String data) {
		log.info("~~~~~ Validating the username field is empty and the error message is displayed while trying to Login.");
		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		log.info("~~~~~ Login Page is loaded.");
		loginPage.enterUsername(user).clickLogin();
		assertTrue(loginPage.isErrorMessageDisplayed(data), "Failed to show the error message for empty username field!");
		log.info("~~~~~ Username field is empty and the error message is displayed while trying to Login.");
	}

}
