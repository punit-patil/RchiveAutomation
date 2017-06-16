package co.rchive.spec.userpages;

//import static org.junit.Assert.assertTrue;
//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.readerpage.AddAnnotations;
import co.rchive.pages.readerpage.ReadScreenplay;
import co.rchive.pages.userpages.DashBoardPage;
import co.rchive.pages.userpages.ReaderPage;

public class ReaderSpecDefinition {
	ReaderPage reader;
	private ReadScreenplay readpage;
	private AddAnnotations addannotations;
	private DashBoardPage dashboard;

	public ReaderSpecDefinition(WebDriver driver) {
		reader = new ReaderPage(driver);
		readpage = new ReadScreenplay(driver);
		addannotations = new AddAnnotations(driver);
		dashboard = new DashBoardPage(driver);
	}

	public void openReaderPageOfScreenplay() {
		Assert.assertTrue(dashboard.checkSidTable());
		readpage.readScreenplay();

	}

	public void addHighlight() {
		addannotations.addHighlightAnnotation();
	}

	public void deleteHighlight() {
		addannotations.deleteHighlightedAnnotation();
	}

	public void createNote(String noteText) {
		addannotations.addCreateNoteArea(noteText);
		addannotations.deleteCreateNoteArea();
		addannotations.addCreateNoteText(noteText);
		addannotations.deleteCreateNoteText();
		addannotations.addCreateNotePointAnnotation();
	}

	public void readerPrivacySettingsToInvitedUsers(String privacyPopHeader) {
		Assert.assertTrue(addannotations.isPrivacySettingFromReaderPageChangeInvited(privacyPopHeader));

	}
	
	public void shareScreenplayFromReader(String sharePopHeader){
		Assert.assertTrue(addannotations.isSharePopUpReader(sharePopHeader));
		
	}
	public void CloseTheReaderPage(){
		addannotations.closeReaderPage();
	}

}
