package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.HeaderPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class TC0001_LoginTest_ValidCredential extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String user) {
		log.info("~~~~~ Validating the login functionality of saucedemo application with valid credential");

		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		assertTrue(loginPage.getPageTitle().contains("Swag Labs"), "Failed to navigate to the required page!");
		log.info("~~~~~ Login Page is loaded.");

		HomePage homePage = loginPage.enterUsername(getConfigValue(user)).enterPassword(getPwrd()).clickLogin();
		assertTrue(homePage.isHomePageDisplayed(), "Failed to navigate to the Home page!");
		log.info("~~~~~ Navigated to Home Page after login.");

		HeaderPage headerPage = new HeaderPage();
		headerPage.proceedToMenuPage().proceedToLogout();
	}

}
