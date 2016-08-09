package lib.User;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;

public class MyLibraryDetails {
	

	private String MyLibraryTitle;
	private String AllContents;
	private String BooksContents;
	private String PagesContents;
	private String SortBy;

	private static MyLibraryDetails instance;

//	public static CreateBookDetails getInstance() throws BiffException,IOException, InterruptedException {
//		if (instance == null) {
//			instance.setBookTitle(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle"));
//			instance.setBookDescription(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription"));
//		}
//		return instance;
//	}
	
	public static MyLibraryDetails getAddPageInBookDetails(String bookPageName)
	{
		MyLibraryDetails myLibraryDetails = new MyLibraryDetails();
		MyLibraryDetails.setMyLibraryLink(bookPageName);
		return myLibraryDetails;
	}

	public static void clear() {
		instance = null;
	}

	private static String MyLibraryLink;
	public String getMyLibraryLink() {
		return MyLibraryLink;
	}

	public static void setMyLibraryLink(String myLibraryLink) {
		MyLibraryLink = myLibraryLink;
	}

	public String getMyLibraryTitle() {
		return MyLibraryTitle;
	}

	public void setMyLibraryTitle(String myLibraryTitle) {
		MyLibraryTitle = myLibraryTitle;
	}

	public String getAllContents() {
		return AllContents;
	}

	public void setAllContents(String allContents) {
		AllContents = allContents;
	}

	public String getBooksContents() {
		return BooksContents;
	}

	public void setBooksContents(String booksContents) {
		BooksContents = booksContents;
	}

	public String getPagesContents() {
		return PagesContents;
	}

	public void setPagesContents(String pagesContents) {
		PagesContents = pagesContents;
	}

	public String getSortBy() {
		return SortBy;
	}

	public void setSortBy(String sortBy) {
		SortBy = sortBy;
	}
}
