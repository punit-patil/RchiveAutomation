package co.rchive.pages.verifyemailspages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class RchiveEmailDeleteForSignUp {
	private WebDriver driver;

	@FindBy(id = "Email")
	private WebElement txtbx_Email;

	@FindBy(id = "Passwd")
	private WebElement txtbx_Paswd;

	@FindBy(id = "next")
	private WebElement btn_Next;

	@FindBy(id = "PersistentCookie")
	private WebElement chk_StaySignedIn;

	@FindBy(id = "signIn")
	private WebElement btn_SignIn;

	@FindBy(id = "gb_71")
	private WebElement btn_SignOut;

	@FindBy(css = ".aos.T-I-J3.J-J5-Ji")
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

	public RchiveEmailDeleteForSignUp(WebDriver driver) {
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
		btn_Next.click();

		new Wait(driver).waitForElementToBeClickable(txtbx_Paswd, 10);
		txtbx_Paswd.click();
		txtbx_Paswd.sendKeys(pasword);
		chk_StaySignedIn.click();
		btn_SignIn.click();
	}

	public void logout() {
		btn_Setings.click();
		btn_SignOut.click();
	}

	public boolean isInboxPage() {
		new Wait(driver).waitForElementToBeVisible(btn_Compose, 20);
		return btn_Compose.isDisplayed() && btn_Setings.isDisplayed();
	}

	/*
	 * public void delOpenedMail() { new
	 * Wait(driver).waitForElementToBeClickable(btn_DelThisMsg, 5);
	 * btn_DelThisMsg.click(); }
	 */

	public boolean deleteRchiveWelcomeEmail(String senderName, String subject) {
		boolean deleteVerfiMail = false;
//table id changes in gmail frequently so look in the dom of gmail for new id
		emails = driver.findElements(By.xpath(".//*[@id=':2x']/tbody/tr"));

		// iterate mails
		for (WebElement mail : emails) {
			// fetch mail sender and subject
			WebElement sender = mail.findElement(By.xpath(".//td[4]/div[2]/span"));
			WebElement subj = mail.findElement(By.xpath(".//td[6]/div/div/div/span[1]"));
			// assert particulars and open mail
			if (sender.getText().equalsIgnoreCase(senderName) && subj.getText().equalsIgnoreCase(subject)) {
				mail.click();
				new Wait(driver).waitForElementToBeClickable(btn_DelThisMsg, 5);
				btn_DelThisMsg.click();
				deleteVerfiMail = true;
				break;
			}
		}
		return deleteVerfiMail;
	}

}
