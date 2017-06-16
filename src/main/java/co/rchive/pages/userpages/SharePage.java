package co.rchive.pages.userpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.spec.userpages.PrivacySpecDefinition;
import co.rchive.util.Wait;

public class SharePage {
	private WebDriver driver;

	@FindBy(xpath = "//div[@id='file-doc-detail']/div/div[1]/h4")
	private WebElement heading_Share;

	@FindBy(id = "txtreciever")
	private WebElement txtbx_ToField;

	@FindBy(id = "share_submit")
	private WebElement btn_Share;

	@FindBy(xpath = "//div[starts-with(@id,'as-results-')]/ul/li")
	private List<WebElement> usersResultList;

	@FindBy(id = "readonline")
	private WebElement checkbx_Read;

	@FindBy(id = "downloadfile")
	private WebElement checkbx_Download;

	@FindBy(xpath = ".//*[@id='success_error_msg']/span")
	private WebElement textPopUp;

	@FindBy(css = "#newuser_div>label")
	private WebElement newUserMsg;

	@FindBy(id = "fname")
	private WebElement newUserFName;

	@FindBy(id = "lname")
	private WebElement newUserLName;

	@FindBy(xpath = ".//*[@id='back']")
	private WebElement closeshare;

	@FindBy(css = ".user-name")
	private WebElement signOutUser;

	@FindBy(id = "myTabDrop1")
	private WebElement moreOption;

	public SharePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isSharePage() {
		new Wait(driver).waitForElementToBeVisible(heading_Share, 6);
		return heading_Share.getText().equals("Share");
	}

	public void shareToConnectedUser(String emailToShare) {
		new Wait(driver).waitForElementToBeClickable(txtbx_ToField, 10);
		txtbx_ToField.click();
		txtbx_ToField.sendKeys(" ");

		new Wait(driver).waitForElementToBeVisible(usersResultList.get(0), 6);
		if (!(usersResultList.get(0).getText().trim().equals("No Results Found"))) {
			for (int i = 0; i < usersResultList.size(); i++) {
				WebElement userCard = driver.findElement(By.xpath("//li[@id='as-result-item-" + i + "']/div/span[2]"));
				String userEmailInResult = userCard.getText();
				if (userEmailInResult.equalsIgnoreCase(emailToShare)) {
					driver.findElement(By.xpath("//li[@id='as-result-item-" + i + "']/div")).click();
					btn_Share.click();
					// new Wait(driver).waitForElementToBeVisible(textPopUp,
					// 2000);
					// closeshare.click();
					// new Wait(driver).waitForElementToBeClickable(signOutUser,
					// 500);
					break;
				}
			}
		} else {
			System.out.println("no connected user(s) found");
		}
	}

	public void smartShare(String emailToShare, String fName, String Lname) {
		boolean flag = false;
		new Wait(driver).waitForElementToBeClickable(txtbx_ToField, 10);
		txtbx_ToField.click();
		// txtbx_ToField.sendKeys(emailToShare);
		txtbx_ToField.sendKeys(" ");
		new Wait(driver).waitForElementToBeVisible(usersResultList.get(0), 7);
		if (!(usersResultList.get(0).getText().trim().equals("No Results Found"))) {
			for (int i = 0; i < usersResultList.size(); i++) {
				WebElement userCard = driver.findElement(By.xpath("//li[@id='as-result-item-" + i + "']/div/span[2]"));
				String userEmailInResult = userCard.getText();
				if (userEmailInResult.equalsIgnoreCase(emailToShare)) {
					driver.findElement(By.xpath("//li[@id='as-result-item-" + i + "']/div")).click();
					btn_Share.click();
					flag = true;
					new Wait(driver).waitForElementToBeVisible(textPopUp, 10);
					// new Wait(driver).waitForElementToBeVisible(closeshare,
					// 8);

					// closeshare.click();
					break;
				}

			}
			if (flag == false) {
				txtbx_ToField.sendKeys(Keys.BACK_SPACE);
				txtbx_ToField.sendKeys(emailToShare);
				txtbx_ToField.sendKeys(Keys.ENTER);
				if (newUserFName.isEnabled() && newUserLName.isEnabled()) {
					newUserFName.sendKeys(fName);
					newUserLName.sendKeys(Lname);
					btn_Share.click();
					new Wait(driver).waitForElementToBeVisible(textPopUp, 8);
					closeshare.click();
				}

			}
		}

		// new Wait(driver).waitForElementToBeVisible(newUserMsg, 3);

	}

	public boolean checkPopUpMsgAfterConShare() {
		boolean textFlag = false;
		new Wait(driver).waitForElementToBeVisible(textPopUp, 20);
		String shareMsg = textPopUp.getText();
		if (shareMsg.equals("one or more invitation already invited")) {
			textFlag = true;
		} else if (shareMsg
				.equals("Invitation sent.  To manage this invitation, please visit the Manage Access page")) {
			textFlag = true;
		}
		return textFlag;
	}

	public void closeShareWindow() {
		new Wait(driver).sleepFor(2000);
		closeshare.click();
		driver.navigate().refresh();
		// new Wait(driver).waitForElementToBeClickable(moreOption, 5000);
	}

	public boolean verifyPrivacyPermissions(String[] privacyPermission) {
		boolean flag = false;
		if ((privacyPermission[0] != null && privacyPermission[0].equals("invited"))
				&& (checkbx_Read.isSelected() && checkbx_Download.isSelected())) {
			flag = true;
		} else if (privacyPermission[0].equals("unchecked")) {
			flag = true;
		} else if (checkbx_Read.isSelected() || checkbx_Download.isSelected()) {
			if (privacyPermission[0] != null && privacyPermission[0].equals("everyone_read")) {
				flag = checkbx_Read.isSelected();
			}
			if (privacyPermission[1] != null && privacyPermission[1].equals("everyone_download")) {
				flag = checkbx_Download.isSelected();
			}
		}
		return flag;
	}

	public boolean checkPopUpMsgAfterSmartShare() {
		new Wait(driver).waitForElementToBeVisible(textPopUp, 3);
		String shareMsg = textPopUp.getText();
		return shareMsg.equals("SmartShare invite sent.");
	}

	public void getCheckedPermissionInPrivacy() {
		PrivacySpecDefinition privacy = new PrivacySpecDefinition(driver);
		privacy.goToPrivacyLevelMenu();
		privacy.closePrivacyWindow();
		// String[] return privacy.checkedPermissionInPrivacyAndCloseMenu();
	}

}
