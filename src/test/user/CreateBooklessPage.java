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

public class CreateBooklessPage extends TestBase {
	Utilities myUtility = new Utilities();
	Navigation navigation = new Navigation();

	public CreateBooklessPage(String location) throws BiffException, IOException, InterruptedException {
	}

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {
		initializeDriver();
		Login.performLogin();
		// navigation.navigateToAddPage();
		// navigation.navigateToCreateBook();
	}

	@Test
	public void testCreateBooklessPagePositiveScenarios() throws Exception {
		if (myUtility.toBeExecuted("CreateBooklessPage"))
			try {
				String positiveBooklessPageName = ConfigurationProperty.xlData.getValueFor("CreateBooklessPage", "PageName");

				CreateBooklessPageDetails myBooklessPage = CreateBooklessPageDetails.getBooklessPageDetails(positiveBooklessPageName);
				navigation.navigateToCreateBooklessPage();
				CreateBooklessPageAction.createBooklessPage(myBooklessPage);
				SearchPage searchPage = new SearchPage();
				assertTrue(searchPage.isBooklessPageCreated(myBooklessPage.getBooklessPageName()), "Created Bookless Page is not found");
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