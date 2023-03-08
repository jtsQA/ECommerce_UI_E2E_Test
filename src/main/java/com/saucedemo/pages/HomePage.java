package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class HomePage extends BasePage {

	private static final String PAGE_TITLE = "Products";

	/* Initializing the Page Objects */
	public HomePage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkPageTitle = By.className("title");
	private final By linkHomePage = By.xpath("//span[@class='title' and text()='" + PAGE_TITLE + "']");
	private final By linkProductName = By.className("inventory_item_name");
	private final By buttonAddToCart = By.xpath("//button[contains(@id,'add-to-cart')]");
	private final By buttonRemoveProduct = By.xpath("//button[contains(@id,'add-to-cart')]");

	public String getPageTitle() {
		return retrieveData(linkPageTitle);
	}

	public boolean verifyPageTitle() {
		return getPageTitle().equals(PAGE_TITLE);
	}

	public boolean isHomePageDisplayed() {
		return verifyDisplayed(linkHomePage);
	}

	public String getProductName() {
		return retrieveData(linkProductName);
	}

	public HomePage addProduct(String inputData) {
		confirmClickFromList(linkProductName, buttonAddToCart, inputData);
		return this;
	}

	public HomePage addProduct() {
		confirmClick(buttonAddToCart);
		return this;
	}

	public HomePage removeProduct() {
		confirmClick(buttonRemoveProduct);
		return this;
	}

	public HomePage addProductToCart(String product) {
		// By btnRemoveProduct = By.xpath("//div[text()='" + product + "']//ancestor::div[@class='inventory_item_label']//following-sibling::div/button");
		String add = "add-to-cart-sauce-labs-" + product.replace(" ", "-").toLowerCase();
		By btnAddProduct = By.id(add);
		confirmClick(btnAddProduct);
		return this;
	}

	public HomePage removeProductFromCart(String product) {
		String remove = "remove-sauce-labs-" + product.replace(" ", "-").toLowerCase();
		By btnRemoveProduct = By.id(remove);
		confirmClick(btnRemoveProduct);
		return this;
	}

	public CartPage proceedToCartPage() {
		confirmClick(HeaderPage.getLinkShoppingCart());
		return new CartPage();
	}

}
