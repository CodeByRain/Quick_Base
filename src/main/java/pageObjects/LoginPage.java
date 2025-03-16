package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * LoginPage class contains all the locators of Login page
 */
public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public By noButton = By.xpath("//button[contains(@id,\"quickbaseSignin\")]");
	public By yesButton = By.xpath("//button[contains(@id,\"samlSignin\")]");
	public By textOnPage = By.xpath("//b[contains(text(),'Do you have a Quickbase single sign-on (SSO) account?')]");
	public By textNeedToLogin = By.xpath("//div[@id=\"loginMsgDiv\"]");
	public By userName = By.xpath("//input[@name=\"loginid\" and @class='UserInput WithPadding']");
	public By password = By.xpath("//input[@name=\"password\" and @class='UserInput WithPadding']");
	public By signInButton = By.xpath("//button[@id=\"signin\"]");
	public By inputCode = By.xpath("//input[@id=\"TwoStepCode\"]");
	public By submitCode = By.xpath("//button[@id=\"signin\"]");
	
	public WebElement getNoButton() {
		return driver.findElement(noButton);
	}

	public WebElement getYesButton() {
		return driver.findElement(yesButton);
	}

	public WebElement getTextOnPage() {
		return driver.findElement(textOnPage);
	}
	public WebElement textNeedToLogin() {
		return driver.findElement(textNeedToLogin);
	}
	public WebElement enterUserName() {
		return driver.findElement(userName);
	}
	
	public WebElement enterPassword() {
		return driver.findElement(password);
	}
	public WebElement signInButton() {
		return driver.findElement(signInButton);
	}

	public WebElement getInputCode() {
		return driver.findElement(inputCode);
	}
	public WebElement getSubmitCode() {
		return driver.findElement(submitCode);
	}
}
