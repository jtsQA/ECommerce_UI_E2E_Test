package com.saucedemo.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class CartPage extends BasePage {

	private static final String PAGE_TITLE = "Your Cart";
	private static Map<String, String> productDetails = new LinkedHashMap<String, String>();

	/* Initializing the Page Objects */
	public CartPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkPageTitle = By.className("title");
	private final By linkCartPage = By.xpath("//span[@class='title' and text()='" + PAGE_TITLE + "']");
	private final By linkProductName = By.className("inventory_item_name");
	private final By linkProductPrice = By.className("inventory_item_price");
	private final By buttonRemoveProduct = By.xpath("//button[contains(@id,'remove')]");
	private final By buttonContinueShopping = By.id("continue-shopping");
	private final By buttonCheckout = By.id("checkout");

	public String getPageTitle() {
		return retrieveData(linkPageTitle);
	}

	public boolean verifyPageTitle() {
		return getPageTitle().equals(PAGE_TITLE);
	}

	public boolean isCartPageDisplayed() {
		return verifyDisplayed(linkCartPage);
	}

	public List<String> getProductNames() {
		return retrieveDataFromList(linkProductName);
	}

	public List<String> getProductPrices() {
		return retrieveDataFromList(linkProductPrice);
	}

	public CartPage removeProduct() {
		confirmClick(buttonRemoveProduct);
		return this;
	}

	public HomePage continueShopping() {
		confirmClick(buttonContinueShopping);
		return new HomePage();
	}

	public CartPage proceedToCheckout() {
		confirmClick(buttonCheckout);
		return this;
	}

	public Map<String, String> getProductDetails() {
		List<String> productNames = getProductNames();
		List<String> productPrices = getProductPrices();
		if (productNames.size() == productPrices.size()) {
			for (int i = 0; i < productNames.size(); i++) {
				productDetails.put(productNames.get(i), productPrices.get(i));
			}
		} else {
			System.err.println("Error at the Cart Page!");
		}
		return productDetails;
	}

}
