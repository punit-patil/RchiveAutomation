package co.rchive.spec.userpages;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.userpages.DashBoardPage;
import co.rchive.spec.beforelogin.BeforeLoginSpecDefinition;

public class DashboardSpecDefinition {
	private DashBoardPage dashboard;
	private BeforeLoginSpecDefinition loginSpec;
	

	public DashboardSpecDefinition(WebDriver driver) {
		this.loginSpec = new BeforeLoginSpecDefinition(driver);
		dashboard = new DashBoardPage(driver);
		
	}
	

	public void loginToRchiveWithEmail(String email, String pasword) {
		loginSpec.loginWithEmail(email, pasword);
	}

	public void logoutFromRchive() {
		dashboard.logOut();
	}
	
	public void readConSharedScreenplay(){
		dashboard.readSharedScreenplayThroughNotification();
	}

	public void verifyScreenplayTableDisplayed() {
		Assert.assertTrue(dashboard.checkSidTable());
	}

	public void selectScreenplay() {
		Assert.assertTrue(dashboard.selectSid());
	}

	public ShareSpecDefinition openShareMenu() {
		return dashboard.openSharePopUp();
	}

	public void openManageAccessMenu() {
		dashboard.openManageAccessPopUp();
	}

	public PrivacySpecDefinition openPrivacyLevelMenu() {
		return dashboard.openPrivacyLevelPopUp();
	}

	public void uploadSid(String absoluteFilePath) {
		dashboard.uploadSid(absoluteFilePath);
		Assert.assertTrue(dashboard.selectSid());
	}

	public void handleSidIsPrivateAlert() {
		dashboard.handleSidIsPrivateAlert();
	}
	public void allLinksOfDashboard(){
		Assert.assertTrue(dashboard.checkAllLinksInDashboard());
	}

	
}
