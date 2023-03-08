package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class HeaderPage extends BasePage {

	/* Initializing the Page Objects */
	public HeaderPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By logoHeaderPage = By.className("app_logo");
	private final static By linkShoppingCart = By.className("shopping_cart_link");
	private final By linkCartBadge = By.className("shopping_cart_badge");
	private final By buttonMenu = By.id("react-burger-menu-btn");

	public static By getLinkShoppingCart() {
		return linkShoppingCart;
	}

	public boolean isLogoDisplayed() {
		return verifyDisplayed(logoHeaderPage);
	}

	public MenuPage proceedToMenuPage() {
		confirmClick(buttonMenu);
		return new MenuPage();
	}

	public CartPage proceedToCartPage() {
		confirmClick(linkShoppingCart);
		return new CartPage();
	}

	public int getCartProductCount() {
		return Integer.valueOf(retrieveData(linkCartBadge));
	}

}
