package co.rchive.pages.verifyemailspages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class RchiveEmailVerificationForSmartShare extends RchiveEmailVerificationForSignUp {
	private WebDriver driver;

	@FindBy(xpath = "//div[1]/table/tbody//div/table[2]/tbody//div[1]/div/p/a")
	private WebElement linkToVerify;
	
	@FindBy(xpath=".//*[@id=':5']/div[2]/div[1]/div/div[1]/div")
	private WebElement back;

	public RchiveEmailVerificationForSmartShare(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean verifyRchiveSmartShareEmail(String senderName, String subject, String emailLinkConent,
			String urlOfEmailLink) {
		boolean flag = this.verifyRchiveWelcomeEmail(senderName, subject);
		if (flag) {
			String parentWindow = driver.getWindowHandle();
			flag = false;
			new Wait(driver).waitForElementToBeVisible(mesageContent, 10);
			// click the smart link
			System.out.println(linkToVerify.getAttribute("href"));
			if (linkToVerify.getAttribute("href").startsWith(emailLinkConent)) {
				linkToVerify.click();

				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Set<String> winHandles = driver.getWindowHandles();
				if (winHandles.size() > 0) {
					for (String childWindow : driver.getWindowHandles()) {
						// switch to newly opened Rchive window
						if (!childWindow.equals(parentWindow)) {
							driver.switchTo().window(childWindow);
							System.out.println(driver.getCurrentUrl());
							flag = driver.getCurrentUrl().startsWith(urlOfEmailLink);
                           System.out.println(flag);
							break;
						}
					}

					if (flag) {
						driver.close();
					}
				}
				driver.switchTo().window(parentWindow);
				back.click();
			}

		}
		return flag;

	}

}
