package lib.User;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.WebPage;

public class CreateBooklessPageAction extends WebPage {

	public CreateBooklessPageAction() throws BiffException, IOException, InterruptedException {
		super();
	}

	private static void clickButton(String buttonName) throws Exception {
		clickOn("CreateBooklessPage", buttonName);
	}

	public static void createBooklessPage(CreateBooklessPageDetails createBooklessPageDetails) throws Exception {
//		waitForElementPresent("PageName", addPageInBookDetails.getBookPageName());
		enterValue("PageName", createBooklessPageDetails.getBooklessPageName());
		// Thread.sleep(1000);
		clickButton("FinishEditing");
	}

	private static void enterValue(String fieldName, String fieldValue) throws BiffException, IOException, InterruptedException {
		waitForElementPresent("CreateBooklessPage", fieldName);
		clearField("CreateBooklessPage", fieldName);
		enterValue("CreateBooklessPage", fieldName, fieldValue);
	}
}
