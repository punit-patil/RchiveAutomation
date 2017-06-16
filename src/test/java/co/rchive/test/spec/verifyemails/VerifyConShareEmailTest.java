package co.rchive.test.spec.verifyemails;

import java.util.Properties;

import org.testng.annotations.Test;
import co.rchive.spec.verifyemails.VerifyEmailSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class VerifyConShareEmailTest extends TestBase{
//	TestBase tb=new TestBase();
	Properties prop=getpropValues();

	public VerifyConShareEmailTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("gmail_url"));
	}
	
	@Test
	public void verifyEmailForConShare() {
		VerifyEmailSpecDefinition user=  new VerifyEmailSpecDefinition(driver);
		user.loginToUserEmail(prop.getProperty("connected_user"),prop.getProperty("connected_user_pass"));
		user.verifyRchiveConShareMail();
		user.logOutUserEmail();
	}

}
