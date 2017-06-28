package co.rchive.pages.userpages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.rchive.pages.beforeloginpages.HomePage;
//import co.rchive.spec.userpages.MyConnectionSpecDefinition;
import co.rchive.spec.userpages.PrivacySpecDefinition;
import co.rchive.spec.userpages.ReaderSpecDefinition;
import co.rchive.spec.userpages.ShareSpecDefinition;
import co.rchive.util.Wait;

public class DashBoardPage {

	private WebDriver driver;

	@FindBy(id = "grid-small")
	private WebElement sidTable;

	@FindBy(className = "")
	private WebElement btn_Dashboard;

	@FindBy(css = "#header_profile")
	private WebElement btn_Settings;

	@FindBy(xpath = "html/body/main/div[1]/div/div[4]/div/ul[2]/li[2]/ul/li[3]/a")
	private WebElement btn_Logout;

	@FindBy(xpath = "(//div[@id='first-doc2'])[1]")
	private WebElement sid;

	@FindBy(css = ".action-bar>h4")
	private WebElement sid_Name;

	@FindBy(id = "read")
	private WebElement btn_Read;

	@FindBy(css = ".fileUpload.btn.submit-bg3")
	private WebElement btn_Upload;

	@FindBy(id = "screenplay_info")
	private WebElement sid_Info;

	@FindBy(xpath = "//*[@id='myTab']/li[2]/a")
	private WebElement link_Share;

	@FindBy(id = "shareForm")
	private WebElement shareForm;

	@FindBy(id = "share_submit")
	private WebElement btn_Share;

	@FindBy(xpath = "//*[@id='myTab']/li[3]/a")
	private WebElement link_ManageAcsess;

	@FindBy(xpath = "//*[@id='myTab']/li[5]/a")
	private WebElement link_Privacy;

	@FindBy(id = "myTabDrop1")
	private WebElement link_More;

	@FindBy(xpath = "//*[@id='myTab']/li[5]/ul/li[1]/a")
	private WebElement link_ActivityLog;

	@FindBy(xpath = "//*[@id='myTab']/li[5]/ul/li[2]/a")
	private WebElement link_Reviews;

	@FindBy(css = ".custom-drop-z>h3")
	private WebElement fileDropZone;

	@FindBy(xpath = "//div[1]/div/div[4]/div/ul[2]/li[1]/a/i")
	private WebElement notificationbell;

	@FindBy(css = "p.task-details")
	private WebElement notificationDetails;

	@FindBy(xpath = "//tbody/tr[1]/td[3]/a")
	private WebElement sharedWithMePageRead;

	// @FindBy(tagName="a")
	List<WebElement> allLinks;

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean checkSidTable() {
		new Wait(driver).waitForElementToBeVisible(sidTable, 25);
		return sidTable.isDisplayed();
	}

	public HomePage logOut() {
		// new Wait(driver).waitForElementToBeVisible(btn_Settings, 40);
		// close.click();
		new Wait(driver).waitForElementToBeClickable(btn_Settings, 5);
		btn_Settings.click();
		// new Wait(driver).waitForElementToBeClickable(btn_Logout, 5);

		btn_Logout.click();
		return new HomePage(driver);
	}

	// need to refactor the code
	public boolean selectSid() {
		boolean sidNameFlag = false;
		new Wait(driver).waitForElementToBeClickable(sid, 20);
		if (sid.isDisplayed()) {
			sid.click();
			sidNameFlag = true;
			// String name = sid.getAttribute("title");
			// new Wait(driver).waitForElementToBeVisible(sid_Name, 8);
			// sidNameFlag = name.equalsIgnoreCase(sid_Name.getText());
			// System.out.println("screenplay selected -" + sid_Name.getText());
		}
		return sidNameFlag;
	}

