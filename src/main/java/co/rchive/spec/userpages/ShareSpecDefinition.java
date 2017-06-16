package co.rchive.spec.userpages;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
//import java.util.Properties;

import org.openqa.selenium.WebDriver;

import co.rchive.pages.userpages.ActivityLogPage;
import co.rchive.pages.userpages.SharePage;
import co.rchive.spec.beforelogin.BeforeLoginSpecDefinition;
//import co.rchive.test.testbase.TestBase;

public class ShareSpecDefinition {
	private SharePage share;
	private ActivityLogPage activty;
	private BeforeLoginSpecDefinition loginSpec;
	private DashboardSpecDefinition dashboardSpec;
	private PrivacySpecDefinition privacySpec;
	private ActivityLogSpecDefinition activitylog;
//	TestBase tb = new TestBase();
//	Properties prop = tb.getpropValues();

	public ShareSpecDefinition(WebDriver driver) {
		loginSpec = new BeforeLoginSpecDefinition(driver);
		// dashboardSpec = new DashboardSpecDefinition(driver);
		share = new SharePage(driver);
		activty=new ActivityLogPage(driver);
		privacySpec = new PrivacySpecDefinition(driver);
		activitylog=new ActivityLogSpecDefinition(driver);
	}

	public void loginToRchiveWithEmail(String email, String pasword) {
		dashboardSpec = loginSpec.loginWithEmail(email, pasword);
	}

	public void selectScreenPlay() {
		dashboardSpec.selectScreenplay();
	}

	public void goToShareWindowAndShareToConnectedUser(String connectedUser,String ActivityLog,String emailId,String activiMessage) {
	//	 String[] privacyPermission = 
		share.getCheckedPermissionInPrivacy();
		activitylog.clickOnMoreAndSelectActivityLog(ActivityLog);
	//	Assert.assertTrue(activty.checkUserActivityInActivityLog(prop.getProperty("email"), "privacy settings updated to invited reviewers"));
		Assert.assertTrue(activty.checkUserActivityInActivityLog(emailId,activiMessage));
		dashboardSpec.selectScreenplay();
		dashboardSpec.openShareMenu();
		Assert.assertTrue(share.isSharePage());
	//	assertTrue(share.verifyPrivacyPermissions(privacyPermission));
		share.shareToConnectedUser(connectedUser);
		Assert.assertTrue(share.checkPopUpMsgAfterConShare());
		share.closeShareWindow();
		dashboardSpec.selectScreenplay();
		activitylog.clickOnMoreAndSelectActivityLog(ActivityLog);
		
		
		
	}

	public void goToShareWindowAndSmartShare(String user,String fname,String lname,String email,String activiMessage) {
		//String[] privacyPermission = 
		share.getCheckedPermissionInPrivacy();
		activitylog.clickOnMoreAndSelectActivityLog("activity log");
	//	Assert.assertTrue(activty.checkUserActivityInActivityLog(prop.getProperty("email"), "privacy settings updated to invited reviewers"));
		Assert.assertTrue(activty.checkUserActivityInActivityLog(email,activiMessage));
		dashboardSpec.selectScreenplay();
		dashboardSpec.openShareMenu();
		Assert.assertTrue(share.isSharePage());
		//assertTrue(share.verifyPrivacyPermissions(privacyPermission));
		share.smartShare(user,fname,lname);
		Assert.assertTrue(share.checkPopUpMsgAfterSmartShare());
		share.closeShareWindow();
		dashboardSpec.selectScreenplay();
		activitylog.clickOnMoreAndSelectActivityLog("activity log");
		//dashboardSpec.logoutFromRchive();
	}

	public void shareSidForFirstTime() {
		dashboardSpec.openShareMenu();
		dashboardSpec.handleSidIsPrivateAlert();
		privacySpec.goToPrivacyLevelMenu();
		privacySpec.changePrivacyToEveryone();
		privacySpec.goToDashboard();
		dashboardSpec.selectScreenplay();
	}
}
