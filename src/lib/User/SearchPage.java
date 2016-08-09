package lib.User;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.Navigation;
import lib.common.WebPage;

public class SearchPage extends WebPage {

	Navigation navigation = new Navigation();

	public SearchPage() throws BiffException, IOException, InterruptedException {
		super();
	}

	public void searchCreatedBook(String bookName) throws BiffException, IOException, InterruptedException, NoSuchElementException, java.util.NoSuchElementException, AWTException {
		if (isBookCreated(bookName)) {
			clickOnButton(bookName);
		}
	}

	public boolean searchCreatedBookPage(String accountLastName) throws BiffException, IOException, InterruptedException {
		enterValue("AddCard", "CardHolderLastName", accountLastName);
		clickOn("Search", "SearchCards");
		return true;
	}

	public boolean searchCreatedBooklessPage(String sheetName, String FieldName, String cardNumber) throws BiffException, IOException, InterruptedException {
		enterValue(sheetName, FieldName, cardNumber);
		clickOn("Search", "SearchCards");
		return true;
	}

	public HashMap getBookElementDetails(String bookName) throws BiffException, java.util.NoSuchElementException, IOException, InterruptedException {
		HashMap bookMap = new HashMap<String, String>();
		scrollingToBottomofAPage();
		String totalBooksElementPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "TotalBooks");
		int count = getElementsCount(totalBooksElementPath);
		System.out.println(count);
//		 System.out.println("Created Book " + bookName);
		for (int i = 1; i <= count; i++) {
			String cXPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "SingleBook").replace("count", String.valueOf(i));
//			 System.out.println("Search Book Path" + cXPath);
//			String returnedBookName = getElementByAttribute(cXPath, "title");
			String returnedBookName = getElementValueDirectly(cXPath);
			 System.out.println("Book " + returnedBookName);
			if (returnedBookName.equals(bookName)) {
//				 System.out.println("Book " + bookName + " found at position " + (i));
				bookMap.put("bookName", returnedBookName);
				bookMap.put("bookPath", cXPath);
				return bookMap;
			}
		}
		return null;
	}

	public HashMap getBookPageElementDetails(String bookPageName) throws BiffException, java.util.NoSuchElementException, IOException, InterruptedException {
		HashMap bookMap = new HashMap<String, String>();
		// scrollingToBottomofAPage();
		String totalBookPagesElementPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "TotalBookPages");
		int count = getElementsCount(totalBookPagesElementPath);
		System.out.println(count);

		for (int i = 1; i <= count; i++) {
			String cXPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "SingleBookPage").replace("count", String.valueOf(i + 1));
			String returnedBookPageName = getElementValueDirectly(cXPath);
			System.out.println(returnedBookPageName);
			if (returnedBookPageName.equals(bookPageName)) {
				// System.out.println("Book " + bookName + " found at position "
				// + (i));
				bookMap.put("bookPageName", returnedBookPageName);
				bookMap.put("bookPagePath", cXPath);
				return bookMap;
			}
		}
		return null;
	}

	public HashMap getBooklessPageElementDetails(String booklessPageName) throws BiffException, java.util.NoSuchElementException, IOException, InterruptedException {
		HashMap bookMap = new HashMap<String, String>();
		// scrollingToBottomofAPage();
		String totalBooklessPagesElementPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "TotalBooklessPages");
		int count = getElementsCount(totalBooklessPagesElementPath);
		System.out.println(count);

		for (int i = 1; i <= count; i++) {
			String cXPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "SingleBooklessPage").replace("count", String.valueOf(i));
//			String returnedBooklessPageName = getElementByAttribute(cXPath, "title");
			String returnedBooklessPageName = getElementValueDirectly(cXPath);
			System.out.println(returnedBooklessPageName);
			System.out.println(booklessPageName);
			if (returnedBooklessPageName.equals(booklessPageName)) {
				// System.out.println("Book " + bookName + " found at position "
				// + (i));
				bookMap.put("booklessPageName", returnedBooklessPageName);
				bookMap.put("booklessPagePath", cXPath);
				return bookMap;
			}
		}
		return null;
	}

	public void openCreatedBook(String bookName) throws NoSuchElementException, BiffException, IOException, InterruptedException, AWTException {
		boolean bookFound = false;
		navigation.navigateToMyLibrary();
		HashMap bookMap = getBookElementDetails(bookName);
		if (bookMap != null) {
			System.out.println(bookMap.get("bookName"));
			System.out.println(bookMap.get("bookPath"));
//			String bookPath = bookMap.get("bookPath").toString();
			String bookPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "SingleBookLink").toString();
			clickOnButton(bookPath);
		}
	}

	public void openCreatedBookPage(String bookName, String bookPageName) throws NoSuchElementException, BiffException, IOException, InterruptedException, AWTException {
		boolean bookPageFound = false;
		// navigation.navigateToMyLibrary();
		openCreatedBook(bookName);
		HashMap bookMap = getBookPageElementDetails(bookPageName);
		if (bookMap != null) {
			System.out.println(bookMap.get("bookPageName"));
			System.out.println(bookMap.get("bookPagePath"));
			String bookPagePath = bookMap.get("bookPagePath").toString();
			clickOnButton(bookPagePath);
		}
	}

	public void openCreatedBooklessPage(String booklessPageName) throws NoSuchElementException, BiffException, IOException, InterruptedException {
		boolean booklessPageFound = false;
		// navigation.navigateToMyLibrary();
		HashMap bookMap = getBooklessPageElementDetails(booklessPageName);
		if (bookMap != null) {
			System.out.println(bookMap.get("booklessPageName"));
			System.out.println(bookMap.get("booklessPagePath"));
			String booklessPagePath = bookMap.get("booklessPagePath").toString();
			clickOnButton(booklessPagePath);
		}
	}

	public boolean isBookCreated(String bookName) throws NoSuchElementException, BiffException, IOException, InterruptedException, AWTException {
		boolean bookFound = false;
		navigation.navigateToMyLibrary();
		HashMap bookMap = getBookElementDetails(bookName);
		if (bookMap != null) {
			bookFound = true;
		}
		return bookFound;
	}

	public boolean isBookPageCreated(String bookName, String bookPageName) throws NoSuchElementException, BiffException, IOException, InterruptedException, AWTException {
		boolean bookPageFound = false;
		navigation.navigateToMyLibrary();
		openCreatedBook(bookName);
		HashMap bookMap = getBookPageElementDetails(bookPageName);
		if (bookMap != null) {
			bookPageFound = true;
		}
		return bookPageFound;
	}

	public boolean isBooklessPageCreated(String booklessPageName) throws NoSuchElementException, BiffException, IOException, InterruptedException {
		boolean booklessPageFound = false;
		navigation.navigateToMyLibraryPages();
		HashMap bookMap = getBooklessPageElementDetails(booklessPageName);
		if (bookMap != null) {
			booklessPageFound = true;
		}
		return booklessPageFound;
	}

}