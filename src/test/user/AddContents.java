package test.user;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import enums.ContentType;

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

public class AddContents extends TestBase {

	Utilities myUtility = new Utilities();
	Navigation navigation = new Navigation();

	public AddContents() throws BiffException, IOException, InterruptedException {
	}

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {
		initializeDriver();
		Login.performLogin();
//		navigation.navigateToCreateBook();
	}

	@Test
	public void testAddContentsInBookPage() throws Exception {
		if (myUtility.toBeExecuted("AddContentsInPage"))
			try {
				navigation.navigateToCreateBook();
				String positiveBookTitle = ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle");
				String positiveBookDescription = ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription");

				CreateBookDetails myBook = CreateBookDetails.getCreateBookDetails(positiveBookTitle, positiveBookDescription);

				CreateBookPage.createBook(myBook);

				SearchPage searchPage = new SearchPage();
				searchPage.openCreatedBook(myBook.getBookTitle());

				String positiveBookPageName = ConfigurationProperty.xlData.getValueFor("AddPageInBook", "PageName");

				AddPageInBookDetails myBookPage = AddPageInBookDetails.getAddPageInBookDetails(positiveBookPageName);
				navigation.navigateToAddPage();
				AddPageInBookPage.createPageInBook(myBookPage);
				System.out.println("page created");
				searchPage.openCreatedBookPage(myBook.getBookTitle(), myBookPage.getBookPageName());
				System.out.println("page opened");

				String contentName = ConfigurationProperty.xlData.getValueFor("AddContentsInPage", "AddContentName");
				System.out.println(contentName);
				int numberOfContents = Integer.valueOf(ConfigurationProperty.xlData.getValueFor("AddContentsInPage", "NumberOfContents"));
				System.out.println(numberOfContents);
				navigation.navigateToEditPage();
				AddContentsInPage.addContentToPage(Utilities.ConvertContentType(contentName), numberOfContents);
				// assertTrue(searchPage.isBookPageCreated(myBook.getBookTitle(),myBookPage.getBookPageName()),
				// "Created Book Page is not found");
			} catch (Exception e) {
				fail("Exception thrown" + ExceptionUtils.getMessage(e));
				LogUtility.log("Message = " + ExceptionUtils.getMessage(e));
				LogUtility.log("Stack = " + ExceptionUtils.getStackTrace(e));
			}
	}

	@Test
	public void testAddContentsInBooklessPage() throws Exception {
		if (myUtility.toBeExecuted("AddContentsInPage"))
			try {
				String positiveBooklessPageName = ConfigurationProperty.xlData.getValueFor("CreateBooklessPage", "PageName");

				CreateBooklessPageDetails myBooklessPage = CreateBooklessPageDetails.getBooklessPageDetails(positiveBooklessPageName);
				navigation.navigateToCreateBooklessPage();
				CreateBooklessPageAction.createBooklessPage(myBooklessPage);
				
				SearchPage searchPage = new SearchPage();
				searchPage.openCreatedBooklessPage(myBooklessPage.getBooklessPageName());
				
				String contentName = ConfigurationProperty.xlData.getValueFor("AddContentsInPage", "AddContentName");
				System.out.println(contentName);
				int numberOfContents = Integer.valueOf(ConfigurationProperty.xlData.getValueFor("AddContentsInPage", "NumberOfContents"));
				System.out.println(numberOfContents);
				navigation.navigateToEditPage();
				AddContentsInPage.addContentToPage(Utilities.ConvertContentType(contentName), numberOfContents);
				// assertTrue(searchPage.isBookPageCreated(myBook.getBookTitle(),myBookPage.getBookPageName()),
				// "Created Book Page is not found");
			} catch (Exception e) {
				fail("Exception thrown" + ExceptionUtils.getMessage(e));
				LogUtility.log("Message = " + ExceptionUtils.getMessage(e));
				LogUtility.log("Stack = " + ExceptionUtils.getStackTrace(e));
			}
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		// closebrowser();
	}
}
