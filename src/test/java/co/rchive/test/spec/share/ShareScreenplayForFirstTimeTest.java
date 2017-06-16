package co.rchive.test.spec.share;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.ShareSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class ShareScreenplayForFirstTimeTest extends TestBase {
	// TestBase tb=new TestBase();
	Properties prop = getpropValues();

	public ShareScreenplayForFirstTimeTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void shareScreenplayForFirstTime() {
		ShareSpecDefinition shareUser = new ShareSpecDefinition(driver);
		shareUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		shareUser.selectScreenPlay();
		shareUser.shareSidForFirstTime();
		shareUser.goToShareWindowAndSmartShare(prop.getProperty("smartshare_user"),
				prop.getProperty("smartshare_fname"), prop.getProperty("smartshare_lname"), prop.getProperty("email"),
				prop.getProperty("privacyUpdatedInvitedUsers"));

	}

}
