package co.rchive.pages.userpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.util.Wait;

public class ActivityLogPage {
	private WebDriver driver;

	// @FindBy(xpath = "//div/div[2]/div/div[1]/div/div/a/div")
	List<WebElement> selectScreenplay;
	// .//*[@id='grid-small']/div/div[2]

	@FindBy(xpath = "//div/div[1]/div[3]/div/div[2]/div/div[2]")
	private WebElement miniscroll;

	@FindBy(xpath = "(//div[@id='first-doc2'])[1]")
	private WebElement firstScreen;

	@FindBy(id = "myTabDrop1")
	private WebElement moreOption;

	// @FindBy(xpath = ".//*[@id='myTab']/li[6]/ul/li/a")
	private List<WebElement> moreDropdown;

	@FindBy(xpath = ".//*[@id='myTab']/li[6]/ul/li[1]/a")
	private WebElement activityLogCheck;
	
	@FindBy(xpath=".//*[@id='file-doc-detail_b']/div[1]/div/div[1]/div/a/i")
	private WebElement closeActivityLog;

    List<WebElement> tableRows;

	public ActivityLogPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean selectScreenplayFromDashboard(String screenplayName) {
		boolean screenPlyName = false;
		selectScreenplay = driver.findElements(By.xpath("//div/div[2]/div/div[1]/div/div/a/div"));

		for (WebElement screen : selectScreenplay) {
			System.out.println(screen.getText());
			// screen.sendKeys(Keys.PAGE_DOWN);
			screenPlyName = screen.getText().equalsIgnoreCase(screenplayName);
			if (screen.getText().equalsIgnoreCase(screenplayName)) {
				screen.click();
				break;
			}
}
		//new Wait(driver).sleepFor(500);
		return screenPlyName;
	}

	public void selectActivityLogFromMoreDropDown(String activityLog) {
		moreOption.click();
		moreDropdown = driver.findElements(By.xpath(".//*[@id='myTab']/li[6]/ul/li/a"));
		for (WebElement dropList : moreDropdown) {
			if (dropList.getText().equalsIgnoreCase(activityLog)) {
				System.out.println(dropList);
				new Wait(driver).waitForElementToBeClickable(activityLogCheck, 10000);
				dropList.click();
				break;
			}

		}
		new Wait(driver).sleepFor(500);
	}

	public boolean checkUserActivityInActivityLog(String useremail, String useractivity) {
		boolean validactivity = false;
		try{
		tableRows =driver.findElements(By.xpath("//table[@id='report']//tr"));
	    System.out.println(tableRows.size());
		
		for(int i=2;i<=tableRows.size();i=i+2){
		WebElement email=driver.findElement(By.xpath("//table[@id='report']//tr["+i+"]"));
		System.out.println(email.getText());
			WebElement col=email.findElement(By.xpath("//tr["+i+"]"+"/td[1]"));
			System.out.println(col.getAttribute("title"));
			WebElement col2=email.findElement(By.xpath("//tr["+i+"]"+"/td[2]"));
			System.out.println(col2.getText());
			if(col.getAttribute("title").equalsIgnoreCase(useremail) && col2.getText().equalsIgnoreCase(useractivity)){
				validactivity = true;
				break;	
			}
				}
			
		}catch(Exception e){
		System.out.println(e.getMessage());
		}
		closeActivityLog.click();
		new Wait(driver).sleepFor(1000);
	
	/*	for(WebElement test:tableRows){
		WebElement name=test.findElement(By.xpath("//td[1]"));
		WebElement email=test.findElement(By.xpath("//td[2]"));
			System.out.println(name.getText());
			System.out.println(email.getAttribute("title"));
		}
		System.out.println(activityLogContent.size());
		for (WebElement activity : activityLogContent) {
			WebElement usermaill = activity.findElement(By.xpath(".//tr/td[1]"));
			WebElement useractivii = activity.findElement(By.xpath(".//tr/td[2]"));
			System.out.println(usermaill.getAttribute("title"));
			System.out.println(useractivii.getText());
			if (usermaill.getAttribute("title").equalsIgnoreCase(useremail)
					&& useractivii.getText().equalsIgnoreCase(useractivity)) {
				validactivity = true;
				break;
			}

		}
	*/
		return validactivity;
	}

}
