package co.rchive.pages.readerpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.rchive.util.Wait;

public class ReadScreenplay {
	private WebDriver driver;

	@FindBy(xpath = "(//div[@id='first-doc2'])[1]")
	private WebElement sid;

	@FindBy(css = ".action-bar>h4")
	private WebElement sid_Name;

	@FindBy(id = "read")
	private WebElement btn_Read;

	@FindBy(xpath = ".//*[@id='annotationsToolbar']/div[1]")
	private WebElement highlight_annotation;

	@FindBy(xpath = ".//*[@id='toolbar_documentViewer_annotations_flexpaper_colorselector_orange']")
	private WebElement highlight_color;

	@FindBy(xpath = ".//*[@id='documentViewer_dummyPageCanvas_0']")
	private WebElement page1;

	@FindBy(xpath = ".//*[@id='documentViewerpage_0_word_18']")
	private WebElement source;

	@FindBy(xpath = ".//*[@id='documentViewerpage_0_word_91']")
	private WebElement destination;

	@FindBy(xpath = ".//*[@id='toolbar_documentViewer']/img[11]")
	private WebElement nextpage;

	@FindBy(xpath = ".//*[@id='documentViewerpage_0_word_34']")
	private WebElement deleteHighlight;

	@FindBy(xpath = ".//*[@id='annotationsToolbar']/div[5]")
	WebElement deleteicon;

	@FindBy(xpath = ".//*[@id='rchiveLogoToReturnToScreenplay']")
	private WebElement rchivelogo;

	@FindBy(className = "flexpaper_initloader_panel")
	private WebElement loader;

	public ReadScreenplay(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void readScreenplay() {
		// Actions action = new Actions(driver);
		// action.moveToElement(sid).doubleClick().build().perform();
		sid.click();
		new Wait(driver).waitForElementToBeVisible(btn_Read, 10);
		if (btn_Read.isEnabled()) {
			btn_Read.click();
			// new Wait(driver).waitForElementToBeVisible(loader, 2000);
			new Wait(driver).waitForElementToBeClickable(nextpage, 5);

			WebDriverWait wait = new WebDriverWait(driver, 3);

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("flexpaper_initloader_panel")));
			nextpage.click();

			// new Wait(driver).waitForElementToBeSelected(nextpage, 20);

			// new Wait(driver).sleepFor(18000);
		}
	}

}
