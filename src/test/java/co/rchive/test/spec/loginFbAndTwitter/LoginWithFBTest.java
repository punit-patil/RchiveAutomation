package co.rchive.test.spec.loginFbAndTwitter;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.loginFbAndTwitter.LoginWithFbSpecDefinition;
import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class LoginWithFBTest extends TestBase {
	//TestBase tb = new TestBase();
	Properties prop = getpropValues();

	LoginWithFBTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void loginWithFBandDashboardLinksTest() {
		LoginWithFbSpecDefinition loginfbUser = new LoginWithFbSpecDefinition(driver);
		DashboardSpecDefinition dbs=new DashboardSpecDefinition(driver);
		loginfbUser.loginWithFB(prop.getProperty("fb_email"), prop.getProperty("fb_password"));
		dbs.allLinksOfDashboard();

	}

}
