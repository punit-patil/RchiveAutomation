package co.rchive.test.spec.adminpage;

//import static org.junit.Assert.assertTrue;
import org.testng.Assert;
import java.util.Properties;

//import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

//import co.rchive.pages.adminpage.AdminHomePage;
import co.rchive.spec.adminpage.AdminSpecDefinitionPage;

public class AdminLoginTest extends AdminTestBase {
	Properties prop = getpropValues();

	@Test
	public void adminLoginTest() {
		AdminSpecDefinitionPage admin1 = new AdminSpecDefinitionPage(browser);
		// AdminHomePage hm=new AdminHomePage(browser);

		boolean TestAdminLogin = admin1.loginToAdmin(prop.getProperty("admin_name"), prop.getProperty("admin_pass"));
		Assert.assertTrue(TestAdminLogin);

		if (TestAdminLogin) {
			extentTest.log(LogStatus.PASS, "AdminLoginTest passed");
		} else {
			extentTest.log(LogStatus.FAIL, "AdminLoginTest failed");
		}

		// hm.adminLoginAction(prop.getProperty("admin_name"),prop.getProperty("admin_pass"));
		admin1.logoutAdmin();
	}

}
