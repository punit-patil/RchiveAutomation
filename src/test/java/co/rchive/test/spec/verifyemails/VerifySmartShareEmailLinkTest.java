package co.rchive.test.spec.verifyemails;

import java.util.Properties;

import org.testng.annotations.Test;
import co.rchive.spec.verifyemails.VerifyEmailSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class VerifySmartShareEmailLinkTest extends TestBase {
	//TestBase tb=new TestBase();
	Properties prop=getpropValues();

	public VerifySmartShareEmailLinkTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("gmail_url"));
	}

	@Test
	public void verifySmartShareLink() {
		VerifyEmailSpecDefinition user = new VerifyEmailSpecDefinition(driver);
		user.loginToUserEmail(prop.getProperty("smartshare_user"),prop.getProperty("smartshare_user_pass"));
		user.verifyRchiveSmartShareMail();
		user.logOutUserEmail();
	}
}
