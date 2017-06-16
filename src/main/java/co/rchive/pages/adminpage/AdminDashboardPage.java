package co.rchive.pages.adminpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

import co.rchive.util.Wait;

public class AdminDashboardPage {
	private WebDriver driver;

	@FindBy(linkText = "Admin Management")
	private WebElement adminMangmnt;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li[2]/ul")
	private WebElement adminMangmntLinks;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li[2]/ul/li[2]")
	private WebElement btn_writers;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li[2]/ul/li[7]")
	private WebElement btn_deletedUsers;

	@FindBy(css = ".rightAlign.welcometxt>a")
	private WebElement btn_Logout;

	public AdminDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isAdminDashboardPage() {
		new Wait(driver).waitForElementToBeVisible(adminMangmnt, 10);
		return (adminMangmnt.isDisplayed());
	}

	public AdminWritersPage goToAdminWritersPage() {
		new Wait(driver).waitForElementToBeVisible(adminMangmnt, 10);
		adminMangmnt.click();
		new Wait(driver).waitForElementToBeClickable(btn_writers, 6);
		btn_writers.click();
		return new AdminWritersPage(driver);
	}

	public AdminDeletedUsersPage goToAdminDeletedUsersPage() {
		if (adminMangmntLinks.isDisplayed()) {
			new Wait(driver).waitForElementToBeClickable(btn_deletedUsers, 6);
			btn_deletedUsers.click();
		} else {
			new Wait(driver).waitForElementToBeVisible(adminMangmnt, 10);
			adminMangmnt.click();
			new Wait(driver).waitForElementToBeClickable(btn_deletedUsers, 6);
			btn_deletedUsers.click();
		}
		return new AdminDeletedUsersPage(driver);
	}

	public AdminHomePage logoutAdmin() {
		new Wait(driver).waitForElementToBeClickable(btn_Logout, 8);
		btn_Logout.click();
		return new AdminHomePage(driver);
	}
}
