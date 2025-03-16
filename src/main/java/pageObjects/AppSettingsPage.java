package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/*
 * AppSettingsPage class contains locators related to app settings page
 */

public class AppSettingsPage extends Base{



	
	public By logOut = By.xpath("//*[@id=\"signOutButton\"]");
	public By profileOption = By.xpath("//div[@id=\"signedInAsButton\"]/span/span");
	
		
	public WebElement getprofileOption() {
		return driver.findElement(profileOption);
	}
	public WebElement getlogOutButton() {
		return driver.findElement(logOut);
	}
		
}