package co.rchive.test.spec.adminpage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import co.rchive.spec.adminpage.AdminSpecDefinitionPage;
import co.rchive.test.testbase.AppBrowser;

public class AdminTestBase {

	protected WebDriver browser;
	protected AdminSpecDefinitionPage admin;
	protected ExtentReports extentReport;
	protected ExtentTest extentTest;

	Properties prop = null;
	InputStream input = null;

	@BeforeSuite
	public void beforeSuite() {
//		extentReport = new ExtentReports("G://MyExtentReport.html", true);
//		extentReport.loadConfig(new File("F://rchiveAutomation_TestNG//extent-config.xml"));
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

	@BeforeMethod
	public void setUp(Method method) {
		admin = new AdminSpecDefinitionPage(browser);
		prop = getpropValues();
		extentReport = new ExtentReports("G://MyExtentReport.html",true);
		extentReport.loadConfig(new File("F://rchiveAutomation_TestNG//extent-config.xml"));
		extentTest = extentReport.startTest((this.getClass().getSimpleName() + "::" + method.getName()),
				method.getName());
		browser = new AppBrowser("firefox").getDriver();
		browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.get(prop.getProperty("admin_url"));
		String currentUrl = browser.getCurrentUrl();
		extentTest.log(LogStatus.INFO, "url of browser::" + currentUrl);

	}
			
	@AfterMethod
	public void tearDown() {
		browser.close();
		extentTest.log(LogStatus.INFO, "browser closed");
		extentReport.endTest(extentTest);
	}

	@AfterSuite
	public void afterSuite() {
		extentReport.flush();
		extentReport.close();
	}

}
