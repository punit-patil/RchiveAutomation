package co.rchive.test.testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppBrowser {
	private WebDriver driver;
	private String browserType;

	public AppBrowser(final String driverType) {
		this.browserType = driverType.toUpperCase();
	}

	public WebDriver getDriver() {

		switch (browserType) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver",
					"E:\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "FIREFOX":
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("driver not loaded...");
			break;
		}
		return driver;
	}
}
