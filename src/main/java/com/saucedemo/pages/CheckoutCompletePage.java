package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class CheckoutCompletePage extends BasePage {

	private static final String PAGE_TITLE = "Checkout: Complete!";
	private static final String ORDER_CONFIRMATION = "Thank you for your order!";
	private static final String ORDER_DISPATCH = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

	/* Initializing the Page Objects */
	public CheckoutCompletePage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkPageTitle = By.className("title");
	private final By linkCheckoutCompletePage = By.xpath("//span[@class='title' and text()='" + PAGE_TITLE + "']");
	private final By linkOrderConfirmation = By.className("complete-header");
	private final By linkOrderDispatchDetails = By.className("complete-text");
	private final By buttonBackToHome = By.id("back-to-products");

	public String getPageTitle() {
		return retrieveData(linkPageTitle);
	}

	public boolean verifyPageTitle() {
		return getPageTitle().equals(PAGE_TITLE);
	}

	public boolean isCheckoutCompletePageDisplayed() {
		return verifyDisplayed(linkCheckoutCompletePage);
	}

	public String getOrderConfirmation() {
		return retrieveData(linkOrderConfirmation);
	}

	public String getOrderDispatchDetails() {
		return retrieveData(linkOrderDispatchDetails);
	}

	public boolean verifyOrderConfirmation() {
		return getOrderConfirmation().equals(ORDER_CONFIRMATION);
	}

	public boolean verifyOrderDispatch() {
		return getOrderDispatchDetails().equalsIgnoreCase(ORDER_DISPATCH);
	}

	public HomePage proceedBackToHome() {
		confirmClick(buttonBackToHome);
		return new HomePage();
	}

}
