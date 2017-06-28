package co.rchive.test.spec.myconnections;

import java.util.Properties;

import org.testng.annotations.Test;

import co.rchive.spec.userpages.DashboardSpecDefinition;
import co.rchive.spec.userpages.MyConnectionSpecDefinition;
import co.rchive.test.testbase.TestBase;

public class AcceptConnectionRequestTest extends TestBase {
	TestBase tb = new TestBase();
	Properties prop = tb.getpropValues();

	public AcceptConnectionRequestTest() {
		super();
		setBrowser(prop.getProperty("browser1"));
		setBaseURL(prop.getProperty("beta_url"));
	}

	@Test
	public void acceptConnectionReq() {
		MyConnectionSpecDefinition conReqUser = new MyConnectionSpecDefinition(driver);
		DashboardSpecDefinition dash = new DashboardSpecDefinition(driver);
		conReqUser.loginToRchiveWithEmail(prop.getProperty("email_accept_request"),
				prop.getProperty("pass_accept_request"));
		// conReqUser.goToMyConnections();
		conReqUser.acceptConnecctionReq();
		dash.logoutFromRchive();
	}
}
