package co.rchive.test.spec.beforelogin;

import java.util.Properties;

//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import co.rchive.pages.beforeloginpages.HomePage;
import co.rchive.spec.beforelogin.BeforeLoginSpecDefinition;
import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class LoginWithEmailTest extends TestBase {
	TestBase tb = new TestBase();
	Properties prop = tb.getpropValues();

	public LoginWithEmailTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	// @Parameters({"loginEmail","loginPasword"})
	@Test
	public void loginWithEmail() {
		BeforeLoginSpecDefinition loginUser = new BeforeLoginSpecDefinition(driver);
		loginUser.loginWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		DashboardSpecDefinition dash = new DashboardSpecDefinition(driver);
		dash.logoutFromRchive();
	}

}
