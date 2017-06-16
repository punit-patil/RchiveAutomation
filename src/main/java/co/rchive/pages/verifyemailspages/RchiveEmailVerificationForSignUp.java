package co.rchive.pages.verifyemailspages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class RchiveEmailVerificationForSignUp {
	private WebDriver driver;

	@FindBy(id = "identifierId")
	private WebElement txtbx_Email;

	@FindBy(xpath = ".//*[@id='password']/div/div/div[1]/input")
	private WebElement txtbx_Paswd;

	@FindBy(xpath = ".//*[@id='identifierNext']")
	private WebElement btn_Next;
	
	@FindBy(id="passwordNext")
	private WebElement passwordNext;

	@FindBy(id = "PersistentCookie")
	private WebElement chk_StaySignedIn;

	@FindBy(id = "signIn")
	private WebElement btn_SignIn;

	@FindBy(id = "gb_71")
	private WebElement btn_SignOut;

	@FindBy(xpath = ".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")
	private WebElement btn_Setings;

	@FindBy(xpath = "//div[@class='z0']/div")
	private WebElement btn_Compose;

	@FindBy(xpath = "//div[1]/table/tbody//div/table[2]/tbody//div[1]/div/p/a")
	private WebElement link_Verification;

	@FindBy(xpath = ".//*[@id='message']/div/div/div[2]")
	private WebElement accountVerified;

	@FindBy(css = ".adn.ads")
	protected WebElement mesageContent;

	private List<WebElement> emails;

	@FindBy(xpath = "//div[@id=':5']/div[2]/div[1]/div/div[2]/div[3]")
	private WebElement btn_DelThisMsg;

	public RchiveEmailVerificationForSignUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean checkGmailLoginPane() {
		new Wait(driver).waitForElementToBeVisible(txtbx_Email, 15);
		return txtbx_Email.isDisplayed();
	}

	public void gmailLoginAction(String userName, String pasword) {
		new Wait(driver).waitForElementToBeClickable(txtbx_Email, 15);

		txtbx_Email.click();
		txtbx_Email.sendKeys(userName);
		System.out.println(btn_Next.getText());
		btn_Next.click();

		new Wait(driver).waitForElementToBeClickable(txtbx_Paswd, 10);
		txtbx_Paswd.click();
		txtbx_Paswd.sendKeys(pasword);
		passwordNext.click();
	//	chk_StaySignedIn.click();
	//	btn_SignIn.click();
	}

	public void logout() {
		new Wait(driver).waitForElementToBeVisible(btn_Setings, 20);
		btn_Setings.click();
		btn_SignOut.click();
	}

	public boolean isInboxPage() {
		new Wait(driver).waitForElementToBeVisible(btn_Compose, 20);
		return btn_Compose.isDisplayed() && btn_Setings.isDisplayed();
	}

	public boolean verifySignUpRegisterLink(String senderName, String subject, String emailLinkConent,
			String verifiedPopupText) {
		/*
		 * emails = driver.findElements(By
		 * .xpath("//div[@id=':2h']/div[2]/div/table/tbody/tr"));
		 * 
		 * // iterate mails for (WebElement mail : emails) { // fetch mail
		 * sender and subject WebElement sender = mail.findElement(By
		 * .xpath(".//td[4]/div[2]/span")); WebElement subj =
		 * mail.findElement(By .xpath(".//td[6]/div/div/div/span[1]")); //
		 * assert particulars and open mail if
		 * (sender.getText().equalsIgnoreCase(senderName) &&
		 * subj.getText().equalsIgnoreCase(subject)) { mail.click(); break; } }
		 */
		boolean flag = this.verifyRchiveWelcomeEmail(senderName, subject);
		if (flag) {
			String parentWindow = driver.getWindowHandle();
			flag = false;
			new Wait(driver).waitForElementToBeVisible(mesageContent, 10);
			// click the link to activate Rchive account
			if (link_Verification.getAttribute("href").contains(emailLinkConent)) {
				link_Verification.click();

				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				flag = this.isRequiredUrlLoadedInNewWindow(parentWindow, verifiedPopupText);
				driver.switchTo().window(parentWindow);
			}

		}
		return flag;
	}

	protected boolean isRequiredUrlLoadedInNewWindow(String parentWindow, String popupVerify) {
		// store winHandles names in a set
		Set<String> winHandles = driver.getWindowHandles();
		if (winHandles.size() > 0) {
			for (String childWindow : driver.getWindowHandles()) {
				// switch to newly opened Rchive window
				if (!childWindow.equals(parentWindow)) {
					driver.switchTo().window(childWindow);
					System.out.println(accountVerified.getText());

					break;
				}
			}
		}
		System.out.println(accountVerified.getText().equalsIgnoreCase(popupVerify));
		return (accountVerified.getText().equalsIgnoreCase(popupVerify));
	}

	public void delOpenedMail() {
		new Wait(driver).waitForElementToBeClickable(btn_DelThisMsg, 5);
		btn_DelThisMsg.click();
	}

	public boolean verifyRchiveWelcomeEmail(String senderName, String subject) {
		boolean mailPresentFlag = false;
		// see the xpath in gmail the table id is changing
		emails = driver.findElements(By.xpath(".//*[@id=':2x']/tbody/tr"));

		// iterate mails
		for (WebElement mail : emails) {
			// fetch mail sender and subject
			WebElement sender = mail.findElement(By.xpath(".//td[4]/div[2]/span"));
			WebElement subj = mail.findElement(By.xpath(".//td[6]/div/div/div/span[1]"));
			// assert particulars and open mail
			System.out.println(sender.getText());
			System.out.println(subj.getText());
			if (sender.getText().equalsIgnoreCase(senderName) && subj.getText().equalsIgnoreCase(subject)) {
				mail.click();
				isAlertPresent();
				mailPresentFlag = true;
				break;
			}
		}
		return mailPresentFlag;
	}

	public boolean isAlertPresent() {
		boolean flag = false;
		try {

			Alert alert = driver.switchTo().alert();

			alert.accept();
			flag = true;
			System.out.println(alert.getText());
		} catch (NoAlertPresentException e) {
			// e.printStackTrace();
		}
		return flag;
	}

}
