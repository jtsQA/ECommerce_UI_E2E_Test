package com.saucedemo.testcases;

import static org.testng.Assert.assertTrue;
import java.util.Map;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutInfoPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.HeaderPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class TC0501_OrderCheckout extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void run(String user, String product, String firstName, String lastName, String postalCode) {
		log.info("~~~~~ Place an order and checkout the product.");

		SoftAssert softAssert = new SoftAssert();

		LoginPage loginPage = new LoginPage();
		assertTrue(loginPage.isLogoDisplayed(), "Failed to load the required page!");
		softAssert.assertTrue(loginPage.verifyPageTitle(), "Failed to match the Login Page Title!");

		HomePage homePage = loginPage.enterUsername(getConfigValue(user)).enterPassword(getPwrd()).clickLogin();
		assertTrue(homePage.isHomePageDisplayed(), "Failed to navigate to the Home page!");
		softAssert.assertTrue(homePage.verifyPageTitle(), "Failed to match the Page Title!");
		homePage.addProductToCart(product).proceedToCartPage();

		CartPage cartPage = new CartPage();
		assertTrue(cartPage.isCartPageDisplayed(), "Failed to navigate to the Cart page!");
		softAssert.assertTrue(cartPage.verifyPageTitle(), "Failed to match the Cart Page Title!");
		Map<String, String> mapCart = cartPage.getProductDetails();
		cartPage.proceedToCheckout();

		CheckoutInfoPage cInfoPage = new CheckoutInfoPage();
		assertTrue(cInfoPage.isCheckoutInfoPageDisplayed(), "Failed to navigate to the Checkout Information page!");
		softAssert.assertTrue(cInfoPage.verifyPageTitle(), "Failed to match the Checkout Information Page Title!");
		cInfoPage.enterFirstName(firstName).enterLastName(lastName).enterPostalCode(postalCode).proceedToCheckoutOverview();

		CheckoutOverviewPage cOverviewPage = new CheckoutOverviewPage();
		assertTrue(cOverviewPage.isCheckoutOverviewPageDisplayed(), "Failed to navigate to the Checkout Overview page!");
		softAssert.assertTrue(cOverviewPage.verifyPageTitle(), "Failed to match the Checkout Overview Page Title!");
		Map<String, String> mapCheckout = cOverviewPage.getProductDetails();
		cOverviewPage.proceedToCheckoutComplete();

		log.debug(String.format("~~~~~ Compare Data \nCart Page: %s \nCheckout Page: %s", mapCart.toString(), mapCheckout.toString()));
		for (String key : mapCart.keySet()) {
			softAssert.assertEquals(mapCart.get(key), mapCheckout.get(key), "Price of product: " + key + " is not matching in Cart and Checkout pages.");
		}

		CheckoutCompletePage cCompletePage = new CheckoutCompletePage();
		assertTrue(cCompletePage.isCheckoutCompletePageDisplayed(), "Failed to navigate to the Checkout Complete page!");
		softAssert.assertTrue(cCompletePage.verifyPageTitle(), "Failed to match the Checkout Complete Page Title!");
		boolean orderConfirmation = cCompletePage.verifyOrderConfirmation();
		boolean orderDispatch = cCompletePage.verifyOrderDispatch();
		softAssert.assertTrue(orderConfirmation, "Failed to place the Order!");
		softAssert.assertTrue(orderDispatch, "Failed to dispatch the Order!");
		if (orderConfirmation || orderDispatch) {
			log.info(String.format("~~~~~ %s %s", cCompletePage.getOrderConfirmation(), cCompletePage.getOrderDispatchDetails()));
		}
		cCompletePage.proceedBackToHome();

		HeaderPage headerPage = new HeaderPage();
		headerPage.proceedToMenuPage().proceedToLogout();

		softAssert.assertAll();
	}

}
