package co.rchive.test.spec.share;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.ActivityLogSpecDefinition;
import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.spec.userpages.ShareSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class ShareToConnectedUserTest extends TestBase {
	// TestBase tb = new TestBase();
	Properties prop = getpropValues();

	public ShareToConnectedUserTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void shareToConnectedUser() {
		ShareSpecDefinition shareUser = new ShareSpecDefinition(driver);
		ActivityLogSpecDefinition activitylog = new ActivityLogSpecDefinition(driver);
		DashboardSpecDefinition dash = new DashboardSpecDefinition(driver);

		shareUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		shareUser.selectScreenPlay();
		shareUser.goToShareWindowAndShareToConnectedUser(prop.getProperty("connected_user"), prop.getProperty("email"),
				prop.getProperty("privacyUpdatedInvitedUsers"),prop.getProperty("activityLog"));
		activitylog.activityOfUser(prop.getProperty("connected_user"), "review invitation sent");
		dash.logoutFromRchive();
	}
}
