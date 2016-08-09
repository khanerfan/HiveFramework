package lib.common;

import java.awt.AWTException;
import java.io.IOException;
import java.util.NoSuchElementException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebPage {
	private String location;
	protected static TestDriver driver;

	public WebPage() throws BiffException, IOException, InterruptedException {
		super();
//		this.setLocation();
		driver = TestDriver.getInstance();

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static void clickOn(String pageName, String buttonName) throws BiffException, IOException {
//		waitForElementPresent(pageName, buttonName);
		driver.getElement(pageName, buttonName).click();

	}
	
	public static void openNewTab(String sheetName, String elementName) throws BiffException, IOException, org.openqa.selenium.NoSuchElementException, AWTException
	{
		driver.openNewTab(sheetName, elementName);
	}
	

	public void findElement(String sheetName, String elementName) throws BiffException, IOException {
		driver.findElement(sheetName, elementName);
	}

	public static void loadUrl(String sheetName, String elementName) throws BiffException, IOException {
		driver.loadURL(ConfigurationProperty.xlData.getValueFor(sheetName, elementName));
	}

	public void mouseOver(String sheetName, String menuElement, String elementName) {

		driver.mouseOver(sheetName, menuElement, elementName);
	}

	public static void doubleClickOn(String sheetName, String elementName) throws BiffException, NoSuchElementException, IOException {
		WebElement element = driver.getElement(sheetName, elementName);
		TestDriver.doubleClickOn(element);
	}

	public static void doubleClickOnDirectly(String cXPath) throws BiffException, NoSuchElementException, IOException {
		// WebElement element = driver.getElement(sheetName, elementName);
		driver.doubleClickOnDirectly(cXPath);
	}

	public static String getElement(String sheetName, String elementName) throws BiffException, IOException, NoSuchElementException {
		return driver.getElement(sheetName, elementName).getText();

	}

	public WebElement getElementDirectly(String xPathLocation) throws BiffException, IOException, NoSuchElementException {
		return driver.getElementDirectly(xPathLocation);
	}

	public static String getElementValueDirectly(String xPathLocation) throws BiffException, IOException, NoSuchElementException {
		return driver.getElementDirectly(xPathLocation).getText();
	}

	public static String savePageSource() throws IOException {
		System.out.println("Store page Source");
		return driver.savePageSource();
	}

	public void selectFrame(String sheetName, String elementName) throws BiffException, NoSuchElementException, IOException {
		driver.selectFrame(sheetName, elementName);
	}

	public static void selectValueFromDropdown(String pageName, String dropdownName, String valueToSelect) throws BiffException, IOException {
		new Select(driver.getElement(pageName, dropdownName)).selectByVisibleText(valueToSelect);
	}

	public static void clickElementInGrid(String pageName, String tableName, String valueInRow, int columnNumber) throws BiffException, IOException {
		int totalRows = getGridRowCount(pageName, tableName);
		int totalColumns = getGridColumnCount(pageName, tableName);
		System.out.println("total Rows = " + totalRows + " and totalCol = " + totalColumns);

		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {

			for (int colIndex = 1; colIndex <= totalColumns; colIndex++) {
				String generatedXPathForColumn = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr[" + rowIndex + "]/td[" + colIndex + "]");
				String valueInCell = driver.getElementDirectly(generatedXPathForColumn).getText();
				if (valueInRow.equalsIgnoreCase(valueInCell)) {
					String generatedXPathForEditColumn = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr[" + rowIndex + "]/td[" + columnNumber + "]/input");
					System.out.println("generatedXPathForEditColumn = " + generatedXPathForEditColumn);
					clickOnButton(generatedXPathForEditColumn);
					break;
				}
			}
		}
	}

	public static void clickOnButton(String elementName) throws BiffException, NoSuchElementException, IOException {
		System.out.println("Clicking THE ..." + elementName);
		 waitForElementPresentDirectly(elementName);
		driver.clickOn(elementName);

	}

	public static int getGridColumnCount(String pageName, String tableName) throws BiffException, NoSuchElementException, IOException {

		String actualTableName = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr[1]/td");
		return driver.getElementsDirectly(actualTableName).size();
	}

	public static int getGridRowCount(String pageName, String tableName) throws BiffException, NoSuchElementException, IOException {
		System.out.println("pageName = " + pageName + "tableName = " + tableName);
		String actualTableName = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr");
		System.out.println("actualTableName = " + actualTableName);
		return driver.getElementsDirectly(actualTableName).size();
	}

	public void openMenu(String menuName) throws BiffException, IOException {
		if ("Operations".equals(menuName))
			driver.getElement("Navigation", menuName).click();
	}

	public static void enterValue(String pageName, String fieldName, String fieldValue) throws BiffException, IOException, InterruptedException {
		driver.getElement(pageName, fieldName).sendKeys(fieldValue);

	}

	public static void clearField(String pageName, String fieldName) throws BiffException, IOException, InterruptedException {
		driver.getElement(pageName, fieldName).clear();

	}

	public void navigateTo(String pageName) throws BiffException, IOException, InterruptedException {
		if ("ManageOrganizations".equals(pageName)) {
			driver.getElement("Navigation", "Operations").click();
			// waitForAjax(10);
			driver.getElement("Navigation", pageName).click();
		}
	}

	public static void waitForElementPresent(String sheetName, String elementName) throws BiffException, IOException {
		driver.waitForElementPresent(sheetName, elementName);
	}

	public static void waitForElementPresentDirectly(String elementName) throws BiffException, IOException {
		driver.waitForElementPresentDirectly(elementName);
		System.out.println("Element" + elementName + "Found");
	}

	public boolean isElementPresent(String sheetName, String elementName) {
		return driver.isElementPresent(sheetName, elementName);
	}

	public static boolean isElementPresent(String elementName) {
		return driver.isElementPresent(elementName);
	}

	public static int getElementsCount(String xPathLocation) throws IOException, BiffException, NoSuchElementException {
		return driver.getElementsDirectly(xPathLocation).size();
	}

	public String getElementByAttribute(String xPathLocation, String attributeName) throws IOException, BiffException, NoSuchElementException {
		return driver.getElementByAttribute(xPathLocation, attributeName);
	}

	public void scrollingToBottomofAPage() throws InterruptedException {
		driver.scrollingToBottomofAPage();
	}

	public static void firefoxsignin(String sheetName, String elementName) throws BiffException, IOException {
		driver.getElement(sheetName, elementName);
		
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", driver);
		waitForElementPresent(sheetName, elementName);
//		driver.firefoxsignin(sheetName, elementName); 
	}

}