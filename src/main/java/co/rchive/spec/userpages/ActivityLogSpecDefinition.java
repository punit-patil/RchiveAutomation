package co.rchive.spec.userpages;

//import static org.junit.Assert.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.userpages.ActivityLogPage;

public class ActivityLogSpecDefinition {
	private ActivityLogPage activity;

	public ActivityLogSpecDefinition(WebDriver driver) {
		activity = new ActivityLogPage(driver);
	}

	public void selectScreenplay(String selectScreenply) {
		Assert.assertTrue(activity.selectScreenplayFromDashboard(selectScreenply));
	}

	public void clickOnMoreAndSelectActivityLog(String activitylog) {
		activity.selectActivityLogFromMoreDropDown(activitylog);
	}

	public void activityOfUser(String useremail, String useractivity) {
		Assert.assertTrue(activity.checkUserActivityInActivityLog(useremail, useractivity));
	}

}
