package co.rchive.pages.adminpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class AdminDeletedUsersPage extends AdminWritersPage {
	private WebDriver driver;

	@FindBy(css = ".head")
	private WebElement header;

	public AdminDeletedUsersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isAdminDeletedUsersPage() {
		new Wait(driver).waitForElementToBeVisible(header, 10);
		return (header.getText().equalsIgnoreCase("Deleted Users"));
	}

	public void searchInDeletedUsersByEmailAndDelete(final String writerEmail) {
		if (this.searchWriterInUsersByEmail(writerEmail))
			this.delWriterAction(writerEmail);
	}
}
