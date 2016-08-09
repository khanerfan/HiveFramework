package lib.User;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import jxl.read.biff.BiffException;
import lib.common.Navigation;
import lib.common.WebPage;
import lib.User.CreateBookDetails;

public class CreateBookPage extends WebPage {

	public CreateBookPage() throws BiffException, IOException, InterruptedException {
		super();
	}

	private static  void clickButton(String buttonName) throws Exception {
		clickOn("CreateBook", buttonName);
	}

	public static void createBook(CreateBookDetails createBookDetails) throws Exception {
			enterValue("BookTitle", createBookDetails.getBookTitle());
			enterValue("BookDescription", createBookDetails.getBookDescription());
			 clickButton("Save");
	}
	
	private static void enterValue(String fieldName, String fieldValue) throws BiffException, IOException, InterruptedException {
		clearField("CreateBook", fieldName);
		enterValue("CreateBook", fieldName, fieldValue);
	}
}
