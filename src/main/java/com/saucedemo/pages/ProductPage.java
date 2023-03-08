package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class ProductPage extends BasePage {


	/* Initializing the Page Objects */
	public ProductPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By buttonAddToCart = By.xpath("//button[contains(@id,'add-to-cart')]");

	public CartPage addProductToCart() {
		confirmClick(buttonAddToCart);
		return new CartPage();
	}

}
