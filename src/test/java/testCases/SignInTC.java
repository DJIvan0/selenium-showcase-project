package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SignInPage;
import resources.Driver;

public class SignInTC {
	
	public WebDriver driver;
	HomePage homePage;
	SignInPage signInPage;
	
	@BeforeTest
	public void setUp() throws IOException {
		driver = Driver.initializeDriver();
		driver.get(Driver.properties.getProperty("url"));
	}
	
	@Test
	public void signIn() {
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
		
		homePage.clickOnSignInLink();
		signInPage.enterEmailAddress(Driver.properties.getProperty("email"));
		signInPage.enterPassword(Driver.properties.getProperty("password"));
		signInPage.clickOnSignInButton();
		homePage.assertWelcomeMessagePresent();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}


}
