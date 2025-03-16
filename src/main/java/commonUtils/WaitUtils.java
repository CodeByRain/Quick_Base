package commonUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/*
 * This is an utility class and contains common util methods related to waits
 */
	public class WaitUtils {
	    
	    /**
	     * Waits for the page to load completely using document.readyState.
	     * @param driver The WebDriver instance
	     * @param timeoutInSeconds The maximum time to wait for the page to load
	     */
	    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
	        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(
	            webDriver -> ((JavascriptExecutor) webDriver)
	                .executeScript("return document.readyState").equals("complete")
	        );
	    }
	    
	    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeout) {
	        new WebDriverWait(driver, Duration.ofSeconds(timeout))
	                .until(ExpectedConditions.visibilityOf(element));
	    }

	    // Wait for an element to be clickable
	    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout) {
	        new WebDriverWait(driver, Duration.ofSeconds(timeout))
	                .until(ExpectedConditions.elementToBeClickable(element));
	    }
	}



