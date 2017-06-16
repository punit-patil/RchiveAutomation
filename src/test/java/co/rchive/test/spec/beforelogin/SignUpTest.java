package co.rchive.test.spec.beforelogin;

//import static org.junit.Assert.assertTrue;
import org.testng.Assert;
import java.util.Properties;

//import org.junit.Test;

import org.testng.annotations.Test;

import co.rchive.spec.beforelogin.BeforeLoginSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class SignUpTest extends TestBase {
	// TestBase tb;
	Properties prop;

	public SignUpTest() {
		super();
		// tb=new TestBase();
		prop = getpropValues();

		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void SignUpWithEmail() {
		BeforeLoginSpecDefinition user = new BeforeLoginSpecDefinition(driver);
		boolean signUpVerified = user.signUpWithEmail(prop.getProperty("fname"), prop.getProperty("lname"),
				prop.getProperty("email"), prop.getProperty("password"));
		Assert.assertTrue(signUpVerified);

	}

}
