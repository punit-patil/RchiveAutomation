package co.rchive.test.spec.loginFbAndTwitter;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.loginFbAndTwitter.LoginWithTwitterSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class LoginWithTwitterTest extends TestBase {
	//TestBase tb = new TestBase();
	Properties prop = getpropValues();

	LoginWithTwitterTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void twitterLogin() {
		LoginWithTwitterSpecDefinition logintwitter = new LoginWithTwitterSpecDefinition(driver);

		logintwitter.loginWithTwitter(prop.getProperty("twitter_email"), prop.getProperty("twitter_password"));
	}
}
