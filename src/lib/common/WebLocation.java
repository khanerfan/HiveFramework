package lib.common;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class WebLocation {
	protected String webURL;

	public WebLocation(String webURL) throws BiffException, IOException {
		super();
		XLManipulation xlData = new XLManipulation(new ConfigurationProperty().getDataXLPath());
		// driver=TestDriver.getInstance();
		this.webURL = xlData.getValueFor("Properties", "baseurl") + webURL;

	}

	public String getWebURL() {
		return webURL;
	}

	public void setWebURL(String webURL) {
		this.webURL = webURL;
	}

}
