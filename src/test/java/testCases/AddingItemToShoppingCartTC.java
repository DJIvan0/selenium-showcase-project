package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HeroHoodieProductPage;
import pageObjects.HomePage;
import resources.Driver;

public class AddingItemToShoppingCartTC {

	public WebDriver driver;
	HomePage homePage;
	HeroHoodieProductPage heroHoodieProductPage;

	@BeforeTest
	public void setUp() throws IOException {
		driver = Driver.initializeDriver();
		driver.get(Driver.properties.getProperty("url"));
	}

	@Test
	public void addingItemToShoppingCart() {
		homePage = new HomePage(driver);
		heroHoodieProductPage = new HeroHoodieProductPage(driver);

		homePage.clickOnHeroHoodieLink();
		heroHoodieProductPage.clickOnSizeLOption();
		heroHoodieProductPage.clickOnColorBlackOption();
		heroHoodieProductPage.clickOnAddToCartButton();
		heroHoodieProductPage.assertConfirmationMessagePresent();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
