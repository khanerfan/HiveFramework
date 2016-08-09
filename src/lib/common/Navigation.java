package lib.common;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;

import jxl.read.biff.BiffException;

public class Navigation extends WebPage {

	public Navigation() throws BiffException, IOException, InterruptedException {
		//super(ConfigurationProperty.xlData.getValueFor("Properties", "baseurl"));
		super();
	}

	public void navigateToCreateBook() throws BiffException, IOException {
		clickOn("CapturedIcon", "CapturedButton");
		clickOn("CreateBook", "NewBook");
	}

	public void navigateToAddPage() throws BiffException, IOException, InterruptedException {
//		clickOn("AddPageInBook", "MyLibrarylink");
//		clickOn("AddPageInBook", "BooksLink");
//		waitForElementPresent("AddPageInBook", "SortFilter");
//		clickOn("AddPageInBook", "SortFilter");
//		clickOn("AddPageInBook", "DateCreated");
//		clickOn("AddPageInBook", "OpenBook");
		clickOn("AddPageInBook", "NewPage");
	}
	
	public void navigateToEditPage() throws BiffException, IOException {
//		waitForElementPresent("EditPageInBook", "EditPageButton");
		clickOn("AddContentsInPage", "EditPageButton");
	}
	public void navigateToCreateBooklessPage() throws BiffException, IOException {
		clickOn("CapturedIcon", "CapturedButton");
		clickOn("CreateBooklessPage", "NewPage");
	}

	public void navigateToMyLibrary() throws BiffException, IOException, NoSuchElementException, AWTException {
		waitForElementPresent("MyLibrary", "MyLibraryLink");
		
		clickOn("MyLibrary", "MyLibraryLink");
		waitForElementPresent("MyLibrary", "BooksLink");
		openNewTab("MyLibrary", "BooksLink");
//		clickOn("MyLibrary", "BooksLink");
	}
	
	public void navigateToMyLibraryPages() throws BiffException, IOException {
		waitForElementPresent("MyLibrary", "MyLibraryLink");
		clickOn("MyLibrary", "MyLibraryLink");
		clickOn("MyLibrary", "PagesLink");
	}
	
	public void navigateToMyFiles() throws BiffException, IOException {
		clickOn("MyFiles", "MyFile");
	}

	public void navigateToGroups() throws BiffException, IOException {
		clickOn("Groups", "Connections");
		clickOn("Groups", "MyGroups");
	}
}