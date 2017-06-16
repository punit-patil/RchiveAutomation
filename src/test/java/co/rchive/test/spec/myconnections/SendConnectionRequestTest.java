package co.rchive.test.spec.myconnections;

import java.util.Properties;

//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.spec.userpages.MyConnectionSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class SendConnectionRequestTest extends TestBase {
	TestBase tb = new TestBase();
	Properties prop = tb.getpropValues();

	public SendConnectionRequestTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	// @Parameters({"loginEmail","loginPasword"})
	@Test
	public void sendconnectionRequest() {
		// public void sendconnectionRequest(String loginEmail,String
		// loginPasword) {
		MyConnectionSpecDefinition conReqUser = new MyConnectionSpecDefinition(driver);
		conReqUser.loginToRchiveWithEmail(prop.getProperty("email"), prop.getProperty("password"));
		conReqUser.goToMyConnections();
		conReqUser.sendConnectionRequest();
		DashboardSpecDefinition dash = new DashboardSpecDefinition(driver);
		dash.logoutFromRchive();
	}

}
