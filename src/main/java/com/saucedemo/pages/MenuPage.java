package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class MenuPage extends BasePage {

	/* Initializing the Page Objects */
	public MenuPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkHomePageSidebar = By.id("inventory_sidebar_link");
	private final By linkLogoutSidebar = By.id("logout_sidebar_link");
	private final By linkResetSidebar = By.id("reset_sidebar_link");

	public HomePage proceedToHomePage() {
		confirmClick(linkHomePageSidebar);
		return new HomePage();
	}

	public LoginPage proceedToLogout() {
		confirmClick(linkLogoutSidebar);
		return new LoginPage();
	}

	public HeaderPage proceedToResetAppState() {
		confirmClick(linkResetSidebar);
		return new HeaderPage();
	}

}
