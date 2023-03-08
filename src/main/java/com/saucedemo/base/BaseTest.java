package com.saucedemo.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.saucedemo.utils.DataProviderUtils;

public abstract class BaseTest extends BasePage {

	protected static final Logger log = LogManager.getLogger(BaseTest.class);

	protected BaseTest() {}

	@BeforeClass
	@Parameters("browser")
	public void initialize(@Optional("edge") String browser) {
		setup(browser);
		log.info("~~~~~ Browser initialized.");
	}

	@DataProvider
	public Object[][] getTestData() {
		log.info("~~~~~ Getting test data from excel.");
		return DataProviderUtils.getExcelDatas(this.getClass().getSimpleName().split("_")[0]);
	}

	@AfterClass
	public void finish() {
		tearDown();
		log.info("~~~~~ Browser closed.");
	}

}
