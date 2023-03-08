package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class TC0010_LoginTest_PasswordInPlaceholder extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String data) {
		log.info("~~~~~ Validating the password is available as placeholder in the password field.");
		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		log.info("~~~~~ Login Page is loaded.");
		assertTrue(loginPage.isPasswordInPlaceholder().equals(data), "Password is not available as placeholder in the password field!");
		log.info("~~~~~ Password is available as placeholder in the password field.");
	}

}
