package lib.User;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.WebPage;

public class AddPageInBookPage extends WebPage {

	public AddPageInBookPage() throws BiffException, IOException, InterruptedException {
		super();
	}

	private static void clickButton(String buttonName) throws Exception {
		clickOn("AddPageInBook", buttonName);
	}

	public static void createPageInBook(AddPageInBookDetails addPageInBookDetails) throws Exception {
//		waitForElementPresent("PageName", addPageInBookDetails.getBookPageName());
		enterValue("PageName", addPageInBookDetails.getBookPageName());
		// Thread.sleep(1000);
		clickButton("FinishEditing");
	}

	private static void enterValue(String fieldName, String fieldValue) throws BiffException, IOException, InterruptedException {
		waitForElementPresent("AddPageInBook", fieldName);
		Thread.sleep(1000);
		clearField("AddPageInBook", fieldName);
		enterValue("AddPageInBook", fieldName, fieldValue);
	}
}
