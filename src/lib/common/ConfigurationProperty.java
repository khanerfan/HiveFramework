package lib.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jxl.read.biff.BiffException;

public class ConfigurationProperty {
	private String dataXLPath;
	private String locationXLPath;
	private static final String DATA_XL_FILENAME = "Data.xls";
	private static final String LOCATION_XL_FILENAME = "Location.xls";
	public static final XLManipulation xlData = initializeDataFile();
	public static final XLManipulation xlLocation = initializeLocationFile();

	private static XLManipulation initializeDataFile() {
		try {
			return new XLManipulation(new ConfigurationProperty().getDataXLPath());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static XLManipulation initializeLocationFile() {
		try {
			return new XLManipulation(new ConfigurationProperty().getLocationXLPath());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getDataXLPath() throws IOException {
		return dataXLPath;
	}

	public String getLocationXLPath() {
		return locationXLPath;
	}

	public void setDataXLPath(String dataxlpath) {
		this.dataXLPath = dataxlpath;
	}

	public void setLocationXLPath(String locationXLPath) {
		this.locationXLPath = locationXLPath;
	}

	public ConfigurationProperty() throws BiffException, IOException {
		this.readPropertyValues();

	}

	private void readPropertyValues() throws BiffException, IOException {
//		Properties prop = new Properties();
//		String propFileName = "config.properties";
//		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//		if (inputStream != null) {
//			try {
//				prop.load(inputStream);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			try {
//				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//
		// get the property value and print it out
		// setDataXLPath(prop.getProperty("dataxlpath"));
		// setLocationXLPath(prop.getProperty("locationxlpath"));
		setDataXLPath(new File(".").getCanonicalPath() + "/" + DATA_XL_FILENAME);
		setLocationXLPath(new File(".").getCanonicalPath() + "/" + LOCATION_XL_FILENAME);
		// dataXLPath = prop.getProperty("dataxlpath");
		// locationXLPath = prop.getProperty("locationxlpath");
	}
}
