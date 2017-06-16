package co.rchive.test.spec.readerpage;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.ActivityLogSpecDefinition;
import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.spec.userpages.ReaderSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class ReaderPageTest extends TestBase {
	//TestBase tb = new TestBase();
	Properties prop = getpropValues();

	ReaderPageTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void readScreenplay() {
		DashboardSpecDefinition uploadUser = new DashboardSpecDefinition(driver);
		ReaderSpecDefinition readerpage = new ReaderSpecDefinition(driver);
		ActivityLogSpecDefinition activitylog=new ActivityLogSpecDefinition(driver);
		uploadUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		readerpage.openReaderPageOfScreenplay();
		readerpage.addHighlight();
		readerpage.deleteHighlight();
		readerpage.createNote("Hello user");
		readerpage.CloseTheReaderPage();
		activitylog.selectScreenplay("The beatle..");
		activitylog.clickOnMoreAndSelectActivityLog("activity log");
		activitylog.activityOfUser(prop.getProperty("email"),"deleted note on page 2");
		
		uploadUser.logoutFromRchive();
	//	readerpage.readerPrivacySettingsToInvitedUsers("privacy setting");

	}
/*	@Test
	public void shareScreenplayFromReaderPage(){
		DashboardSpecDefinition uploadUser = new DashboardSpecDefinition(driver);
		ReaderSpecDefinition readerpage = new ReaderSpecDefinition(driver);
		uploadUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		readerpage.openReaderPageOfScreenplay();
		readerpage.shareScreenplayFromReader("share");
		
	}*/

}
