package lib.common;

import java.io.IOException;
import java.util.NoSuchElementException;

import jxl.read.biff.BiffException;

public class WebPageVerifier {
	protected  TestDriver driver;
	private static double amount = 0;
	

	public WebPageVerifier() throws BiffException, IOException {
		super();
		driver = TestDriver.getInstance();

	}
	public double  getValue(String sheetName, String elementName) throws Exception {
		String str= ConfigurationProperty.xlData.getValueFor(sheetName, elementName);
		LogUtility.log("Value" + str);
		amount= Double.parseDouble(str);
		LogUtility.log("Get Balance" + amount);
		return amount;
	}

	public boolean verifyErrorMessage(String pageName, String elementName, String expectedErrorMessage) throws Exception {
		String actualMessage = driver.getElement(pageName, elementName).getAttribute("data-original-title").toString();
		if (expectedErrorMessage.equalsIgnoreCase(actualMessage))
			return true;
		else {
			LogUtility.log("Verification failed.\nExpected Result = " + expectedErrorMessage + "\nActual Result = " + actualMessage);
			return false;
		}
	}
	
	public boolean verifyBalance(double actualResult, double expectedResult) throws Exception {	
		if (actualResult==expectedResult)
		{
			LogUtility.log("Balance Verified Successfully");
			return true;
		}
		else 
		{
			LogUtility.log("Verification failed.\nExpected Result = " + expectedResult + "\nActual Result = " + actualResult);
			return false;
		}
	}
	
	
	
	public  double getAmount(String sheetName, String elementName, String balanceType) throws Exception {
		
		if (balanceType.equalsIgnoreCase("card")) {
			String AvailableBalance = driver.getElement(sheetName, elementName).getText();
			String[] BAL = AvailableBalance.split(" ");
			for (int i = 0; i < BAL.length; ++i) {
				LogUtility.log(i + BAL[i]);
			}
			String str = BAL[1].replace(",", "").substring(1);
			amount = Double.parseDouble(str);
			LogUtility.log("Get Balance" + amount);
			return amount;
		} else if (balanceType.equalsIgnoreCase("account")) {
			String AvailableBalance = driver.getElement(sheetName, elementName).getText();
			String[] BAL = AvailableBalance.split(" ");
			for (int i = 0; i < BAL.length; ++i) {
				LogUtility.log(i + BAL[i]);
			}
			String str = BAL[2].replace(",", "");
			amount = Double.parseDouble(str);
			LogUtility.log("Get Balance" + amount);
			return amount;
		}	
		else
		{
			return amount;
		}
	}
	
	
	public boolean getPageSource(String value)
	{
	  return driver.getPageSource(value);
	}

	public String sendValue(String sheetName, String elementName) throws BiffException, IOException {
		return ConfigurationProperty.xlData.getValueFor(sheetName, elementName);
	}

	public boolean verifyLabelText(String pageName, String elementName) throws Exception {

		String expectedResult = ConfigurationProperty.xlData.getValueFor(pageName, elementName);
		String actualResult = driver.getElement(pageName, elementName).getText();
		if (expectedResult.equals(actualResult))
			return true;
		else {
			LogUtility.log("Verification failed.\nExpected Result = " + expectedResult + "\nActual Result = " + actualResult);
			return false;
		}
	}

	public boolean verifyLabelText(String pageName, String elementName, String expectedResult) throws Exception {
		String actualResult = driver.getElement(pageName, elementName).getText();
		if (expectedResult.equals(actualResult))
			return true;
		else {
			LogUtility.log("Verification failed.\nExpected Result = " + expectedResult + "\nActual Result = " + actualResult);
			return false;
		}
	}

	public boolean getTextByAttribute(String pageName, String elementName, String attribute, String expectedResult) throws Exception {
		String actualResult = driver.getElement(pageName, elementName).getAttribute(attribute).toString();
		if (expectedResult.equals(actualResult))
			return true;
		else {
			LogUtility.log("Verification failed.\nExpected Result = " + expectedResult + "\nActual Result = " + actualResult);
			return false;
		}
	}
	
	
	
	

	public boolean getTextByAttribute(String pageName, String elementName, String attribute) throws Exception {
		String expectedResult = ConfigurationProperty.xlData.getValueFor(pageName, elementName);
		String actualResult = driver.getElement(pageName, elementName).getAttribute(attribute).toString();
		if (expectedResult.equals(actualResult))
			return true;
		else {
			LogUtility.log("Verification failed.\nExpected Result = " + expectedResult + "\nActual Result = " + actualResult);
			return false;
		}
	}

	public String getFieldValue(String pageName, String elementName) throws BiffException, NoSuchElementException, IOException {
		return driver.getElement(pageName, elementName).getText();

	}
	public String getFieldAttribute(String pageName, String elementName) throws BiffException, NoSuchElementException, IOException {
		return driver.getElement(pageName, elementName).getAttribute("value");
	}
		
	public boolean isElementPresent(String sheetName, String elementName) throws BiffException, IOException {
		return driver.isElementPresent(sheetName, elementName);
	}

	public String getFieldValueFromLabel(String pageName, String elementName) throws BiffException, NoSuchElementException, IOException {
		return driver.getElement(pageName, elementName).getText();
	}

	public boolean verifyElementPresent(String pageName, String elementName) throws BiffException, IOException, InterruptedException {
		try {
			driver.getElement(pageName, elementName);
			return true;
		} catch (NoSuchElementException nse) {
			return false;
		}
	}

	public boolean isAt(String pageName) throws BiffException, IOException {
		if (ConfigurationProperty.xlData.getValueFor(pageName, "Header").equalsIgnoreCase(ConfigurationProperty.xlLocation.getValueFor(pageName, "Header")))
			return true;
		else
			return false;
	}

	public boolean isElementVisible(String pageName, String elementName) throws BiffException, IOException {
		return driver.getElement(pageName, elementName).isDisplayed();
	}

	public boolean verifyValueInGrid(String pageName, String tableName, String expectedValue) throws BiffException, IOException {
		int totalRows = getGridRowCount(pageName, tableName);
		int totalColumns = getGridColumnCount(pageName, tableName);

		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
			for (int colIndex = 1; colIndex <= totalColumns; colIndex++) {
				String generatedXPathForColumn = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr[" + rowIndex + "]/td[" + colIndex + "]");
				String valueInCell = driver.getElementDirectly(generatedXPathForColumn).getText();
				if (expectedValue.equalsIgnoreCase(valueInCell))
					return true;
			}
		}
		return false;
	}

	private int getGridColumnCount(String pageName, String tableName) throws BiffException, NoSuchElementException, IOException {

		String actualTableName = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr[1]/td");
		return driver.getElementsDirectly(actualTableName).size();
	}

	private int getGridRowCount(String pageName, String tableName) throws BiffException, NoSuchElementException, IOException {
		String actualTableName = ConfigurationProperty.xlLocation.getValueFor(pageName, tableName).concat("/tbody/tr");
		return driver.getElementsDirectly(actualTableName).size();
	}
}