package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class TC0005_LoginTest_UnmaskedUsername extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String user, String type) {
		log.info("~~~~~ Validating the username field is not masked in the Login page.");
		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		log.info("~~~~~ Login Page is loaded.");
		loginPage.enterUsername(getConfigValue(user));
		assertTrue(loginPage.isUsernameUnmasked(type), "Username field is masked in the Login page!");
		log.info("~~~~~ Username field is not masked in the Login page.");
	}

}
