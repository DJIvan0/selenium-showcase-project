package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.FusionBackpackProductPage;
import pageObjects.HomePage;
import pageObjects.PushItMessengerBagProductPage;
import resources.Driver;

public class ProductComparisonTC {

	public WebDriver driver;
	HomePage homePage;
	FusionBackpackProductPage fusionBackpackProductPage;
	PushItMessengerBagProductPage pushItMessengerBagProductPage;

	@BeforeTest
	public void setUp() throws IOException {
		driver = Driver.initializeDriver();
		driver.get(Driver.properties.getProperty("url"));
	}

	@Test
	public void productComparison() {
		homePage = new HomePage(driver);
		fusionBackpackProductPage = new FusionBackpackProductPage(driver);
		pushItMessengerBagProductPage = new PushItMessengerBagProductPage(driver);

		homePage.clickOnFusionBackpackLink();
		fusionBackpackProductPage.clickOnAddToCompareLink();
		fusionBackpackProductPage.clickOnHomePageLink();
		homePage.clickOnPushItMessengerBagLink();
		pushItMessengerBagProductPage.clickOnAddToCompareLink();
		pushItMessengerBagProductPage.clickOnHomePageLink();
		homePage.clickOnCompareProductsPageLink();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
