package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPageSauceDemo extends Base{
	


		By email = By.xpath("//input[@id=\"user-name\"]");
		By password = By.xpath("//input[@id=\"password\"]");
		By login = By.xpath("//input[@id=\"login-button\"]");

		public WebElement getEmail() {
			return driver.findElement(email);
		}

		public WebElement getPassword() {
			return driver.findElement(password);
		}

		public WebElement getLogin() {
			return driver.findElement(login);
		}

	}

