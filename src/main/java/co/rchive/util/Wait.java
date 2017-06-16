package co.rchive.util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	private WebDriver driver;
	private WebDriverWait wait;

	public Wait(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToBeVisible(WebElement elm, int timeInSec) {
		wait = new WebDriverWait(driver, timeInSec);
		wait.until(ExpectedConditions.visibilityOf(elm));
	}

	public void waitForElementToBeSelected(WebElement elm, int timeInSec) {
		wait = new WebDriverWait(driver, timeInSec);
		wait.until(ExpectedConditions.elementToBeSelected(elm));
	}

	
	public void waitForElementToBeClickable(WebElement elm, int timeInSec) {
		wait = new WebDriverWait(driver, timeInSec);
		wait.until(ExpectedConditions.elementToBeClickable(elm));
	}
	
	public void sleepFor(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
