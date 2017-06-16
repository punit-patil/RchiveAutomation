package co.rchive.pages.adminpage;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.rchive.util.Wait;

public class AdminWritersPage {
	private WebDriver driver;

	@FindBy(css = ".head")
	private WebElement header;

	@FindBy(xpath = "//div[@id='example_filter']/label/input")
	private WebElement searchBox;

	@FindBy(xpath = "//table[@id='example']/tbody/tr/td/input[@name='checbox_ids[]']")
	private List<WebElement> rows;

	@FindBy(id = "submit")
	private WebElement btn_go;

	@FindBy(id = "action")
	private WebElement actionDropdown;

	@FindBy(xpath = "//*[@id='example']/tbody")
	private WebElement searchTable;

	public AdminWritersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean isAdminWritersPage() {
		new Wait(driver).waitForElementToBeVisible(header, 10);
		return (header.getText().equalsIgnoreCase("Users"));
	}

	public boolean searchWriterInUsersByEmail(String rchiveUserEmail) {
		new Wait(driver).waitForElementToBeVisible(searchBox, 15);
		searchBox.sendKeys(rchiveUserEmail);
		return (rows.size() > 0);
	}

	public boolean delWriterAction(String writerToDel) {
		WebElement checkBox = null;
		boolean flag = false;

		new Wait(driver).waitForElementToBeVisible(searchTable, 15);
		for (int i = 1; i <= rows.size(); i++) {

			String email = searchTable
					.findElement(
							By.xpath("//table[@id='example']/tbody/tr[" + i
									+ "]/td[4]")).getText();
			System.out.println("user email to del -" + email);
			if (email.equals(writerToDel)) {
				checkBox = driver.findElement(By
						.xpath("//table[@id='example']/tbody/tr[" + i
								+ "]/td[1]/input[@type='checkbox']"));
				checkBox.click();
				break;
			}
		}
		if (checkBox.isSelected()) {
			Select select = new Select(actionDropdown);
			select.selectByValue("delete");
			btn_go.click();

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			flag = true;
		}
		return flag;
	}

}
