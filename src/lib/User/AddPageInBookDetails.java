package lib.User;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.Utilities;

public class AddPageInBookDetails {
	private String bookPageName;
	
	private static AddPageInBookDetails instance;

//	public static CreateBookDetails getInstance() throws BiffException,IOException, InterruptedException {
//		if (instance == null) {
//			instance.setBookTitle(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle"));
//			instance.setBookDescription(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription"));
//		}
//		return instance;
//	}
	
	public static AddPageInBookDetails getAddPageInBookDetails(String bookPageName)
	{
		AddPageInBookDetails addPageInBookDetails = new AddPageInBookDetails();
		String uniquePageTitle = Utilities.generateRandomNumber(5);
		addPageInBookDetails.setBookPageName(bookPageName.concat(uniquePageTitle));
		return addPageInBookDetails;
	}

	public static void clear() {
		instance = null;
	}

	public String getBookPageName() {
		return bookPageName;
	}

	public void setBookPageName(String bookPageName) {
		this.bookPageName = bookPageName;
	}
}
