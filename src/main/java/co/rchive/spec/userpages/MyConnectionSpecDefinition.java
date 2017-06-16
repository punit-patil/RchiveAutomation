package co.rchive.spec.userpages;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.userpages.MyConnectionsPage;
import co.rchive.spec.beforelogin.BeforeLoginSpecDefinition;

public class MyConnectionSpecDefinition {
	private MyConnectionsPage myConnections;
	private BeforeLoginSpecDefinition loginSpec;

	public MyConnectionSpecDefinition(WebDriver driver) {
		loginSpec = new BeforeLoginSpecDefinition(driver);
		myConnections = new MyConnectionsPage(driver);
	}

	public void loginToRchiveWithEmail(String email, String pasword) {
		loginSpec.loginWithEmail(email, pasword);
	}

	public void goToMyConnections() {
		myConnections.goToMyConnectionsPage();
		Assert.assertTrue(myConnections.isMyConnectionsPage());
	}

	public void sendConnectionRequest() {
		myConnections.sendConnectionRequest("rchivesanity2@gmail.com", "its my first test");
		Assert.assertTrue(myConnections.checkSentConnectionStatus("Pending"));
	}

	public void acceptConnecctionReq() {
		// myConnections.acceptConnectionRequest();
		myConnections.acceptConnectionRequestThroughNotification();
		Assert.assertTrue(myConnections.checkRecievedConnectionStatus("Accepted"));
		Assert.assertTrue(myConnections.checkAcceptedUserAddedInRequesteeConList());
	}
}
