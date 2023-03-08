package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;
import com.saucedemo.enums.FrameworkConstants;

public class LoginPage extends BasePage {

	private static final String PAGE_TITLE = "Swag Labs";
	
	/* Initializing the Page Objects */
	public LoginPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By logoLoginPage = By.className("login_logo");
	private final By inputUsername = By.id("user-name");
	private final By inputPassword = By.id("password");
	private final By buttonLogin = By.id("login-button");
	private final By linkErrorMessage = By.xpath("//div[contains(@class,'error-message')]");

	public String getPageTitle() {
		return getTitle();
	}

	public boolean verifyPageTitle() {
		return getPageTitle().equals(PAGE_TITLE);
	}

	public boolean isLogoDisplayed() {
		return verifyDisplayed(logoLoginPage);
	}

	public LoginPage enterUsername(String inputData) {
		sendInputData(inputUsername, inputData);
		return this;
	}

	public LoginPage enterPassword(String inputData) {
		sendInputData(inputPassword, inputData);
		return this;
	}

	public boolean isUsernameUnmasked(String inputData) {
		return retrieveAttributeValue(inputUsername, FrameworkConstants.TYPE.name()).equals(inputData);
	}

	public boolean isPasswordMasked(String inputData) {
		return retrieveAttributeValue(inputPassword, FrameworkConstants.TYPE.name()).equals(inputData);
	}

	public String isUsernameInPlaceholder() {
		return retrieveAttributeValue(inputUsername, FrameworkConstants.PLACEHOLDER.name());
	}

	public String isPasswordInPlaceholder() {
		return retrieveAttributeValue(inputPassword, FrameworkConstants.PLACEHOLDER.name());
	}

	public HomePage clickLogin() {
		confirmClick(buttonLogin);
		return new HomePage();
	}

	public boolean isErrorMessageDisplayed(String inputData) {
		return retrieveData(linkErrorMessage).equalsIgnoreCase(inputData);
	}

}
