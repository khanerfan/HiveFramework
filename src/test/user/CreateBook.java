 package test.user;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.LogUtility;
import lib.common.Utilities;
import lib.common.Navigation;
import lib.common.TestBase;
import lib.common.WebLocation;
import lib.User.*;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import verification.user.*;

public class CreateBook extends TestBase {
	Utilities myUtility = new Utilities();
	Navigation navigation =new Navigation();
	public CreateBook() throws BiffException, IOException, InterruptedException {
	}
	
	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {
		initializeDriver();
		Login.performLogin();
		navigation.navigateToCreateBook();
	}

	@Test
	public void testCreateBookEmpty() throws Exception {
		if (myUtility.toBeExecuted("CreateBook"))
			try {
				CreateBookPage createBookPage = new CreateBookPage();
				
				CreateBookDetails myBook = CreateBookDetails.getCreateBookDetails("","");
 
				CreateBookPage.createBook(myBook);
				CreateBookVerifier bookVerifier = new CreateBookVerifier(createBookPage);
				assertTrue(bookVerifier.verifyValidationErrorMessage());
			} catch (Exception e) {
				fail("Exception thrown" + ExceptionUtils.getMessage(e));
				LogUtility.log("Message = " + ExceptionUtils.getMessage(e));
				LogUtility.log("Stack = " + ExceptionUtils.getStackTrace(e));
			}

	}
	
	@Test
	public void testCreateBookPositiveScenarios() throws Exception {
		if (myUtility.toBeExecuted("CreateBook"))
			try {
				String positiveBookTitle = ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle");
				String positiveBookDescription = ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription");
				
				CreateBookDetails myBook = CreateBookDetails.getCreateBookDetails(positiveBookTitle, positiveBookDescription);
				
				CreateBookPage.createBook(myBook);
				SearchPage searchPage=new SearchPage();
				assertTrue(searchPage.isBookCreated(myBook.getBookTitle()), "Created Book is not found");
			} catch (Exception e) {
				fail("Exception thrown" + ExceptionUtils.getMessage(e));
				LogUtility.log("Message = " + ExceptionUtils.getMessage(e));
				LogUtility.log("Stack = " + ExceptionUtils.getStackTrace(e));
			}
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
//		closebrowser();
	}
}