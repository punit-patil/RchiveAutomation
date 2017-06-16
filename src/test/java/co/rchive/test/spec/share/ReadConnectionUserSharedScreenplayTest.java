package co.rchive.test.spec.share;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.spec.userpages.ShareSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class ReadConnectionUserSharedScreenplayTest extends TestBase {
	//TestBase tb = new TestBase();
	Properties prop = getpropValues();

	public ReadConnectionUserSharedScreenplayTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void ReadConUserSharedScreen() {
		ShareSpecDefinition spec = new ShareSpecDefinition(driver);
		spec.loginToRchiveWithEmail(prop.getProperty("email_accept_request"), prop.getProperty("pass_accept_request"));
		DashboardSpecDefinition dashspec=new DashboardSpecDefinition(driver);
		dashspec.readConSharedScreenplay();
	}

}
