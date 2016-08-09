package lib.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class TestDriver {
	public static WebDriver driver;
	private static TestDriver instance;
	private static final int TIMEOUT_SECONDS = 60;

	private TestDriver() throws BiffException, IOException {
		initialize();
	}

	public static void initialize() {
		String browser = ConfigurationProperty.xlData.getValueFor("Properties", "browser");
		System.out.println("Browser = " + browser);

		if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//src/ExecutableJarFiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("HeadLess")) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src/ExecutableJarFiles/chromedriver.exe");
//			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//			driver = new HtmlUnitDriver(BrowserVersion.CHROME,true);
//			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				//driver = new HtmlUnitDriver(capabilities);
				capabilities.setJavascriptEnabled(false);
				java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src/ExecutableJarFiles/chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigurationProperty.xlData.getValueFor("Properties", "timeout")), TimeUnit.SECONDS);
	}

	public static void closebrowser() throws IOException {
		System.out.println("Closing the Browser");
		driver.quit();
		// driver.close();
		// driver.
	}

	public static void doubleClickOn(WebElement element) {

		Actions action = new Actions(driver).doubleClick(element);
		action.build().perform();
	}

	public void openNewTab(String sheetName, String elementName) throws NoSuchElementException, BiffException, IOException, AWTException {

		waitForElementPresent(sheetName, elementName);
		driver.findElement(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))).sendKeys(Keys.chord(Keys.CONTROL,"t"));
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		String newTabURL =fetchURLFromLink(sheetName,elementName);
		driver.switchTo().window(tabs.get(1));
		driver.navigate().to(newTabURL);
