package tests;

import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commonUtils.CommonMethodsUtil;
import commonUtils.WaitUtils;
import io.qameta.allure.Description;
import pageObjects.Base;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;


public class LoginPageTest extends Base {

	public static final String loginPageMessage = "Do you have a Quickbase single sign-on (SSO) account?";
	public static final String needToLoginMessage = "You need to sign in to get to that page.";
	public static Logger log = LogManager.getLogger(LoginPageTest.class.getName());
	String userName = "abc_test@gmail.com";
	String password = "Quickbase@123";
	String newTableName = CommonMethodsUtil.generateRandomString(6)+" Test_Table";
	WebDriverWait wait;
	DashboardPage dbPage;



	@BeforeClass
	public void initialize() throws IOException {

		driver = initializeDriver();

	}

	@Test(priority=1)
	@Description("Verify user login functionality")
	public void testLogin()
			throws InterruptedException, IOException {
		System.out.println("Login Test Started" );
		driver.get(prop.getProperty("url"));

		WaitUtils.waitForPageToLoad(driver, 10);

		LoginPage lgp = new LoginPage(driver);
		WaitUtils.waitForElementToBeVisible(driver, lgp.getTextOnPage(), 20);

		Assert.assertEquals(lgp.getTextOnPage().getText(), loginPageMessage);

		lgp.getNoButton().click();
		WaitUtils.waitForPageToLoad(driver, 10);

		WaitUtils.waitForElementToBeVisible(driver, lgp.textNeedToLogin(), 20);
		Assert.assertEquals(lgp.textNeedToLogin().getText(), needToLoginMessage);

		lgp.enterUserName().sendKeys(userName);
		lgp.enterPassword().sendKeys(password);
		WaitUtils.waitForElementToBeVisible(driver, lgp.signInButton(), 20);
		lgp.signInButton().click();
		System.out.println("Waiting for code ");
		Thread.sleep(10000);
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter the code :");
		String code = myObj.nextLine();
		System.out.println("code is: " +code);  
		lgp.getInputCode().sendKeys(code);
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.RETURN).build().perform();
		System.out.println("Logged in to the system");
		dbPage = new DashboardPage();
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForElementToBeVisible(driver, dbPage.getProjectName(), 20);
		System.out.println("Clicking on ProjectName");
		dbPage.getProjectName().click();

		Assert.assertEquals(dbPage.getAppSettings().isDisplayed(), true, "AppSettings option not displayed");
	}

	@Test(priority=2, dependsOnMethods= {"testLogin"})
	@Description("Verify Create Table functionality")
	public void testCreateTable() throws InterruptedException{
		System.out.println("Create Table Test Started");
		dbPage.getAppSettings().click();
		WaitUtils.waitForElementToBeVisible(driver, dbPage.getNewButton(), 20);
		System.out.println("Verify Cancel button on Create Table Modal");
		enterDialogInfo(newTableName);
		dbPage.getButtonCancelDialog().click();
		System.out.println("Verify Creation of New Table using Create Table Modal");
		enterDialogInfo(newTableName);
		dbPage.getButtonCreateTable().click();

		WaitUtils.waitForElementToBeVisible(driver, dbPage.getNewFieldsDialog(), 20);
		System.out.println("Enter details in New Fields Dialog");
		String fieldName ="Name";
		dbPage.getInputAddFieldName().sendKeys(fieldName);
		dbPage.getButtonFieldsDialogOk().click();
		System.out.println("Verify table of fields for added field : "+ fieldName);
		Assert.assertTrue(dbPage.isElementPresentInTable(fieldName, dbPage.getFieldsTable()), "Expected field not found in the table");
		WaitUtils.waitForElementToBeVisible(driver, dbPage.getAppSettings(), 20);
		dbPage.getAppSettings().click();
		enterDialogInfo(newTableName);
		dbPage.getButtonCreateTable().click();
		System.out.println("Verify table exists message");
		WaitUtils.waitForElementToBeVisible(driver, dbPage.getTableNameAlreadyExistsMessage(), 20);
		String actualAlreadyExistsMessage = dbPage.getTableNameAlreadyExistsMessage().getText();
		dbPage.getButtonCancelDialog().click();
		Assert.assertEquals(actualAlreadyExistsMessage, "There is already a table with this name.", "Table Exists Message did not show up");

	}

	@Test(priority=3, dependsOnMethods = {"testLogin"}, enabled=true)
	@Description("Verify Table View page and functionality")
	public void testTablesView() throws InterruptedException {
		System.out.println("Tables View Test Started");
		dbPage = new DashboardPage();
		// driver.navigate().refresh();
		dbPage.getAppSettings().click();
		dbPage.getClickOnLinkForTables();
		System.out.println("Navigating to All Tables Page");
		Thread.sleep(5000);
		dbPage.getInputSearchTable().sendKeys(newTableName);
		Assert.assertTrue(dbPage.isElementPresentInTable(newTableName, dbPage.getListOfTablesAvailable()), "Searched Table name is not found in the search result");
		dbPage.getButtonSearchDelete().click();
		Assert.assertEquals(dbPage.getInputSearchTable().getText(), "", "Searched table name is not deleted from search box");
	}

	@Test(priority=4, dependsOnMethods = {"testLogin"}, enabled=true)
	@Description("Verify delete table functionality")
	public void testDeleteTable() throws InterruptedException {
		System.out.println("Delete Table Test Started");
		dbPage = new DashboardPage();
		driver.navigate().refresh();
		dbPage.getAppSettings().click();
		dbPage.getClickOnLinkForTables();
		System.out.println("Navigating to All Tables Page");
		Thread.sleep(5000);
		dbPage.deleteTable(newTableName); 
		Assert.assertFalse(dbPage.isElementPresentInTable(newTableName, dbPage.getListOfTablesAvailable()), "Table is not deleted and found in the tablelist");

	}

	@AfterClass(enabled=true)
	@Description("Tear down and close the browser")
	public void tearDown() {
		if (driver != null) {
			driver.quit(); //  Close the driver after tests
			System.out.println("Browser closed successfully!");
		}
	}

	/*
	 * Method to enter details in Create new table Dialog box
	 * @param String tableName
	 */
	private void enterDialogInfo(String tableName) throws InterruptedException {
		dbPage = new DashboardPage();
		WaitUtils.waitForElementToBeClickable(driver, dbPage.getNewButton(), 10);
		dbPage.getNewButton().click();
		System.out.println("clicked on New button");
		WaitUtils.waitForElementToBeVisible(driver, dbPage.getFromScratchOption(), 10);
		dbPage.getFromScratchOption().click();
		WaitUtils.waitForElementToBeVisible(driver, dbPage.getNewTableDialog(), 10);
		System.out.println("New Table dialog is appeared");
		dbPage.getTableName().sendKeys(tableName);

		dbPage.getInputSingleRecord().sendKeys("This is Test Input1");
		dbPage.getInputDescription().sendKeys("Desc");


	}


}
