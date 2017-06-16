package co.rchive.spec.adminpage;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.adminpage.AdminDashboardPage;
import co.rchive.pages.adminpage.AdminDeletedUsersPage;
import co.rchive.pages.adminpage.AdminHomePage;
import co.rchive.pages.adminpage.AdminWritersPage;

public class AdminSpecDefinitionPage {
	AdminHomePage adminHomePage;
	AdminDashboardPage adminDashboardPage;
	AdminWritersPage adminWritersPage;
	AdminDeletedUsersPage adminDeletedUsersPage;

	public AdminSpecDefinitionPage(WebDriver driver) {
		adminHomePage = new AdminHomePage(driver);
		adminDashboardPage = new AdminDashboardPage(driver);
		adminWritersPage = new AdminWritersPage(driver);
		adminDeletedUsersPage = new AdminDeletedUsersPage(driver);
	}

	public boolean loginToAdmin(String adminUserName, String adminPasword) {

		Assert.assertTrue(adminHomePage.isAdminPage());
		adminDashboardPage = adminHomePage.adminLoginAction(adminUserName, adminPasword);
		// assertTrue(adminDashboardPage.isAdminDashboardPage());
		return adminDashboardPage.isAdminDashboardPage();
	}

	public void goToWritersPage() {
		adminWritersPage = adminDashboardPage.goToAdminWritersPage();
		Assert.assertTrue(adminWritersPage.isAdminWritersPage());
	}

	public boolean removeWriterInUsers(final String writerEmail) {
		boolean deletedUser = false;
		if (adminWritersPage.searchWriterInUsersByEmail(writerEmail)) {
			adminWritersPage.delWriterAction(writerEmail);
			deletedUser = true;
		}
		return deletedUser;
	}

	public void goToDeletedUsersPage() {
		adminDeletedUsersPage = adminDashboardPage.goToAdminDeletedUsersPage();
		Assert.assertTrue(adminDeletedUsersPage.isAdminDeletedUsersPage());
	}

	public void removeWriterInDeletedUsers(final String writerEmail) {
		adminDeletedUsersPage.searchInDeletedUsersByEmailAndDelete(writerEmail);
	}

	public void logoutAdmin() {
		adminDashboardPage.logoutAdmin();
		Assert.assertTrue(adminHomePage.isAdminPage());
	}
}
