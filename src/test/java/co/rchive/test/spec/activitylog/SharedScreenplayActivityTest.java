package co.rchive.test.spec.activitylog;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.ActivityLogSpecDefinition;
import co.rchive.spec.userpages.ShareSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class SharedScreenplayActivityTest extends TestBase {
	TestBase tb = new TestBase();
	Properties prop = tb.getpropValues();

	SharedScreenplayActivityTest() {
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void sharedScreenTest() {
		ShareSpecDefinition shareUser = new ShareSpecDefinition(driver);
		shareUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		ActivityLogSpecDefinition act = new ActivityLogSpecDefinition(driver);
		act.selectScreenplay("the beatle..");
		act.clickOnMoreAndSelectActivityLog("activity log");
		act.activityOfUser("rchivesanity2@gmail.com","review invitation sent");
	}

}
