package lib.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.apache.commons.lang3.RandomStringUtils;

import enums.ContentType;

public  class  Utilities extends ConfigurationProperty {

	protected  static TestDriver driver;

	public Utilities() throws BiffException, IOException {

	}


	public boolean toBeExecuted(String testCase) {
		String runMode = ConfigurationProperty.xlData.getCellValue("TestScenarios", 2, xlData.getRowNumber("TestScenarios", testCase));
		if (runMode.equalsIgnoreCase("Y"))
			return true;
		else
			return false;
	}

	public static ContentType ConvertContentType(String contentType)
	{
		for (ContentType selectedType : ContentType.values()) {
			  
			if(selectedType.value().equalsIgnoreCase(contentType))
				{
					return selectedType;
				}
			}
		return ContentType.image;
	}
	
	public static String generateRandomNumber(int length) {
		if (length > 0) {
			Random rand = new Random();
			int firstDigit = rand.nextInt(8) + 1;
			return firstDigit + RandomStringUtils.randomNumeric(length - 1);
		} else
			return "0";
	}

	public static String generateRandomNumber(int min, int max) {
		int length = getRandomLength(min, max);
		return generateRandomNumber(length);
	}

	private static int getRandomLength(int min, int max) {
		if (min == max)
			return min;
		
		//Get the difference
		int difference = max - min;
		
		//generate the random with the difference
		int random = Integer.parseInt(generateRandomNumber(difference));
		
		//Return sum of random and min
		return min+random;
	}

}
