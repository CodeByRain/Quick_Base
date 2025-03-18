package pageObjects;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * DashboadPage class contains all the locators of Dashboad page
 */
public class DashboardPage extends Base{


	public By myAppsText = By.xpath("//h2[@class=\"css-17g6zul e5slb5i2\"]");
	public By projectName = By.xpath("//button[@aria-label=\"Simple Project Manager - Craft Demo (VARSHA)\"]");
	public By appSettings = By.xpath("//div[text()='App settings']");
	public By tableButton = By.xpath("//a[contains(@id,'tables')]");
	public By newButton = By.xpath("//span[contains(@class, 'Icon DownArrow Settings')]");
	public By fromScratchOption = By.xpath("//a[contains(@id,'btnNewTableAppHome')]");
	public By newTableDialog = By.xpath("//div[@data-test-id=\"newTableDialog\"]");
	public By tableName = By.xpath("//input[@autocapitalize=\"none\"]");
	public By inputSingleRecord = By.xpath("//input[@data-test-id=\"SingleRecordInput\"]");
	public By inputDescription = By.xpath("//textarea[@data-test-id=\"TableDescription\"]");
	public By buttonCancelDialog = By.xpath("//button[@data-test-id=\"dialogCancelButton\"]");
	public By buttonCreateTable = By.xpath("//button[@data-test-id=\"dialogOkButton\"]");
	public By newFieldsDialog = By.xpath("//div[@data-test-id=\"newFieldDialog\"]");
	public By inputAddFieldName = By.xpath("//input[contains(@id,\"fieldName\")]");
	public By buttonFieldsDialogOk = By.xpath("//button[contains(@class,\"dialogOkButton\")]");
	public By buttonFieldsDialogCancel = By.xpath("//button[contains(@class,\"dialogCancelButton\")]");
	public By tableColumnNames = By.xpath("//div[contains(@class,\"ui-jqgrid-hbox\")]//thead/tr/th");
	public By tableRows = By.xpath("//table[@id=\"appTablesListTable\"]/tbody");
	public By fieldsTable = By.xpath("//table[@id=\"fieldsTable\"]");
	public By linkForTables = By.xpath("//a[@id=\"appSettingsNav_tables\"]");
	public By deleteDialog = By.xpath("//div[@id='dialogDeleteTable']");
	public By buttonDeleteOnDelDialog = By.xpath("//button[contains(@class, \"Vibrant Confirm\")]");
	public By inputTypeYesField = By.xpath("//input[@id=\"typeYesField\"]");
	public By inputSearchTable = By.xpath("//input[@id=\"tablesSearch\"]");
	public By buttonSearchDelete = By.xpath("//a[@class=\"clearsearch Clear Icon\"]");
	public By messageTableExists = By.xpath("//p[contains(@class,\"errorText css-ct785q evcjqd60\")]");

	public WebElement getMyAppsText() {
		return driver.findElement(myAppsText);
	}

	public WebElement getProjectName() {
		return driver.findElement(projectName);
	}
	public WebElement getAppSettings() {
		return driver.findElement(appSettings);
	}
	public WebElement getTableButton() {
		return driver.findElement(tableButton);
	}
	public WebElement getNewButton() {
		return driver.findElement(newButton);
	}
	public WebElement getFromScratchOption() {
		return driver.findElement(fromScratchOption);
	}

	public WebElement getNewTableDialog() {
		return driver.findElement(newTableDialog);
	}
	public WebElement getTableName() {
		return driver.findElement(tableName);
	}

	public WebElement getInputSingleRecord() {
		return driver.findElement(inputSingleRecord);
	}

	public WebElement getInputDescription() {
		return driver.findElement(inputDescription);
	}
	public WebElement getButtonCancelDialog() {
		return driver.findElement(buttonCancelDialog);
	}
	public WebElement getButtonCreateTable() {
		return driver.findElement(buttonCreateTable);
	}
	public WebElement getNewFieldsDialog() {
		return driver.findElement(newFieldsDialog);
	}

	public WebElement getInputAddFieldName() {
		return driver.findElement(inputAddFieldName);
	}

	public WebElement getButtonFieldsDialogOk() {
		return driver.findElement(buttonFieldsDialogOk);
	}

	public WebElement getButtonFieldsDialogCancel() {
		return driver.findElement(buttonFieldsDialogCancel);
	}

	public List <WebElement> getTableHeaders() {
		return driver.findElements(tableColumnNames);
	}

	public  WebElement getListOfTablesAvailable() {
		return driver.findElement(tableRows);
	}


	public WebElement getTableNameAlreadyExistsMessage() {
		return driver.findElement(messageTableExists);
	}

	public void getClickOnLinkForTables() {
		driver.findElement(linkForTables).click();
	}

	public WebElement getInputSearchTable() {
		return driver.findElement(inputSearchTable);

	}

	public WebElement getButtonSearchDelete() {
		return driver.findElement(buttonSearchDelete);

	}


	public void deleteTable(String nameOftheTable) throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(getListOfTablesAvailable()));
		List<WebElement> rowsfortablelist = getListOfTablesAvailable().findElements(By.tagName("tr"));
		for (WebElement row : rowsfortablelist)
		{
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				wait.until(ExpectedConditions.textToBePresentInElement(cell, cell.getText().trim()));
				if (cell.getText().trim().equalsIgnoreCase(nameOftheTable))
				{
					System.out.println("Found name of the table");
					System.out.println("Name of the Table is : "+cell.getText().trim());
					Thread.sleep(5000);
					WebElement deleteIcon = row.findElement(By.xpath(".//td//span/a[@class=\"RowAction Delete Icon\"]"));		        	
					System.out.println("Value from another column: " + deleteIcon.getTagName());
					deleteIcon.click();
					Thread.sleep(5000);
					//table[@id="appTablesListTable"]//tr/td[contains(@class,"Movable")]/span/a[@class="RowAction Delete Icon"]

					new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(deleteDialog));
					driver.findElement(inputTypeYesField).click();
					driver.findElement(inputTypeYesField).sendKeys("YES");
					Thread.sleep(100);
					driver.findElement(buttonDeleteOnDelDialog).click();
					break;
				}

			}
		}
	}


	public WebElement getFieldsTable() {
		return driver.findElement(fieldsTable);
	}

	public List <WebElement> getAllRows() {

		return getFieldsTable().findElements(By.tagName("tr"));
	}



	public boolean isElementPresentInTable(String elementName, WebElement table) throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait until the table is visible
		wait.until(ExpectedConditions.visibilityOf(table));

		List <WebElement> getRows = table.findElements(By.tagName("tr"));

		for (WebElement row : getRows) {

			// Get all cells in the row
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				// a short wait before reading text
				wait.until(ExpectedConditions.textToBePresentInElement(cell, cell.getText().trim()));

				// Check if the cell text matches the target element name
				if (cell.getText().trim().equalsIgnoreCase(elementName)) {
					String nameOfTheElementFound = cell.getText();
					System.out.println("Name of the value found is : "+nameOfTheElementFound);
					return true; // Element is found
				}
			}
		}
		return false; // Element is not found
	}


}