//		driver.close();
//		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))).sendKeys(Keys.chord(Keys.CONTROL,"\t"));
	}
	
	public String fetchURLFromLink(String sheetName, String elementName)
	{
		return driver.findElement(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))).getAttribute("href");

	}
	
	public void navigateToNextTab(ArrayList<String> tabs)
	{
		driver.switchTo().window(tabs.get(1));
	}
	
	public void navigateToPreviousTab()
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	
	public void rightClick(WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).click(element).keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();
			Thread.sleep(5000);
			System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
	}
	
	public void mouseOver(String sheetName, String menuElement, String elementName) {
		Actions actions = new Actions(driver);
		// menuElement = driver.findElement(By.xpath(menuElement));
		// actions.moveToElement(driver.findElement(By.xpath(menuElement))).moveToElement(driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName,
		// elementName)))).click();
		actions.moveToElement(driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, menuElement))))
				.moveToElement(driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))).click();

	}

	public void dragAndDrop(String sheetName, String source, String target) {
		Actions actions = new Actions(driver);

		WebElement targetWebElement = driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, target)));
		WebElement sourceWebElement = driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, source)));
		// actions.clickAndHold(Source).moveToElement(Target).release(Target).perform();
		actions.dragAndDrop(sourceWebElement, targetWebElement).perform();
	}

	public void selectFrame(String sheetName, String elementName) throws IOException, BiffException, NoSuchElementException {
		driver.switchTo().frame(driver.findElement(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))));
	}

	public void findElement(String sheetName, String elementName) throws IOException, BiffException, NoSuchElementException {
		driver.findElement(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName))));
	}

	public void doubleClickOn(String sheetName, String elementName) throws BiffException, NoSuchElementException, IOException {
		WebElement element = driver.findElement(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName))));
		doubleClickOn(element);
	}

	public void doubleClickOnDirectly(String elementName) throws BiffException, NoSuchElementException, IOException {
		WebElement element = driver.findElement(By.xpath(elementName));
		doubleClickOn(element);
	}

	public void setAttribute(String sheetName, String atrributeValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('//id of element').setAttribute(sheetName, atrributeValue)");
	}

	public void scrollingToBottomofAPage() throws InterruptedException {
		long scrollValue = Long.parseLong(ConfigurationProperty.xlData.getValueFor("Properties", "timeout"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		for (int second = 0;; second++) {
			if (second >= 20) {
				break;
			}
			jse.executeScript("window.scrollBy(0," + scrollValue + ")", ""); // y
																				// value
																				// '800'
																				// can
																				// be
																				// altered
			Thread.sleep(2000);
		}
	}

	public String getElementByAttribute(String xPathLocation, String attributeName) throws IOException, BiffException, NoSuchElementException {
		return getElementDirectly(xPathLocation).getAttribute(attributeName);
	}

	public String savePageSource() throws IOException {
		return driver.getPageSource();

	}

	public boolean getPageSource(String value) {
		return driver.getPageSource().contains(value);
	}

	public void clickOn(String elementName) throws BiffException, IOException, NoSuchElementException {
		driver.findElement(By.xpath(elementName)).click();
	}

	public void refreshthePage() throws BiffException, IOException, NoSuchElementException {
		driver.navigate().refresh();
	}

	public void waitForAjax(int timeoutInSeconds) throws InterruptedException {
		System.out.println("Checking active ajax calls by calling jquery.active");
		if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
			for (int i = 0; i < timeoutInSeconds; i++) {
				Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");

				if (numberOfAjaxConnections instanceof Long) {
					Long n = (Long) numberOfAjaxConnections;
					System.out.println("Number of active jquery ajax calls: " + n);
					if (n.longValue() == 0L)
						break;
				}
				Thread.sleep(1000);
			}
		} else {
			System.out.println("Web driver: " + driver + " cannot execute javascript");
		}
	}

	public WebElement getElement(String sheetName, String elementName) throws BiffException, IOException, NoSuchElementException {

		WebElement element = driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)));
		// System.out.println("Element " + elementName + " found on page " +
		// sheetName + ". Returning the element now");
		return element;
	}

	public WebElement getElementByCSS(String sheetName, String elementName) throws BiffException, IOException, NoSuchElementException {

		WebElement element = driver.findElement(By.cssSelector(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)));
		return element;
	}

	public List<WebElement> getElements(String sheetName, String elementName) throws BiffException, IOException, NoSuchElementException {
		List<WebElement> elements = driver.findElements(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)));
		return elements;
	}

	public List<WebElement> getGridElements(String sheetName, String elementName, String elementType) throws BiffException, IOException, NoSuchElementException, NullPointerException {
		List<WebElement> elements = null;
		if (elementType.equalsIgnoreCase("rows"))
			elements = driver.findElements(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName).concat("/tbody/tr")));
		else if (elementType.equalsIgnoreCase("columns"))
			elements = driver.findElements(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName).concat("/tbody/tr/td")));
		return elements;
	}

	public String getValueInGrid(String pageName, String gridName, int row, int column) throws NoSuchElementException, BiffException, IOException, NullPointerException {
		List<WebElement> allRows = getGridElements(pageName, gridName, "rows");
		return allRows.get(row).findElements(By.xpath("//td")).get(column).getText();
	}

	public int getRowNumberFromGrid(String pageName, String gridName, String valueInFirstColumn) throws NoSuchElementException, BiffException, IOException, NullPointerException {
		List<WebElement> allRows = getGridElements(pageName, gridName, "rows");
		for (WebElement element : allRows) {
			if (element.findElement(By.xpath("//td")).getText().equals(valueInFirstColumn))
				return allRows.indexOf(element);
			else
				return -1;
		}
		return -1;
	}

	public String getCellValueFromGrid(String pageName, String gridName, int row, int column) throws NoSuchElementException, BiffException, IOException, NullPointerException {
		List<WebElement> allRows = getGridElements(pageName, gridName, "rows");
		return allRows.get(row).findElements(By.xpath(ConfigurationProperty.xlLocation.getValueFor(pageName, gridName))).get(column).getText();
	}

	public int getColumnNumberFromGrid(String pageName, String gridName, String valueInFirstRow) throws NoSuchElementException, BiffException, IOException, NullPointerException {
		List<WebElement> allColumns = getGridElements(pageName, gridName, "columns");
		for (WebElement element : allColumns) {
			if (element.getText().equals(valueInFirstRow))
				return allColumns.indexOf(element);
		}
		return -1;
	}

	public WebElement getElementDirectly(String xPathLocation) throws NoSuchElementException {
		return driver.findElement(By.xpath(xPathLocation));
	}

	public List<WebElement> getElementsDirectly(String xPathLocation) throws NoSuchElementException {
		return driver.findElements(By.xpath(xPathLocation));
	}

	public void loadURL(String url) {
		driver.get(url);
	}

	public String getPageHeader(String pageName) {
		return driver.findElement(By.xpath("//div[@id='content']/h3")).getText();
	}

	public boolean isElementPresent(String sheetName, String elementName) {
		try {
			if (driver.findElement(By.xpath(ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName))) != null)
				return true;
			else
				return false;
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	public boolean isElementPresent(String elementName) {
		try {
			if (driver.findElement(By.xpath(elementName)) != null)
				return true;
			else
				return false;
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	public static TestDriver getInstance() throws BiffException, IOException {
		if (instance == null)
			instance = new TestDriver();
		return instance;

	}

	public static void waitForElementPresent(String sheetName, String elementName) throws IOException, BiffException, NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))));
	}

	public void waitForElementPresentDirectly(String elementName) throws IOException, BiffException, NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementName)));
	}

	public static void firefoxsignin(String sheetName, String elementName) throws BiffException, IOException {
//		driver.getElement(sheetName, elementName);

//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", we);

		waitForElementPresent(sheetName, elementName);
	}

}