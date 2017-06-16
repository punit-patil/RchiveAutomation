package co.rchive.test.spec.adminpage;

import java.util.Properties;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.rchive.spec.adminpage.AdminSpecDefinitionPage;


public class DeleteWritersTest extends AdminTestBase {
	Properties prop = null;
	AdminSpecDefinitionPage admin1;

	@Test
	public void delWriterTest() {
		prop = getpropValues();
		admin1 = new AdminSpecDefinitionPage(browser);
		admin1.loginToAdmin(prop.getProperty("admin_name"), prop.getProperty("admin_pass"));
		admin1.goToWritersPage();

		if (admin1.removeWriterInUsers(prop.getProperty("remove_user"))) {
			extentTest.log(LogStatus.PASS, "user deleted successfully");
		} else {
			extentTest.log(LogStatus.FAIL, "failed to delete user");
		}
		admin1.goToDeletedUsersPage();
		admin1.removeWriterInDeletedUsers(prop.getProperty("remove_user"));
		admin1.logoutAdmin();
	}
}
