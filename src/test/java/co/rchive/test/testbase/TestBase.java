package co.rchive.test.testbase;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.junit.Before;
import org.openqa.selenium.WebDriver;

//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;


import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


//import org.testng.annotations.Parameters;

public class TestBase {
	protected WebDriver driver;
	protected Properties prop;
	protected FileInputStream input;
	String browser;
	String baseUrl;

	protected ExtentReports extentReport;
	protected ExtentTest extentTest;

	protected String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	protected String getBaseURL() {
		return baseUrl;
	}

	public void setBaseURL(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	// in all subclasses set url and browser
	// previously throwing error bcoz of using @BeforeClass



	@BeforeTest
	public void setUp() {
		// driver=new FirefoxDriver();
		driver = new AppBrowser(browser).getDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
		
	}

	// @After
	@AfterTest
	public void tearDown() {
		driver.close();
	}

	
	public Properties getpropValues() {

		try {
			input = new FileInputStream("details.properties");
			prop = new Properties();
			prop.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

}
