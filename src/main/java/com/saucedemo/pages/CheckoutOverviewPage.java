package com.saucedemo.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class CheckoutOverviewPage extends BasePage {

	private static final String PAGE_TITLE = "Checkout: Overview";
	private static Map<String, String> productDetails = new LinkedHashMap<String, String>();

	/* Initializing the Page Objects */
	public CheckoutOverviewPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkPageTitle = By.className("title");
	private final By linkCheckoutOverviewPage = By.xpath("//span[@class='title' and text()='" + PAGE_TITLE + "']");
	private final By linkProductName = By.className("inventory_item_name");
	private final By linkProductPrice = By.className("inventory_item_price");
	private final By buttonFinish = By.id("finish");
	private final By buttonCancel = By.id("cancel");

	public String getPageTitle() {
		return retrieveData(linkPageTitle);
	}

	public boolean verifyPageTitle() {
		return getPageTitle().equals(PAGE_TITLE);
	}

	public boolean isCheckoutOverviewPageDisplayed() {
		return verifyDisplayed(linkCheckoutOverviewPage);
	}

	public List<String> getProductNames() {
		return retrieveDataFromList(linkProductName);
	}

	public List<String> getProductPrices() {
		return retrieveDataFromList(linkProductPrice);
	}

	public CheckoutCompletePage proceedToCheckoutComplete() {
		confirmClick(buttonFinish);
		return new CheckoutCompletePage();
	}

	public CartPage proceedBackToCart() {
		confirmClick(buttonCancel);
		return new CartPage();
	}

	public Map<String, String> getProductDetails() {
		List<String> productNames = getProductNames();
		List<String> productPrices = getProductPrices();
		if (productNames.size() == productPrices.size()) {
			for (int i = 0; i < productNames.size(); i++) {
				productDetails.put(productNames.get(i), productPrices.get(i));
			}
		} else {
			System.err.println("Error at the Checkout Overview Page!");
		}
		return productDetails;
	}

}
