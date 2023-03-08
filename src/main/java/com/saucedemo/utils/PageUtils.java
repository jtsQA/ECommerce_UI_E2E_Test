package com.saucedemo.utils;

import static com.saucedemo.utils.WaitUtils.performWait;
import static com.saucedemo.utils.WaitUtils.performWaitForList;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.saucedemo.enums.WaitApproach;

public class PageUtils {

	protected static WebDriver driver;

	public static void setDriver(WebDriver driver) {
		PageUtils.driver = driver;
	}

	public static boolean verifyDisplayed(By by) {
		return performWait(WaitApproach.VISIBLE, driver, by).isDisplayed();
	}

	public static void confirmClick(By by) {
		performWait(WaitApproach.CLICKABLE, driver, by).click();
	}

	public static void sendInputData(By by, String data) {
		WebElement element = performWait(WaitApproach.PRESENCE, driver, by);
		element.clear();
		element.sendKeys(data);
	}

	public static String retrieveData(By by) {
		return performWait(WaitApproach.PRESENCE, driver, by).getText();
	}

	public static String retrieveAttributeValue(By by, String inputData) {
		return performWait(WaitApproach.PRESENCE, driver, by).getAttribute(inputData.toLowerCase());
	}

	public static void findBrokenLinks(By by) {
		List<WebElement> links = performWaitForList(WaitApproach.PRESENCE, driver, by);
		for (WebElement link : links) {
			String href = link.getAttribute("href");
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
				connection.setRequestMethod("HEAD");
				int responseCode = connection.getResponseCode();
				if (responseCode != 200) {
					System.err.println("~~~~~ Broken link: " + href);
				}
			} catch (Exception e) {
				System.err.println("~~~~~ Error checking link: " + href);
			}
		}
	}

	public static List<String> retrieveDataFromList(By by) {
		List<String> strList = new ArrayList<String>();
		for (WebElement element : performWaitForList(WaitApproach.PRESENCE, driver, by)) {
			strList.add(element.getText());
		}
		return strList;
	}

	public static void confirmClickFromList(By by, String inputData) {
		for (WebElement element : performWaitForList(WaitApproach.PRESENCE, driver, by)) {
			if (element.getText().contains(inputData)) {
				element.click();
				break;
			}
		}
	}

	public static void confirmClickFromList(By by1, By by2, String inputData) {
		List<WebElement> elements1 = performWaitForList(WaitApproach.PRESENCE, driver, by1);
		List<WebElement> elements2 = performWaitForList(WaitApproach.CLICKABLE, driver, by2);
		for (int i = 0; i < elements1.size(); i++) {
			if (elements1.get(i).getText().contains(inputData)) {
				elements2.get(i).click();
				break;
			}
		}
	}
}
