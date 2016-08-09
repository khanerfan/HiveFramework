package test.user;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.LogUtility;
import lib.common.Utilities;
import lib.common.Navigation;
import lib.common.TestBase;
import lib.common.WebLocation;
import lib.User.*;
import test.user.CreateBook;


public class AddPageInBook extends TestBase {
	Utilities myUtility = new Utilities();
	Navigation navigation =new Navigation();
	CreateBook createBook =new CreateBook();
	public AddPageInBook(String location) throws BiffException, IOException, InterruptedException {
	}

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {
		initializeDriver();
		Login.performLogin();
//		navigation.navigateToAddPage();
		navigation.navigateToCreateBook();
	}
	
	@Test
	public void testCreateBookPagePositiveScenarios() throws Exception {
		if (myUtility.toBeExecuted("AddPageInBook"))
			try {
				String positiveBookTitle = ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle");
				String positiveBookDescription = ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription");
				
				CreateBookDetails myBook = CreateBookDetails.getCreateBookDetails(positiveBookTitle, positiveBookDescription);
				
				CreateBookPage.createBook(myBook);
				
				SearchPage searchPage=new SearchPage();
				searchPage.openCreatedBook(myBook.getBookTitle());
				String positiveBookPageName = ConfigurationProperty.xlData.getValueFor("AddPageInBook", "PageName");
				
				AddPageInBookDetails myBookPage = AddPageInBookDetails.getAddPageInBookDetails(positiveBookPageName);
				navigation.navigateToAddPage();
				AddPageInBookPage.createPageInBook(myBookPage);
				assertTrue(searchPage.isBookPageCreated(myBook.getBookTitle(),myBookPage.getBookPageName()), "Created Book Page is not found");
			} catch (Exception e) {
				fail("Exception thrown" + ExceptionUtils.getMessage(e));
				LogUtility.log("Message = " + ExceptionUtils.getMessage(e));
				LogUtility.log("Stack = " + ExceptionUtils.getStackTrace(e));
			}
	}
	

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		//closebrowser();
	}
}