package co.rchive.pages.verifyemailspages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class RchiveEmailVerificationForConRequest extends RchiveEmailVerificationForSignUp {
	private WebDriver driver;
	@FindBy(xpath = "//table/tbody/tr/td/div/table[2]/tbody/tr/td/div[1]/div/p[3]")
	private WebElement mailText;

	public RchiveEmailVerificationForConRequest(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean verifyRchiveConRequestEmail(String senderName, String subject,String connectionReqMsg) {
		boolean flag = this.verifyRchiveWelcomeEmail(senderName, subject);
		new Wait(driver).waitForElementToBeVisible(mesageContent, 6);
		if (flag) {
			System.out.println(mailText.getText());
			flag = mailText.getText().contentEquals(connectionReqMsg);
		}
		return flag;
	}
}
