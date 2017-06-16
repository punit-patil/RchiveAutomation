package co.rchive.test.spec.uploadscreenplay;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class UploadSidTest extends TestBase {

//	TestBase tb = new TestBase();
	Properties prop = getpropValues();

	public UploadSidTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void uploadSid() {
		DashboardSpecDefinition uploadUser = new DashboardSpecDefinition(driver);
		uploadUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		uploadUser.uploadSid(prop.getProperty("pdf_path"));
		uploadUser.logoutFromRchive();
	}
}
