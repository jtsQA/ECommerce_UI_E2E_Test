package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class TC0009_LoginTest_UsernameInPlaceholder extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String placeholder) {
		log.info("~~~~~ Validating the username is available as placeholder in the username field.");
		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		log.info("~~~~~ Login Page is loaded.");
		assertTrue(loginPage.isUsernameInPlaceholder().equals(placeholder), "Username is not available as placeholder in the username field!");
		log.info("~~~~~ Username is available as placeholder in the username field.");
	}

}
