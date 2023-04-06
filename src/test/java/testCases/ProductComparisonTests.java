package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.FusionBackpackProductPage;
import pageObjects.HomePage;
import pageObjects.ProductComparePage;

public class ProductComparisonTests extends BaseTest {
	
	private HomePage homePage;
	private ProductComparePage productComparePage;
	private FusionBackpackProductPage fusionBackpackProductPage;
	
	@BeforeMethod
	void pageObjectSetUp() throws InterruptedException {

		homePage = new HomePage(driver);
		fusionBackpackProductPage = new FusionBackpackProductPage(driver);
		productComparePage= new ProductComparePage(driver);
		homePage.clickOnFusionBackpackLink();
		Thread.sleep(1000);
	}
	
	@Test
	void addingProductToComparisonListAndViewingFromConfirmationMessageLinkTest() {
		fusionBackpackProductPage.addToProductComparisonAndView(false);
		productComparePage.verifyaddToCartButtonPresent();
	}
	
	@Test
	void addingProductToComparisonListAndViewingFromHeaderLinkTest() {
		fusionBackpackProductPage.addToProductComparisonAndView(true);
		productComparePage.verifyaddToCartButtonPresent();
	}
	
	@Test
	void removeProductFromComparisonListTest() {
		fusionBackpackProductPage.addToProductComparisonAndView(true);
		productComparePage.removeProductFromComparisonList();
	}
}
