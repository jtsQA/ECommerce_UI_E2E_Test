package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.BasePage;

public class FooterPage extends BasePage {

	/* Initializing the Page Objects */
	public FooterPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By linkCopyRights = By.className("footer_copy");
	private final By linkSocial = By.xpath("//ul[@class='social']/li/a");

	public String getCopyRightsDetails() {
		return retrieveData(linkCopyRights);
	}

	public FooterPage getSocialMediaDetails() {
		findBrokenLinks(linkSocial);
		return this;
	}

}