	public ReaderSpecDefinition clickReadToOpenInReader() {
		new Wait(driver).waitForElementToBeVisible(sid_Info, 8);
		if (sid_Info.isDisplayed()) {
			new Wait(driver).waitForElementToBeClickable(btn_Read, 6);
			String sidUrl = btn_Read.getAttribute("href");
			btn_Read.click();
			System.out.println(sidUrl.equals(driver.getCurrentUrl()) + " -" + sidUrl);
		}
		return new ReaderSpecDefinition(driver);
	}

	public ShareSpecDefinition openSharePopUp() {
		new Wait(driver).waitForElementToBeVisible(sid_Info, 8);
		if (sid_Info.isDisplayed()) {
			new Wait(driver).waitForElementToBeClickable(link_Share, 6);
			link_Share.click();
		}
		return new ShareSpecDefinition(driver);
	}

	public void openManageAccessPopUp() {
		new Wait(driver).waitForElementToBeVisible(sid_Info, 8);
		if (sid_Info.isDisplayed()) {
			new Wait(driver).waitForElementToBeClickable(link_ManageAcsess, 6);
			link_ManageAcsess.click();
		}
	}

	public PrivacySpecDefinition openPrivacyLevelPopUp() {
		new Wait(driver).waitForElementToBeVisible(sid_Info, 8);
		if (sid_Info.isDisplayed()) {
			new Wait(driver).waitForElementToBeClickable(link_Privacy, 6);
			link_Privacy.click();
		}
		return new PrivacySpecDefinition(driver);
	}

	public void openActivityLogPopUp() {
		new Wait(driver).waitForElementToBeVisible(sid_Info, 8);
		if (sid_Info.isDisplayed()) {
			link_More.click();
			new Wait(driver).waitForElementToBeClickable(link_ActivityLog, 6);
			link_ActivityLog.click();
		}
	}

	public void openUserReviewPopUp() {
		new Wait(driver).waitForElementToBeVisible(sid_Info, 8);
		if (sid_Info.isDisplayed()) {
			link_More.click();
			new Wait(driver).waitForElementToBeClickable(link_Reviews, 6);
			link_Reviews.click();
		}
	}

	public void uploadSid(String fileAbsolutePath) {
		new Wait(driver).waitForElementToBeClickable(btn_Upload, 15);
		btn_Upload.click();
		new Wait(driver).waitForElementToBeClickable(fileDropZone, 6);
		fileDropZone.click();

		new Wait(driver).sleepFor(5000);
		StringSelection selection = new StringSelection(fileAbsolutePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		try {
			Robot robot = new Robot();

			// press control+v
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			// release control+v
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			new Wait(driver).sleepFor(3000);
			// press enter,release enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("uploaded file -" + fileAbsolutePath);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void handleSidIsPrivateAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public DashBoardPage readSharedScreenplayThroughNotification() {
		new Wait(driver).waitForElementToBeClickable(notificationbell, 15);

		notificationbell.click();
		String split[] = notificationDetails.getText().split("\"");
		System.out.println(split[0]);
		System.out.println(split[1]);
		if (split[0].trim().endsWith("review")) {
			System.out.println(notificationDetails.getText());
			notificationDetails.click();
			sharedWithMePageRead.click();
			new Wait(driver).sleepFor(15000);
		}

		return new DashBoardPage(driver);
	}

	public boolean checkAllLinksInDashboard() {
		boolean validLinks = false;
		allLinks = driver.findElements(By.tagName("a"));
		String[] linkTests = new String[allLinks.size()];
		System.out.println(allLinks.size());
		int i = 0;
		for (WebElement e : allLinks) {
			if (!e.getText().trim().equals("")) {
				System.out.println(e.getText());
				linkTests[i] = e.getText();
				i++;
			}
		}

		for (String dashboardLinks : linkTests) {
			if (dashboardLinks != null) {
				driver.findElement(By.linkText(dashboardLinks)).click();
				driver.navigate().back();
				validLinks = true;
			}
		}
		return validLinks;
	}

}
// upload msg - /html/body/main/div[1]/div/div[4]/div/div[2]/p
