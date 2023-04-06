package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver;
	public Properties properties;

	@BeforeMethod
	void setUp() throws IOException {

		properties = new Properties();

		String propertyPath = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\data.properties";
		FileInputStream fileInputStream = new FileInputStream(propertyPath);
		properties.load(fileInputStream);
		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("IE")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--headless");
			driver = new EdgeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get(properties.getProperty("url"));
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {
		driver.quit();
	}
}
