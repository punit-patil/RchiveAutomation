package co.rchive.pages.readerpage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class AddAnnotations {
	private WebDriver driver;
	private Actions action;

	@FindBy(xpath = "(//div[@id='first-doc2'])[1]")
	private WebElement sid;

	@FindBy(css = ".action-bar>h4")
	private WebElement sid_Name;

	@FindBy(id = "read")
	private WebElement btn_Read;

	@FindBy(xpath = ".//*[@id='annotationsToolbar']/div[1]")
	private WebElement highlight_annotation;

	@FindBy(xpath = ".//*[@id='annotationsToolbar']/div[2]")
	private WebElement createNote_annotation;

	@FindBy(xpath = ".//*[@id='toolbar_documentViewer_annotations_flexpaper_notetypeselector_point']")
	private WebElement createNotePoint;

	@FindBy(xpath = ".//*[@id='toolbar_documentViewer_annotations_flexpaper_notetypeselector_area']")
	private WebElement createNoteArea;

	@FindBy(css = ".flexpaper_textarea_contenteditable")
	private WebElement textareaContent;

	@FindBy(xpath = ".//*[@id='toolbar_documentViewer_annotations_flexpaper_colorselector_orange']")
	private WebElement highlight_color;

	@FindBy(xpath = ".//*[@id='documentViewer_dummyPageCanvas_0']")
	private WebElement page0;

	@FindBy(xpath = ".//*[@id='documentViewer_dummyPageCanvas_1']")
	private WebElement page1;

	@FindBy(xpath = ".//*[@id='page_1_documentViewer_canvasOverlay']")
	private WebElement clickPage1;

	@FindBy(xpath = ".//*[@id='documentViewerpage_1_word_19']")
	private WebElement source;

	@FindBy(xpath = ".//*[@id='documentViewerpage_1_word_91']")
	private WebElement destination;

	@FindBy(xpath = ".//*[@id='documentViewerpage_1_word_123']")
	private WebElement centerword;

	@FindBy(xpath = ".//*[@id='toolbar_documentViewer']/img[11]")
	private WebElement nextpage;

	@FindBy(xpath = ".//*[@id='documentViewerpage_0_word_34']")
	private WebElement deleteHighlight;

	@FindBy(xpath = ".//*[@id='annotationsToolbar']/div[5]")
	WebElement deleteicon;

	@FindBy(xpath = ".//*[@id='shareframe']/img")
	private WebElement readerShare;

	@FindBy(xpath = ".//*[@id='flex_manage']/a/img")
	private WebElement readerManageAccess;

	@FindBy(xpath = ".//*[@id='shownotification']/img")
	private WebElement readerNotification;

	@FindBy(xpath = ".//*[@id='flex_privacySetting']/a/img")
	private WebElement readerPrivacySettings;

	@FindBy(xpath = ".//*[@id='flex_annotation_download']/img")
	private WebElement readerDownloadWithAnnotations;

	@FindBy(xpath = ".//*[@id='privacy']/h2")
	private WebElement readerPrivacyHeader;

	@FindBy(xpath = ".//*[@id='invitees']")
	private WebElement readerInvitedusers;

	@FindBy(xpath = ".//*[@id='invitedr']/center/input[1]")
	private WebElement privacyChangeYesButton;

	@FindBy(xpath = ".//*[@id='shareframe']/img")
	WebElement readersharebutton;

	@FindBy(xpath ="html/body/div[3]")
	WebElement readersharepopupheader;
	
	@FindBy(xpath=".//*[@id='txtreciever']")
	WebElement tofield;
	
	@FindBy(id="crossLogoToReturnToScreenplayPage")
	private WebElement closeReaderPage;

	public AddAnnotations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void addHighlightAnnotation() {
	//	nextpage.click();

		highlight_annotation.click();

		action = new Actions(driver);
		action.clickAndHold(source).moveToElement(destination).release(destination).build().perform();
		// action.moveToElement(deleteHighlight).click();
	}

	public void deleteHighlightedAnnotation() {
		action = new Actions(driver);
		action.clickAndHold(source).moveToElement(destination).release(destination).build().perform();
		// action.moveToElement(deleteHighlight).click();

		new Wait(driver).waitForElementToBeVisible(deleteicon, 15);

		deleteicon.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void addCreateNotePointAnnotation() {
		action = new Actions(driver);
		nextpage.click();
		new Wait(driver).sleepFor(100);
		createNote_annotation.click();
		createNotePoint.click();
		// page1.click();
		action.moveToElement(centerword).build().perform();
		action.click();
		new Wait(driver).sleepFor(1000);

	}

	public void addCreateNoteArea(String textArea) {
		action = new Actions(driver);
		createNote_annotation.click();
		createNoteArea.click();
		action.clickAndHold(source).moveToElement(destination).release(destination).build().perform();
		textareaContent.click();
		textareaContent.sendKeys(textArea);
		new Wait(driver).sleepFor(100);
		// deleteicon.click();
		// Alert alert = driver.switchTo().alert();
		// alert.accept();
	}

	public void deleteCreateNoteArea() {
		deleteicon.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public void addCreateNoteText(String noteText) {
		action = new Actions(driver);
		createNote_annotation.click();
		action.clickAndHold(source).moveToElement(destination).release(destination).build().perform();
		textareaContent.click();
		textareaContent.sendKeys(noteText);
		new Wait(driver).sleepFor(1000);

	}

	public void deleteCreateNoteText() {
		deleteicon.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public boolean isPrivacySettingFromReaderPageChangeInvited(String privacyPopHeader) {
		boolean isPrivacy;
		readerPrivacySettings.click();
		isPrivacy = readerPrivacyHeader.getText().equalsIgnoreCase(privacyPopHeader);
		readerInvitedusers.click();
		privacyChangeYesButton.click();
		return isPrivacy;
	}

	public boolean isSharePopUpReader(String sharePopHeader) {
		boolean isReaderSharePopup=true;
		readersharebutton.click();
		
	
		 System.out.println(readersharepopupheader.getText());
		//isReaderSharePopup = readersharepopupheader.getText().equalsIgnoreCase(sharePopHeader);
		
		tofield.click();
		tofield.sendKeys(" ");
		return isReaderSharePopup;
	}
	
	public void closeReaderPage(){
		closeReaderPage.click();
	}

}
