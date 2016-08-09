package lib.User;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.Utilities;

public class CreateBooklessPageDetails {
	private String booklessPageName;
	
	private static CreateBooklessPageDetails instance;

//	public static CreateBookDetails getInstance() throws BiffException,IOException, InterruptedException {
//		if (instance == null) {
//			instance.setBookTitle(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle"));
//			instance.setBookDescription(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription"));
//		}
//		return instance;
//	}
	
	public static CreateBooklessPageDetails getBooklessPageDetails(String booklessPageName)
	{
		CreateBooklessPageDetails createBooklessPageDetails = new CreateBooklessPageDetails();
		String uniquePageTitle = Utilities.generateRandomNumber(5);
		createBooklessPageDetails.setBooklessPageName(booklessPageName.concat(uniquePageTitle));
		return createBooklessPageDetails;
	}

	public static void clear() {
		instance = null;
	}

	public String getBooklessPageName() {
		return booklessPageName;
	}

	public void setBooklessPageName(String booklessPageName) {
		this.booklessPageName = booklessPageName;
	}
}

