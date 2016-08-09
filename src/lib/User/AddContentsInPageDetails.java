package lib.User;


import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.Utilities;

public class AddContentsInPageDetails {
	private String image;
	private String video;
	private String audio;
	
	
	private static AddContentsInPageDetails instance;

//	public static CreateBookDetails getInstance() throws BiffException,IOException, InterruptedException {
//		if (instance == null) {
//			instance.setBookTitle(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookTitle"));
//			instance.setBookDescription(ConfigurationProperty.xlData.getValueFor("CreateBook", "BookDescription"));
//		}
//		return instance;
//	}
	
	public static AddContentsInPageDetails getAddPageInBookDetails(String imageName, String videoName, String audioName)
	{
		AddContentsInPageDetails addPageInBookDetails = new AddContentsInPageDetails();
		addPageInBookDetails.setImage(imageName);
		addPageInBookDetails.setVideo(videoName);
		addPageInBookDetails.setAudio(audioName);
		return addPageInBookDetails;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

}
