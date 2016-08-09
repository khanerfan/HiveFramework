package lib.User;

import java.io.IOException;

import enums.ContentType;
import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.WebPage;

public class AddContentsInPage extends WebPage {

	ContentType content;

	public AddContentsInPage() throws BiffException, IOException, InterruptedException {
		super();
	}

	private static void clickButton(String buttonName) throws Exception {
		clickOn("AddContentsInPage", buttonName);
	}

	public static void addContentToPage(ContentType contentItem, int numberOfContents) throws Exception {
		switch (contentItem) {
		case image:
			System.out.println("select image");
			selectContentOption(contentItem.name());
			for (int i = 0; i < numberOfContents; i++) {
				selectContent("SelectedImage", i);
			}
			break;
		case video:
			System.out.println("select video");
			selectContentOption(contentItem.name());
			for (int i = 0; i < numberOfContents; i++) {
				selectContent("SelectedVideo", i + 1);
			}
			break;
		case audio:
			System.out.println("select audio");
			selectContentOption(contentItem.name());
			for (int i = 0; i < numberOfContents; i++) {
				selectContent("SelectedAudio", i );
			}
			break;
		}
		clickButton("AddToPage");
		clickOn("AddPageInBook","FinishEditing");
	}

	private static void selectContent(String contentName, int i) throws Exception {
		System.out.println(contentName +" selecting...");
		String cXPath = ConfigurationProperty.xlLocation.getValueFor("AddContentsInPage", contentName).replace("count", String.valueOf(i + 1));
		clickOnButton(cXPath);
	}
	
	

	private static void selectContentOption(String contentItem) throws Exception {
		clickOn("AddContentsInPage", contentItem);
		System.out.println(contentItem + " option clicked");
	}

	private static void enterValue(String fieldName, String fieldValue) throws BiffException, IOException, InterruptedException {
		clearField("AddContentsInPage", fieldName);
		enterValue("AddContentsInPage", fieldName, fieldValue);
	}
}
