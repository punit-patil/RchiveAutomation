package co.rchive.test.spec.verifyemails;



import java.util.Properties;

import org.testng.annotations.Test;


import co.rchive.spec.verifyemails.VerifyEmailSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class VerifySignUpEmailLinkTest extends TestBase {
	// TestBase tb;
	// Properties prop;
	// TestBase tb = new TestBase();
	Properties prop = getpropValues();

	public VerifySignUpEmailLinkTest() {
		super();

		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("gmail_url"));
	}

	@Test
	public void verifyEmailforSignUp() {
		VerifyEmailSpecDefinition user = new VerifyEmailSpecDefinition(driver);
		user.loginToUserEmail(prop.getProperty("email"), prop.getProperty("password_gmail"));

		user.clickSignUpLink();

		user.logOutUserEmail();
		// user.verifyRchiveWelcomeMailAndDelEmail();
	}

}
