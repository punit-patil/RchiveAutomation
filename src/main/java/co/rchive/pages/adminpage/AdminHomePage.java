package co.rchive.pages.adminpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class AdminHomePage {
	 WebDriver driver;

	@FindBy(css = ".loginbox")
	private WebElement loginBox;

	@FindBy(id = "loginname")
	private WebElement txtbx_UserName;

	@FindBy(id = "password")
	private WebElement txtbx_Password;

	@FindBy(id = "btnLogin")
	private WebElement btn_Login;

	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isAdminPage() {
		System.out.println(btn_Login.isEnabled());
		new Wait(driver).waitForElementToBeVisible(btn_Login, 15);
		return (btn_Login.isDisplayed());
	}

	public AdminDashboardPage adminLoginAction(String adminUserName,
			String adminPasword) {
		new Wait(driver).waitForElementToBeClickable(txtbx_UserName, 6);
		txtbx_UserName.clear();
		txtbx_UserName.sendKeys(adminUserName);
		txtbx_Password.clear();
		txtbx_Password.sendKeys(adminPasword);
		btn_Login.click();
		return new AdminDashboardPage(driver);
	}

}
