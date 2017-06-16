package co.rchive.pages.userpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.rchive.spec.userpages.MyConnectionSpecDefinition;
import co.rchive.util.Wait;

public class MyConnectionsPage {
	private WebDriver driver;

	@FindBy(css = ".menu-icon.glyphicon.glyphicon-resize-small")
	private WebElement sideBarLink_MyConnections;

	@FindBy(xpath = "//div[@id='rootwizard']/ul/li[2]/a")
	private WebElement link_AddConnections;

	@FindBy(xpath = "//div[@id='rootwizard']/ul/li[1]/a")
	private WebElement link_ManageRequest;

	@FindBy(xpath = "//div[@id='rootwizard']/ul/li[3]/a")
	private WebElement link_My_Connections;

	@FindBy(id = "emails")
	private WebElement txtarea_ToField;

	@FindBy(id = "messagedesc")
	private WebElement txtarea_Message;

	@FindBy(id = "btnsendinvite")
	private WebElement btn_SendInvitation;

	@FindBy(css = ".active>a")
	private WebElement activeConnectionsWin;

	@FindBy(xpath = "//div[@id='sent']/div[1]/p[2]")
	private WebElement sentConnectionReqStatus;

	@FindBy(css = ".success")
	private WebElement msgAftConnectionInvite;

	@FindBy(xpath = "//div[@id='received']/div[1]/div[2]/button[1]")
	private WebElement btn_AcceptReq;

	@FindBy(xpath = "//div[@id='received']/div[1]/p[2]")
	private WebElement recievedConnectionReqStatus;

	@FindBy(xpath = "//div[@id='received']/div[1]/p[1]/a")
	private WebElement requesterNameInRecievedCon;

	@FindBy(xpath = "//div[1]/div/div[4]/div/ul[2]/li[1]/a/i")
	private WebElement notificationbell;

	@FindBy(css = "p.task-details")
	private WebElement conReqNotification;

	public MyConnectionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public MyConnectionSpecDefinition goToMyConnectionsPage() {
		sideBarLink_MyConnections.click();

		return new MyConnectionSpecDefinition(driver);
	}

	public boolean isMyConnectionsPage() {
		new Wait(driver).waitForElementToBeVisible(link_AddConnections, 8);
		return (link_AddConnections.isDisplayed());
	}

	private boolean isCurrentMyConnectionsWin(String windowName) {
		return ((activeConnectionsWin.getText().contentEquals(windowName)));
	}

	public MyConnectionSpecDefinition sendConnectionRequest(String email, String msg) {
		String windowName = "Add Connections";

		link_AddConnections.click();
		// check add connections opened & send connection req
		if (isCurrentMyConnectionsWin(windowName)) {
			new Wait(driver).waitForElementToBeClickable(txtarea_ToField, 5);
			txtarea_ToField.sendKeys(email);
			txtarea_Message.sendKeys(msg);
			new Wait(driver).waitForElementToBeClickable(btn_SendInvitation, 20);

			btn_SendInvitation.click();
			new Wait(driver).waitForElementToBeVisible(msgAftConnectionInvite, 20);

			System.out.println("connection Req msg -" + msgAftConnectionInvite.getText());
		}
		return new MyConnectionSpecDefinition(driver);
	}

	public boolean checkSentConnectionStatus(String conStatus) {
		String windowName = "Manage Requests";
		boolean statusFlag = false;

		sideBarLink_MyConnections.click();
		// verify sent connection Req status
		new Wait(driver).waitForElementToBeVisible(link_ManageRequest, 15);
		if (isCurrentMyConnectionsWin(windowName)) {
			List<WebElement> sentConnectionsList = driver.findElements(By.xpath("//div[@id='sent']/div"));
			if (sentConnectionsList.size() > 0
					&& (sentConnectionReqStatus.getText().trim().equalsIgnoreCase(conStatus))) {
				statusFlag = true;
			}
		}
		return statusFlag;
	}

	public MyConnectionSpecDefinition acceptConnectionRequest() {
		String windowName = "Manage Requests";

		// TODO: check notify no.. & read & verify notification content
		link_ManageRequest.click();
		// checks manage request is opened
		if (isCurrentMyConnectionsWin(windowName)) {
			// checks connection request present, if yes accept
			List<WebElement> recievedConnectionsList = driver.findElements(By.xpath("//div[@id='received']/div"));
			if (recievedConnectionsList.size() > 0
					&& (recievedConnectionReqStatus.getText().trim().equalsIgnoreCase("Pending"))) {
				btn_AcceptReq.click();
			} else {
				System.out.println("no request(s) found to accept");
			}
		}
		return new MyConnectionSpecDefinition(driver);
	}

	public MyConnectionSpecDefinition acceptConnectionRequestThroughNotification() {
		new Wait(driver).waitForElementToBeClickable(notificationbell, 15);

		notificationbell.click();
		if (conReqNotification.getText().startsWith("You")) {
			System.out.println(conReqNotification.getText());
			conReqNotification.click();
		}

		return new MyConnectionSpecDefinition(driver);
	}

	public boolean checkRecievedConnectionStatus(String conStatus) {
		String windowName = "Manage Requests";
		boolean statusFlag = false;

		if (isCurrentMyConnectionsWin(windowName)) {
			// checks connection request present, if yes accept
			List<WebElement> recievedConnectionsList = driver.findElements(By.xpath("//div[@id='received']/div"));
			if (recievedConnectionsList.size() > 0
					&& (recievedConnectionReqStatus.getText().trim().equalsIgnoreCase("Pending"))) {
				btn_AcceptReq.click();
			} else {
				System.out.println("no request(s) found to accept");
			}

			// verify received connection Req status
			new Wait(driver).waitForElementToBeVisible(link_ManageRequest, 30);
			if (isCurrentMyConnectionsWin(windowName)) {
				List<WebElement> recievedConnectionsListThroughNoti = driver
						.findElements(By.xpath("//div[@id='received']/div"));
				if (recievedConnectionsListThroughNoti.size() > 0
						&& (recievedConnectionReqStatus.getText().trim().equalsIgnoreCase(conStatus))) {
					statusFlag = true;
				}
			}
		}
		return statusFlag;
	}

	public boolean checkAcceptedUserAddedInRequesteeConList() {
		String windowName = "Manage Requests";
		String requesterName = null;
		boolean statusFlag = false, controlFlag = false;

		if (isCurrentMyConnectionsWin(windowName)) {
			List<WebElement> recievedConnectionsList = driver.findElements(By.xpath("//div[@id='received']/div"));
			if (recievedConnectionsList.size() > 0) {
				new Wait(driver).waitForElementToBeVisible(requesterNameInRecievedCon, 8);
				requesterName = requesterNameInRecievedCon.getText().trim();
				link_My_Connections.click();
				controlFlag = true;
			}
		}
		windowName = "My Connections";
		if (controlFlag && isCurrentMyConnectionsWin(windowName)) {
			// check connections present
			List<WebElement> connectionsList = driver.findElements(By.xpath("//div[@id='replace_friends']/div"));
			if (connectionsList.size() > 0) {
				System.out.println(connectionsList.size());
				// fetch connections name & verify requester name present
				for (int i = 0; i < connectionsList.size(); i++) {
					int n = i + 1;
					String namesOfCon = driver
							.findElement(By.xpath("//div[@id='replace_friends']/div[" + n + "]/div/div/div[2]/p[1]/a"))
							.getText().trim();
					System.out.println(namesOfCon);
					if (namesOfCon.equals(requesterName)) {
						statusFlag = true;
						break;
					}
				}
			}
		}
		return statusFlag;
	}
}
