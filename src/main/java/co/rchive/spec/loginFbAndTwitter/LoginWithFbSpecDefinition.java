package co.rchive.spec.loginFbAndTwitter;

//import static org.junit.Assert.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.beforeloginpages.HomePage;
import co.rchive.spec.userpages.DashboardSpecDefinition;

public class LoginWithFbSpecDefinition {
	private WebDriver driver;
	HomePage homePage;
	DashboardSpecDefinition dashboardSpec;
	
	public LoginWithFbSpecDefinition(WebDriver driver) {
		super();
		this.driver = driver;
		homePage=new HomePage(driver);
	}
	
	public DashboardSpecDefinition loginWithFB(String fbemail,String password){
		Assert.assertTrue(homePage.isHomePage());
		dashboardSpec = homePage.loginWithFB(fbemail, password);
	//	dashboardSpec.logoutFromRchive();
		
		return new DashboardSpecDefinition(driver);
	}

	

}
