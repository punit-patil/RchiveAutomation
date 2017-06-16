package co.rchive.pages.verifyemailspages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class RchiveEmailVerificationForConShare extends
		RchiveEmailVerificationForSignUp {

	private WebDriver driver;

	@FindBy(xpath = "//div[1]/table/tbody//div/table[2]/tbody//div[1]/div/p[2]")
	private WebElement mailText;

	public RchiveEmailVerificationForConShare(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean verifyRchiveConShareEmail(String senderName, String subject) {
		boolean flag = this.verifyRchiveWelcomeEmail(senderName, subject);
		new Wait(driver).waitForElementToBeVisible(mesageContent, 6);
		if (flag) {
			flag = mailText.getText().matches(
					".*\\bhas invited you to review the screenplay\\b\\s.*");
			System.out.println(flag);
		}
		return flag;
	}
}
