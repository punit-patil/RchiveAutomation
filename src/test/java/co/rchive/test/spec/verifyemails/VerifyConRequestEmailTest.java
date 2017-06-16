package co.rchive.test.spec.verifyemails;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.verifyemails.VerifyEmailSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class VerifyConRequestEmailTest extends TestBase {
	TestBase tb=new TestBase();
	Properties prop=tb.getpropValues();

	public VerifyConRequestEmailTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("gmail_url"));
	}
	
	@Test
	public void verifyEmailForConRequest() {
		VerifyEmailSpecDefinition user=  new VerifyEmailSpecDefinition(driver);
		user.loginToUserEmail(prop.getProperty("connected_user"),prop.getProperty("connected_user_pass"));
		user.verifyRchiveConRequestMail();
		user.logOutUserEmail();
	}

}
