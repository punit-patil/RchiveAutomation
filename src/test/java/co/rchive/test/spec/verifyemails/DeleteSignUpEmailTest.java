package co.rchive.test.spec.verifyemails;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.verifyemails.VerifyEmailSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class DeleteSignUpEmailTest extends TestBase {
	TestBase tb = new TestBase();
	Properties prop = tb.getpropValues();

	public DeleteSignUpEmailTest() {
		super();

		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("gmail_url"));
	}

	@Test
	public void deleteEmailforSignUp() {
		VerifyEmailSpecDefinition user = new VerifyEmailSpecDefinition(driver);
		user.loginToUserEmail(prop.getProperty("email"), prop.getProperty("password_gmail"));
		user.deleteRequiredEmail();
		user.logOutUserEmail();
		// user.verifyRchiveWelcomeMailAndDelEmail();
	}

}
