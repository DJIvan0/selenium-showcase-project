package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.ArgusAllWeatherTankProductPage;
import pageObjects.HomePage;

public class ProductReviewTests extends BaseTest {
	
	private HomePage homePage;
	private ArgusAllWeatherTankProductPage argusAllWeatherTankProductPage;
	
	@BeforeMethod
	void pageObjectSetUp() throws InterruptedException {

		homePage = new HomePage(driver);
		argusAllWeatherTankProductPage = new ArgusAllWeatherTankProductPage(driver);
		homePage.clickOnArgusAllWeatherTankLink();
		Thread.sleep(1000);
	}
	
	@Test
	void leavingAReviewWithAllMandatoryFieldsFilledTest() {
		argusAllWeatherTankProductPage.leaveAReview(properties.getProperty("nickname"),properties.getProperty("summary"),properties.getProperty("review"));
	}
	
	@Test
	void leavingAReviewWithoutAnyFieldsFilledTest() {
		argusAllWeatherTankProductPage.leaveAReview();
		argusAllWeatherTankProductPage.assertAllMandatoryFields();
	}
}
