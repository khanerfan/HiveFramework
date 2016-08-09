package lib.User;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.Utilities;

public class CreateBookDetails {
	private String bookTitle;
	private String bookDescription;
	private static CreateBookDetails instance;

//	public static CreateBookDetails getInstance() throws BiffException,IOException, InterruptedException {
//		if (instance == null) {
//			instance.setBookTitle(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle"));
//			instance.setBookDescription(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription"));
//		}
//		return instance;
//	}
	
	public static CreateBookDetails getCreateBookDetails(String bookTitle, String bookDescription)
	{
		CreateBookDetails createBookDetails = new CreateBookDetails();
		String uniqueBookTitle = Utilities.generateRandomNumber(5);
		createBookDetails.setBookDescription(bookDescription);
		if(bookTitle=="")
		{
			createBookDetails.setBookTitle(bookTitle);
		}
		else
		{
		createBookDetails.setBookTitle(bookTitle.concat(uniqueBookTitle));
		}
		return createBookDetails;
	}

	public static void clear() {
		instance = null;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
}
