package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import resources.User;
import resources.UserFactory;

public class RegistrationTests extends BaseTest {

	private HomePage homePage;
	private RegistrationPage registrationPage;
	private AccountPage accountPage;
	private User user;

	@BeforeMethod
	void pageObjectSetUp() throws InterruptedException {
		homePage = new HomePage(driver);
		registrationPage = new RegistrationPage(driver);
		accountPage = new AccountPage(driver);
		homePage.clickOnRegistrationLink();
		Thread.sleep(1000);
	}

	@Test
	void registerWithoutAnyFieldsFilledTest() {
		registrationPage.register();
		registrationPage.assertAllMandatoryFields();
	}

	@Test
	void registerWithAllMandatoryFieldsFilledTest() {
		user = UserFactory.createUser(false);

		registrationPage.register(user);
		accountPage.assertHeadingnPresent();
	}

	@Test
	void registerWithAllMandatoryFieldsFilledAndSubscriptionTest() {
		user = UserFactory.createUser(true);

		registrationPage.register(user);
		accountPage.assertHeadingnPresent();
	}

	@Test
	void emailFieldValidationTest() {
		user = UserFactory.createUser(false);

		registrationPage.validateEmailField(user, false);
		registrationPage.validateEmailField(user, true);
	}

	@Test
	void passwordLengthValidationTest() {
		user = UserFactory.createUser(false);

		registrationPage.validateLengthInPasswordField(user, "Has less than 8 characters");
		registrationPage.validateLengthInPasswordField(user, "Has 8 characters");
		registrationPage.validateLengthInPasswordField(user, "Has more than 8 characters");
	}

	@Test
	void passwordMatchingValidationTest() {
		user = UserFactory.createUser(false);

		registrationPage.validateMatchingPassword(user, false);
		registrationPage.validateMatchingPassword(user, true);
	}

	@Test
	void passwordStrengthMeterValidationTest() {
		user = UserFactory.createUser(true);

		registrationPage.validatePasswordStrengthMeterIsWorking(user, "Weak");
		registrationPage.validatePasswordStrengthMeterIsWorking(user, "Strong");
		registrationPage.validatePasswordStrengthMeterIsWorking(user, "Very strong");
	}
}
