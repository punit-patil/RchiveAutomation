package co.rchive.pages.beforeloginpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class SignUpPage {
	private WebDriver driver;

	@FindBy(id = "fname")
	private WebElement txtbx_fname;

	@FindBy(id = "lname")
	private WebElement txtbx_lname;

	@FindBy(id = "email")
	private WebElement txtbx_email;

	@FindBy(id = "cemail")
	private WebElement txtbx_cnfrmEmail;

	@FindBy(id = "password")
	private WebElement txtbx_password;

	@FindBy(id = "cpassword")
	private WebElement txtbx_cnfrmPassword;

	@FindBy(id = "chk_terms_service")
	private WebElement checkbx_policy;

	@FindBy(css = "#submit")
	private WebElement btn_SignUp;

	@FindBy(css = "#signuppopup > div:nth-child(1) > div:nth-child(1)")
	private WebElement cnfrm_SignUpPopUp;

	@FindBy(id = "docs-internal-guid-0886dc64-947c-3023-494c-8f8297204aa3")
	private WebElement signUpCnfrmMsg;

	@FindBy(xpath = "//*[@id='signuppopup']/div/div/div[1]/button")
	private WebElement closeSignUpMsg;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isSignUpPage(String signUpPageURL) {
		System.out.println(driver.getCurrentUrl());
		new Wait(driver).waitForElementToBeVisible(btn_SignUp, 15);
		return (driver.getCurrentUrl().equals(signUpPageURL) && btn_SignUp.isEnabled());
	}

	public void signUpAction(String fName, String lName, String email, String pasword) {
		new Wait(driver).waitForElementToBeVisible(txtbx_fname, 10);

		txtbx_fname.sendKeys(fName);
		txtbx_lname.sendKeys(lName);
		txtbx_email.sendKeys(email);
		txtbx_cnfrmEmail.sendKeys(email);
		txtbx_password.sendKeys(pasword);
		txtbx_cnfrmPassword.sendKeys(pasword);

		checkbx_policy.click();
		if (checkbx_policy.isSelected())
			// new Wait(driver).waitForElementToBeClickable(btn_SignUp,600);
			new Wait(driver).waitForElementToBeVisible(btn_SignUp, 30);
		btn_SignUp.click();
	}

	public boolean isSignUpCnfrm() {
		boolean flag = false;

		new Wait(driver).waitForElementToBeVisible(cnfrm_SignUpPopUp, 10);
		System.out.println("confirm message -" + signUpCnfrmMsg.getText());
		if (closeSignUpMsg.isDisplayed()) {
			closeSignUpMsg.click();
			flag = true;
		}
		return (flag);
	}
}
