package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.ArgusAllWeatherTankProductPage;
import pageObjects.HomePage;
import resources.Driver;

public class ProductReviewTC {

	public WebDriver driver;
	HomePage homePage;
	ArgusAllWeatherTankProductPage argusAllWeatherTankProductPage;

	@BeforeTest
	public void setUp() throws IOException {
		driver = Driver.initializeDriver();
		driver.get(Driver.properties.getProperty("url"));
	}

	@Test
	public void productReview() {
		homePage = new HomePage(driver);
		argusAllWeatherTankProductPage = new ArgusAllWeatherTankProductPage(driver);

		homePage.clickOnArgusAllWeatherTankLink();
		argusAllWeatherTankProductPage.clickOnReviewTabLink();
		argusAllWeatherTankProductPage.clickOnFiveStars();
		argusAllWeatherTankProductPage.enterNickname(Driver.properties.getProperty("nickname"));
		argusAllWeatherTankProductPage.enterReviewSummary(Driver.properties.getProperty("reviewSummary"));
		argusAllWeatherTankProductPage.enterReviewText(Driver.properties.getProperty("reviewText"));
		argusAllWeatherTankProductPage.clickOnSubmitReviewButton();
		argusAllWeatherTankProductPage.assertConfirmationMessagePresent();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
