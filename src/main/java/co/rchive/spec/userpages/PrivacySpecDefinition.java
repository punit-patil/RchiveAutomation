package co.rchive.spec.userpages;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.userpages.PrivacyLevelPage;
import co.rchive.spec.beforelogin.BeforeLoginSpecDefinition;

public class PrivacySpecDefinition {
	private BeforeLoginSpecDefinition loginSpec;
	private DashboardSpecDefinition dashboardSpec;
	private PrivacyLevelPage privacy;

	public PrivacySpecDefinition(WebDriver driver) {
		loginSpec = new BeforeLoginSpecDefinition(driver);
		dashboardSpec = new DashboardSpecDefinition(driver);
		privacy = new PrivacyLevelPage(driver);
	}

	public void loginToRchiveWithEmail(String email, String pasword) {
		dashboardSpec = loginSpec.loginWithEmail(email, pasword);
	}

	public void selectScreenPlay() {
		dashboardSpec.selectScreenplay();
	}

	public void goToPrivacyLevelMenu() {
		dashboardSpec.openPrivacyLevelMenu();
		Assert.assertTrue(privacy.isPrivacyLevelPage());
		privacy.changePrivacyToInvited();
	}

	public String[] checkedPermissionInPrivacyAndCloseMenu() {
		String[] result = privacy.checkedPermissionInPrivacy();
		privacy.closePrivacyWindow();
		return result;
	}
	
	public void changePrivacyToInvited() {
		Assert.assertTrue(privacy.changePrivacyToInvited());
	}
	
	public void changePrivacyToEveryone() {
		Assert.assertTrue(privacy.changePrivacyToEveryone());
	}
	
	public void closePrivacyWindow() {
		privacy.closePrivacyWindow();
	}
	
	public void goToDashboard() {
		privacy.goToDashboard();
	}
}
