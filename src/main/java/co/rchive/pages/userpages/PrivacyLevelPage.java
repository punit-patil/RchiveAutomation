package co.rchive.pages.userpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class PrivacyLevelPage {
	private WebDriver driver;

	@FindBy(id = "radio1")
	private WebElement radio_Private;

	@FindBy(id = "radio2")
	private WebElement radio_Invited;

	@FindBy(id = "radio3")
	private WebElement radio_Everyone;

	@FindBy(xpath = "//input[@type='checkbox' and @disabled='disabled']")
	private List<WebElement> invited_chkbx_ReadAndDownload;

	@FindBy(id = "everyvisible")
	private WebElement everyone_chkbox_Read;

	@FindBy(id = "everydownload")
	private WebElement everyone_chkbox_Download;

	@CacheLookup
	@FindBy(xpath = "//div[@id='file-doc-detail_c']/div[1]/h4")
	private WebElement heading_privacy;

	@FindBy(xpath = ".//*[@id='file-doc-detail_c']/div[1]/div/a/i")
	private WebElement closePopUp;

	@FindBy(xpath = "//div[@id='invited']/button[1]")
	private WebElement btn_YesForInvited;

	@FindBy(xpath = "//div[@id='public']/button[1]")
	private WebElement btn_YesForEveryone;

	@FindBy(xpath = ".//*[@id='success_error_msg']/span")
	private WebElement textPopUp;
	
	@FindBy(css = ".menu-icon.glyphicon.glyphicon-book")
	WebElement sideBarLink_MyDocuments;

	public PrivacyLevelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isPrivacyLevelPage() {
		return heading_privacy.getText().equals("Privacy Settings");
	}

	public void closePrivacyWindow() {
		//driver.navigate().refresh();
		closePopUp.click();
	}

	public String[] checkedPermissionInPrivacy() {
		String[] checkedPrmisions = new String[2];

		if (radio_Invited.isSelected()) {
			checkedPrmisions[0] = "invited";
		} else if (radio_Everyone.isSelected()) {
			if (everyone_chkbox_Read.isSelected()) {
				checkedPrmisions[0] = "everyone_read";
			}
			if (everyone_chkbox_Download.isSelected()) {
				checkedPrmisions[1] = "everyone_download";
			}
			if (checkedPrmisions[0] == null && checkedPrmisions[1] == null) {
				checkedPrmisions[0] = "unchecked";
			}
		}
		return checkedPrmisions;
	}

	public boolean changePrivacyToInvited() {
		radio_Invited.click();
		btn_YesForInvited.click();
		new Wait(driver).waitForElementToBeVisible(textPopUp, 1000);
		String PrivacyUpdtMsg = textPopUp.getText();
		new Wait(driver).sleepFor(1000);
		System.out.println(PrivacyUpdtMsg);
		return PrivacyUpdtMsg.equals(
				"Privacy settings updated.Your Screenplay is accessible to invited reviewers only");
	}

	public boolean changePrivacyToEveryone() {
		radio_Everyone.click();
		everyone_chkbox_Read.click();
		btn_YesForEveryone.click();
		new Wait(driver).waitForElementToBeVisible(textPopUp, 3);
		String PrivacyUpdtMsg = textPopUp.getText();
		System.out.println(PrivacyUpdtMsg);
		return PrivacyUpdtMsg.equals(
				"Privacy settings updated. Please enable View and Download settings to allow users to review your screenplay.");
	}

	public void goToDashboard() {
		new Wait(driver).waitForElementToBeClickable(sideBarLink_MyDocuments, 8);
		sideBarLink_MyDocuments.click();
	}
}
