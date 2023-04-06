package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.User;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	private JavascriptExecutor je = (JavascriptExecutor) driver;
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	Actions action = new Actions(driver);

	@FindBy(how = How.ID, using = "firstname")
	private WebElement firstNameInputField;

	@FindBy(how = How.ID, using = "lastname")
	private WebElement lastNameInputField;

	@FindBy(how = How.ID, using = "email_address")
	private WebElement emailInputField;

	@FindBy(how = How.ID, using = "password")
	private WebElement passwordInputField;

	@FindBy(how = How.ID, using = "password-confirmation")
	private WebElement confirmPasswordInputField;

	@FindBy(how = How.ID, using = "is_subscribed")
	private WebElement newsletterSubscriptionCheckbox;

	@FindBy(how = How.XPATH, using = "//form[@id='form-validate']//button")
	private WebElement createAnAccountButton;

	@FindBy(how = How.ID, using = "firstname-error")
	private WebElement firstNameErrorMessageText;

	@FindBy(how = How.ID, using = "lastname-error")
	private WebElement lastNameErrorMessageText;

	@FindBy(how = How.ID, using = "email_address-error")
	private WebElement emailErrorMessageText;

	@FindBy(how = How.ID, using = "password-error")
	private WebElement passwordErrorMessageText;

	@FindBy(how = How.ID, using = "password-confirmation-error")
	private WebElement confirmPasswordErrorMessageText;

	@FindBy(how = How.ID, using = "password-strength-meter-label")
	private WebElement passwordStrengthMeterLabel;

	public void register() {
		scrollToElements(createAnAccountButton);
		createAnAccountButton.click();
	}

	public void register(User user) {
		if (!user.getFirstName().isEmpty()) {
			firstNameInputField.sendKeys(user.getFirstName());
		}

		if (!user.getLastName().isEmpty()) {
			lastNameInputField.sendKeys(user.getLastName());
		}

		if (!user.getEmail().isEmpty()) {
			emailInputField.sendKeys(user.getEmail());
		}

		if (!user.getPassword().isEmpty()) {
			passwordInputField.sendKeys(user.getPassword());
		}

		if (!user.getConfirmPassword().isEmpty()) {
			confirmPasswordInputField.sendKeys(user.getConfirmPassword());
		}

		if (user.getSubscription()) {
			newsletterSubscriptionCheckbox.click();
		}

		createAnAccountButton.click();
	}

	public void validateEmailField(User user, boolean validFormat) {
		if (validFormat) {
			emailInputField.sendKeys(user.getEmail());
			scrollToElements(createAnAccountButton);
			je.executeScript("arguments[0].click();", createAnAccountButton);
			Assert.assertTrue(!emailErrorMessageText.isDisplayed());
			emailInputField.clear();
		} else {
			emailInputField.sendKeys(user.getEmail().replace("@", ""));
			scrollToElements(createAnAccountButton);
			je.executeScript("arguments[0].click();", createAnAccountButton);
			Assert.assertEquals(emailErrorMessageText.getText(),
					"Please enter a valid email address (Ex: johndoe@domain.com).");
			emailInputField.clear();
		}
	}

	public void validateLengthInPasswordField(User user, String formatType) {
		String actualMessage = "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
		if (formatType.equalsIgnoreCase("Has less than 8 characters")) {
			passwordInputField.sendKeys(user.getPassword().substring(0, 7));
			passwordInputField.click();
			Assert.assertEquals(passwordErrorMessageText.getText(), actualMessage);
			passwordInputField.clear();
		} else if (formatType.equalsIgnoreCase("Has 8 characters")) {
			passwordInputField.sendKeys(user.getPassword().substring(0, 8));
			Assert.assertTrue(!passwordErrorMessageText.getText().equalsIgnoreCase(actualMessage));
			passwordInputField.clear();
		} else if (formatType.equalsIgnoreCase("Has more than 8 characters")) {
			passwordInputField.sendKeys(user.getPassword());
			Assert.assertTrue(!passwordErrorMessageText.getText().equalsIgnoreCase(actualMessage));
			passwordInputField.clear();
		}
	}

	public void validateMatchingPassword(User user, boolean matching) {
		if (matching) {
			passwordInputField.sendKeys(user.getPassword());
			user.setConfirmPassword(user.getPassword());
			confirmPasswordInputField.sendKeys(user.getConfirmPassword());
			scrollToElements(createAnAccountButton);
			je.executeScript("arguments[0].click();", createAnAccountButton);
			Assert.assertTrue(!confirmPasswordErrorMessageText.isDisplayed());
			passwordInputField.clear();
			confirmPasswordInputField.clear();
		} else {
			passwordInputField.sendKeys(user.getPassword());
			confirmPasswordInputField.sendKeys(user.getConfirmPassword() + ".");
			scrollToElements(createAnAccountButton);
			je.executeScript("arguments[0].click();", createAnAccountButton);
			Assert.assertEquals(confirmPasswordErrorMessageText.getText(), "Please enter the same value again.");
			passwordInputField.clear();
			confirmPasswordInputField.clear();
		}
	}

	public void validatePasswordStrengthMeterIsWorking(User user, String strength) {
		if (strength.equalsIgnoreCase("Weak")) {
			passwordInputField.sendKeys(user.getPassword());
			Assert.assertEquals(passwordStrengthMeterLabel.getText(), "Weak");
			Assert.assertTrue(passwordStrengthMeterLabel.isDisplayed());
			passwordInputField.clear();
		} else if (strength.equalsIgnoreCase("Strong")) {
			passwordInputField.sendKeys(user.getPassword().substring(0, 8).concat("A"));
			Assert.assertEquals(passwordStrengthMeterLabel.getText(), "Strong");
			Assert.assertTrue(passwordStrengthMeterLabel.isDisplayed());
			passwordInputField.clear();
		} else if (strength.equalsIgnoreCase("Very strong")) {
			passwordInputField.sendKeys(user.getPassword().concat("A!."));
			Assert.assertEquals(passwordStrengthMeterLabel.getText(), "Very Strong");
			Assert.assertTrue(passwordStrengthMeterLabel.isDisplayed());
			passwordInputField.clear();
		}
	}

	public void assertAllMandatoryFields() {
		String actualMessage = "This is a required field.";
		scrollToElements(firstNameErrorMessageText);
		Assert.assertEquals(firstNameErrorMessageText.getText(), actualMessage);
		Assert.assertEquals(lastNameErrorMessageText.getText(), actualMessage);
		Assert.assertEquals(emailErrorMessageText.getText(), actualMessage);
		Assert.assertEquals(passwordErrorMessageText.getText(), actualMessage);
		Assert.assertEquals(confirmPasswordErrorMessageText.getText(), actualMessage);
	}

	public void scrollToElements(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
