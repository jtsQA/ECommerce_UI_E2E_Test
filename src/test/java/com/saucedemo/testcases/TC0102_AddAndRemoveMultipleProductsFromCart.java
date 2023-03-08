package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class TC0102_AddAndRemoveMultipleProductsFromCart extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String user, String product1, String product2) {
		log.info("~~~~~ Add and Remove multiple products from the cart in the Products (Home) Page.");

		SoftAssert softAssert = new SoftAssert();

		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the Login page!");
		softAssert.assertTrue(loginPage.verifyPageTitle(), "Failed to match the Login Page Title!");

		HomePage homePage = loginPage.enterUsername(getConfigValue(user)).enterPassword(getPwrd()).clickLogin();
		assertTrue(homePage.isHomePageDisplayed(), "Failed to navigate to the Home page!");
		softAssert.assertTrue(homePage.verifyPageTitle(), "Failed to match the Page Title!");
		homePage.addProductToCart(product1).addProductToCart(product2);
		log.info(String.format("~~~~~ Products: %s and %s are added to cart.", product1, product2));
		homePage.removeProductFromCart(product1).removeProductFromCart(product2);
		log.info(String.format("~~~~~ Products: %s and %s are removed from cart.", product1, product2));
		softAssert.assertAll();
	}

}
