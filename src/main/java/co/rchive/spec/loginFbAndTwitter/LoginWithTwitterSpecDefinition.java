package co.rchive.spec.loginFbAndTwitter;

//import static org.junit.Assert.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.beforeloginpages.HomePage;
import co.rchive.spec.userpages.DashboardSpecDefinition;

public class LoginWithTwitterSpecDefinition {
	private WebDriver driver;
	HomePage homepage;
	DashboardSpecDefinition dashboardSpec;

	public LoginWithTwitterSpecDefinition(WebDriver driver) {
		super();
		homepage = new HomePage(driver);
		this.driver = driver;
	}

	public DashboardSpecDefinition loginWithTwitter(String twitteremail, String password) {
		Assert.assertTrue(homepage.isHomePage());
		dashboardSpec = homepage.loginTOTwitter(twitteremail, password);
		dashboardSpec.logoutFromRchive();
		return new DashboardSpecDefinition(driver);
	}

}
