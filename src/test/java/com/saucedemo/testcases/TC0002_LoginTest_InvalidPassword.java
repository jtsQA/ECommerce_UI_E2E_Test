package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class TC0002_LoginTest_InvalidPassword extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String user, String data) {
		log.info("~~~~~ Validating the error message displayed for invalid credential in login page.");
		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		log.info("~~~~~ Login Page is loaded.");
		loginPage.enterUsername(user).enterPassword(getPwrd()).clickLogin();
		assertTrue(loginPage.isErrorMessageDisplayed(data), "Failed to show the error message for invalid username in login page!");
		log.info("~~~~~ Error message displayed for invalid username in login page.");
	}

}
