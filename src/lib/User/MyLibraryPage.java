package lib.User;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.Navigation;
import lib.common.WebPage;

public class MyLibraryPage extends WebPage{

	Navigation navigation = new Navigation();

	public MyLibraryPage() throws BiffException, IOException, InterruptedException {
		super();
	}
	
	public boolean isBookCreated(String bookName) throws NoSuchElementException, BiffException, IOException, InterruptedException, AWTException {
		boolean bookFound = false;
		navigation.navigateToMyLibrary();
		scrollingToBottomofAPage();
		String totalBooksElementPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "TotalBooks");
		int count = getElementsCount(totalBooksElementPath);
		System.out.println(count);

		for (int i = 1; i <= count; i++) {
			String cXPath = ConfigurationProperty.xlLocation.getValueFor("MyLibrary", "SingleBook").replace("count", String.valueOf(i));
			String returnedBookName = getElementByAttribute(cXPath,"title");
			if (returnedBookName.equals(bookName)) {
				System.out.println("Book " + bookName + " found at position " + (i));
				bookFound = true;
				break;
			}
		}
		return bookFound;
	}
	
	
}
