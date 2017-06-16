package co.rchive.pages.beforeloginpages;

//import static org.junit.Assert.assertEquals;
import org.testng.Assert;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.util.Wait;

public class HomePage {
	private WebDriver driver;

	@FindBy(id = "loginlink")
	private WebElement link_Login;

	@FindBy(linkText = "Sign up")
	private WebElement link_SignUp;

	@FindBy(id = "btnlogin")
	private WebElement btn_Login;

	@FindBy(id = "logemaildesktop")
	private WebElement txtbx_UserName;

	@FindBy(id = "logpwddesktop")
	private WebElement txtbx_Pasword;

	@FindBy(xpath = ".//*[@id='logindiv']/div/div[9]/a/img")
	private WebElement fbloginbutton;

	@FindBy(xpath = "//div[5]/div[1]/form/div/div/div[11]/a/img")
	private WebElement twitterloginbutton;

	@FindBy(id = "email")
	private WebElement fbloginid;

	@FindBy(id = "pass")
	private WebElement fbloginpass;

	@FindBy(id = "loginbutton")
	private WebElement loginfbbutton;

	@FindBy(id = "username_or_email")
	private WebElement twitterid;

	@FindBy(id = "password")
	private WebElement twitterpassword;

	@FindBy(id = "allow")
	private WebElement twittersignin;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean checkSignUpBtn() {
		new Wait(driver).waitForElementToBeVisible(link_SignUp, 10);
		return link_SignUp.isDisplayed();
	}

	public boolean isHomePage() {
		new Wait(driver).waitForElementToBeClickable(link_Login, 15);
		return (link_Login.isEnabled());
	}

	public boolean isFBButtonPresent() {
		new Wait(driver).waitForElementToBeClickable(fbloginbutton, 15);

		return (fbloginbutton.isDisplayed());
	}

	public boolean isTwitterButtonPresent() {
		new Wait(driver).waitForElementToBeClickable(twitterloginbutton, 15);

		return (twitterloginbutton.isDisplayed());
	}

	public SignUpPage goToSignUpPage() {
		if (checkSignUpBtn()) {

			link_SignUp.click();
			// new Wait(driver).waitForElementToBeClickable(link_SignUp, 10);
		}
		return new SignUpPage(driver);
	}

	public DashboardSpecDefinition loginAction(String userName, String pasword) {
		link_Login.click();

		new Wait(driver).waitForElementToBeClickable(txtbx_UserName, 10);
		txtbx_UserName.clear();
		txtbx_UserName.sendKeys(userName);

		txtbx_Pasword.clear();
		txtbx_Pasword.sendKeys(pasword);

		if (btn_Login.isDisplayed()) {
			btn_Login.click();
		}
		return new DashboardSpecDefinition(driver);
	}

	public DashboardSpecDefinition loginWithFB(String fbEmail, String password) {
		String originalWind = driver.getWindowHandle();
		link_Login.click();
		new Wait(driver).waitForElementToBeClickable(fbloginbutton, 15);
	//	assertEquals("rchive - A Secured Screenplay platform", driver.getTitle());
		 Assert.assertEquals("rchive - A Secured Screenplay platform", driver.getTitle());
		fbloginbutton.click();
		// new Wait(driver).waitForElementToBeVisible(fbloginid, 80);

		try {
			Set<String> WinHandle = driver.getWindowHandles();
			if (WinHandle.size() > 0) {
				for (String childWindo : driver.getWindowHandles()) {
					driver.switchTo().window(childWindo);
					driver.manage().window().maximize();
					System.out.println(driver.getTitle());
				}
			}

		} catch (Exception e) {

		}
		//assertEquals("Facebook", driver.getTitle());
        Assert.assertEquals("Facebook", driver.getTitle());
		fbloginid.clear();
		fbloginid.sendKeys(fbEmail);

		fbloginpass.clear();
		fbloginpass.sendKeys(password);

		loginfbbutton.click();
		driver.switchTo().window(originalWind);
		return new DashboardSpecDefinition(driver);
	}

	public DashboardSpecDefinition loginTOTwitter(String twittermail, String password) {
		link_Login.click();
		Assert.assertEquals("rchive - A Secured Screenplay platform", driver.getTitle());

		new Wait(driver).waitForElementToBeClickable(twitterloginbutton, 15);
		twitterloginbutton.click();
		Assert.assertEquals("Twitter / Authorize an application", driver.getTitle());

		twitterid.sendKeys(twittermail);
		twitterpassword.sendKeys(password);
		twittersignin.click();

		return new DashboardSpecDefinition(driver);

	}

}
