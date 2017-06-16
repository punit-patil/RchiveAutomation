package co.rchive.test.spec.share;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.ActivityLogSpecDefinition;
import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.spec.userpages.ShareSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class SmartShareTest extends TestBase {
	// TestBase tb = new TestBase();
	Properties prop = getpropValues();

	public SmartShareTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void smartShare() {
		ShareSpecDefinition shareUser = new ShareSpecDefinition(driver);
		ActivityLogSpecDefinition activitylog = new ActivityLogSpecDefinition(driver);
		DashboardSpecDefinition dash = new DashboardSpecDefinition(driver);
		shareUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		shareUser.selectScreenPlay();
		shareUser.goToShareWindowAndSmartShare(prop.getProperty("smartshare_user"),
				prop.getProperty("smartshare_fname"), prop.getProperty("smartshare_lname"), prop.getProperty("email"),
				prop.getProperty("privacyUpdatedInvitedUsers"));

		activitylog.activityOfUser(prop.getProperty("smartshare_user"), prop.getProperty("smartshare_activity"));

		dash.logoutFromRchive();
	}

}
