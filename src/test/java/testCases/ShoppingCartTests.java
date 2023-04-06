package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HeroHoodieProductPage;
import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;

public class ShoppingCartTests extends BaseTest{
	
	private HomePage homePage;
	private HeroHoodieProductPage heroHoodieProductPage;
	private ShoppingCartPage shoppingCartPage;
	
	@BeforeMethod
	void pageObjectSetUp() throws InterruptedException {
		homePage = new HomePage(driver);
		heroHoodieProductPage = new HeroHoodieProductPage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
		homePage.clickOnHeroHoodieLink();
		Thread.sleep(1000);
	}
	
	@Test
	void addingToCartWithoutAnyFieldsFilledTest() {
		heroHoodieProductPage.addToCart();
		heroHoodieProductPage.assertAllMandatoryFields();
	}
	
	@Test
	void addingToCartWithAllMandatoryFieldsFilledTest() {
		heroHoodieProductPage.addToCart(properties.getProperty("productSize"), properties.getProperty("productColor"),properties.getProperty("productQuantity"));
		shoppingCartPage.verifyProceedToCheckoutButtonPresent();
	}
}
