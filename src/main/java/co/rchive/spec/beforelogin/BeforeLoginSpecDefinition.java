package co.rchive.spec.beforelogin;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import co.rchive.pages.beforeloginpages.HomePage;
import co.rchive.pages.beforeloginpages.SignUpPage;
import co.rchive.spec.userpages.DashboardSpecDefinition;
//import co.rchive.test.testbase.TestBase;

public class BeforeLoginSpecDefinition {
	private WebDriver driver;
	HomePage homePage;
	SignUpPage signUpPage;
	DashboardSpecDefinition dashboardSpec;
	Properties prop;
	//TestBase tb;

	public BeforeLoginSpecDefinition(WebDriver driver) {
		this.driver = driver;
		homePage = new HomePage(this.driver);
	}

	public DashboardSpecDefinition loginWithEmail(String userEmail, String pasword) {
		Assert.assertTrue(homePage.isHomePage());
		dashboardSpec = homePage.loginAction(userEmail, pasword);
		dashboardSpec.verifyScreenplayTableDisplayed();
		return new DashboardSpecDefinition(driver);
	}

	public boolean signUpWithEmail(String fname,String lname,String email,String pass) {
	//	tb = new TestBase();
		Assert.assertTrue(homePage.isHomePage());
		signUpPage = homePage.goToSignUpPage();
	//	prop = tb.getpropValues();
	//	Assert.assertTrue(signUpPage.isSignUpPage(prop.getProperty("sign_up")));
	//	signUpPage.signUpAction(prop.getProperty("fname"), prop.getProperty("lname"), prop.getProperty("email"),
	//			prop.getProperty("password"));
		
		signUpPage.signUpAction(fname, lname, email, pass);
		
		// assertTrue(signUpPage.isSignUpCnfrm());
		return signUpPage.isSignUpCnfrm();
	}
}
