package co.rchive.spec.verifyemails;

//import static org.testng.AssertJUnit.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import co.rchive.pages.verifyemailspages.RchiveEmailDeleteForSignUp;
import co.rchive.pages.verifyemailspages.RchiveEmailVerificationForConRequest;
import co.rchive.pages.verifyemailspages.RchiveEmailVerificationForConShare;
import co.rchive.pages.verifyemailspages.RchiveEmailVerificationForSignUp;
import co.rchive.pages.verifyemailspages.RchiveEmailVerificationForSmartShare;

public class VerifyEmailSpecDefinition {
	RchiveEmailVerificationForSignUp verifySignUp;
	RchiveEmailVerificationForConShare verifyConShare;
	RchiveEmailVerificationForSmartShare verifySmartShare;
	RchiveEmailDeleteForSignUp deletemail;
	RchiveEmailVerificationForConRequest verifyConReqMail;

	public VerifyEmailSpecDefinition(WebDriver driver) {
		verifySignUp = new RchiveEmailVerificationForSignUp(driver);
		verifyConShare = new RchiveEmailVerificationForConShare(driver);
		verifySmartShare = new RchiveEmailVerificationForSmartShare(driver);
		deletemail = new RchiveEmailDeleteForSignUp(driver);
		verifyConReqMail = new RchiveEmailVerificationForConRequest(driver);
	}

	public void loginToUserEmail(String userName, String pasword) {
		Assert.assertTrue(verifySignUp.checkGmailLoginPane());
		verifySignUp.gmailLoginAction(userName, pasword);
		Assert.assertTrue(verifySignUp.isInboxPage());
	}

	public VerifyEmailSpecDefinition clickSignUpLink() {
		Assert.assertTrue(verifySignUp.verifySignUpRegisterLink("rchive", "rchive verification email",
				"http://beta.rchive.co", "Account verified"));

		return this;
	}

	public void deleteRequiredEmail() {
		Assert.assertTrue(deletemail.deleteRchiveWelcomeEmail("rchive", "rchive verification email"));

	}

	public void verifyRchiveWelcomeMailAndDelEmail() {
		Assert.assertTrue(verifySignUp.verifyRchiveWelcomeEmail("rchive", "Welcome to Rchive - get started now!"));
		// verifySignUp.delOpenedMail();
	}

	public void verifyRchiveConShareMail() {
		Assert.assertTrue(
				verifyConShare.verifyRchiveConShareEmail("rchive", "Invitation to Review Screenplay on Rchive"));
		verifyConShare.delOpenedMail();
	}

	public void verifyRchiveConRequestMail() {
		Assert.assertTrue(verifyConReqMail.verifyRchiveConRequestEmail("rchive", "Rchive Connection Request",
				"Please click here to accept and join their network."));
		verifyConShare.delOpenedMail();
	}

	public void verifyRchiveSmartShareMail() {
		Assert.assertTrue(verifySmartShare.verifyRchiveSmartShareEmail("Rchive", "Screenplay Share",
				"http://beta.rchive.co/reader/view", "http://beta.rchive.co/reader/view/"));
	}

	public void logOutUserEmail() {
		verifySignUp.logout();
		// assertTrue(verifySignUp.checkGmailLoginPane());
	}
}
