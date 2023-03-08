package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;

public class TC0004_LoginTest_MaskedPassword extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String type) {
		log.info("~~~~~ Validating the password field is masked in the Login page.");
		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		log.info("~~~~~ Login Page is loaded.");
		loginPage.enterPassword(getPwrd());
		assertTrue(loginPage.isPasswordMasked(type), "Password field is not masked in the Login page!");
		log.info("~~~~~ Password field is masked in the Login page.");
	}

}
