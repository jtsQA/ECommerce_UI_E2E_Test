package com.saucedemo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.saucedemo.enums.BrowserType;
import com.saucedemo.enums.FileDirectory;
import com.saucedemo.enums.FrameworkConstants;
import com.saucedemo.enums.WaitTime;
import com.saucedemo.utils.PageUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage extends PageUtils {

	protected static WebDriver driver;
	private static Properties properties = new Properties();

	/* Eager Initialization */
	static {
		try {
			properties.load(new FileInputStream(FileDirectory.CONFIG_FILE.getDirectory()));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void setup(String browser) {
		if (Objects.isNull(driver)) {
			if (Objects.isNull(getBrowser(browser))) {
				for (int i = 0; i < 3; i++) {
					if (Objects.nonNull(getBrowser(browser))) {
						break;
					}
				}
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(WaitTime.PAGE_LOAD_TIME.getTime()));
			driver.get(getConfigValue(FrameworkConstants.URL.name().toLowerCase()));
		}
	}

	private static WebDriver getBrowser(String browser) {
		String incognito = "--incognito";
		String disableExtensions = "--disable-extensions";
		String disablePopupBlocking = "--disable-popup-blocking";
		String disableNotifications = "--disable-notifications";
		if (!browser.equalsIgnoreCase(BrowserType.CHROME.getBrowser())) {
			if (browser.equalsIgnoreCase(BrowserType.EDGE.getBrowser())) {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments(disableExtensions, incognito, disablePopupBlocking, disableNotifications);
				driver = new EdgeDriver(options);
			} else if (browser.equalsIgnoreCase(BrowserType.FIREFOX.getBrowser())) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments(disableExtensions);
				options.addPreference("browser.privatebrowsing.autostart", true);
				options.addPreference("dom.disable_open_during_load", false);
				options.addPreference("dom.popup_maximum", 0);
				driver = new FirefoxDriver(options);
			}
		} else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments(disableExtensions, incognito, disablePopupBlocking, disableNotifications);
			driver = new ChromeDriver(options);
		}
		return driver;
	}

	public static void tearDown() {
		if (Objects.nonNull(driver)) {
			driver.quit();
			driver = null;
		}
	}

	protected static void getToPage(String url) {
		driver.get(getConfigValue(url));
	}

	protected String getToPageInWindow(String url) {
		driver.get(getConfigValue(url));
		return driver.getWindowHandle();
	}

	protected String getTitle() {
		String title = driver.getTitle();
		System.out.println("~~~~~ Page title: " + title);
		return title;
	}

	protected String getToPageInNewWindow(String url, String window) {
		String newWindowHandle = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + getConfigValue(url) + "','_blank');");
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(window)) {
				newWindowHandle = handle;
				break;
			}
		}
		return newWindowHandle;
	}

	public void switchToWindow(String window) {
		driver.switchTo().window(window);
	}

	public static String getConfigValue(String key) {
		if (Objects.isNull(key) || Objects.isNull(properties.getProperty(key))) {
			try {
				throw new IllegalArgumentException("Property name " + key + " is not found. Please check the config.properties file.");
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return properties.getProperty(key).trim();
	}

	public static String getPwrd() {
		byte[] decodedBytes = Base64.getDecoder().decode(getConfigValue("pwrd"));
		return new String(decodedBytes);
	}

}
