package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class CheckoutInfoPage extends BasePage {

	private static final String PAGE_TITLE = "Checkout: Your Information";

	/* Initializing the Page Objects */
	public CheckoutInfoPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkPageTitle = By.className("title");
	private final By linkCheckoutInfoPage = By.xpath("//span[@class='title' and text()='" + PAGE_TITLE + "']");
	private final By inputFirstName = By.id("first-name");
	private final By inputLastName = By.id("last-name");
	private final By inputPostalCode = By.id("postal-code");
	private final By buttonContinue = By.id("continue");
	private final By buttonCancel = By.id("cancel");

	public String getPageTitle() {
		return retrieveData(linkPageTitle);
	}

	public boolean verifyPageTitle() {
		return getPageTitle().equals(PAGE_TITLE);
	}

	public boolean isCheckoutInfoPageDisplayed() {
		return verifyDisplayed(linkCheckoutInfoPage);
	}

	public CheckoutInfoPage enterFirstName(String inputData) {
		sendInputData(inputFirstName, inputData);
		return this;
	}

	public CheckoutInfoPage enterLastName(String inputData) {
		sendInputData(inputLastName, inputData);
		return this;
	}

	public CheckoutInfoPage enterPostalCode(String inputData) {
		sendInputData(inputPostalCode, inputData);
		return this;
	}

	public CheckoutOverviewPage proceedToCheckoutOverview() {
		confirmClick(buttonContinue);
		return new CheckoutOverviewPage();
	}

	public CartPage proceedBackToCart() {
		confirmClick(buttonCancel);
		return new CartPage();
	}

}
