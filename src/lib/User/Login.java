package lib.User;

import org.testng.annotations.Test;

import java.io.IOException;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import lib.common.ConfigurationProperty;
import lib.common.TestBase;
import lib.common.TestDriver;
import lib.common.Utilities;
import lib.common.WebPage;
import lib.common.XLManipulation;

import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import lib.common.WebPage;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

public class Login extends WebPage {

	public Login() throws BiffException, IOException, InterruptedException {
		super();
	}

	
	public static void performLogin() throws InterruptedException, BiffException, IOException {
		{
			loadUrl("Properties", "capturedLoginURL");
			// Enter User Name by reading data from Excel
//			waitForElementPresent("Login", "Email");
			clearField("Login", "Email");
			System.out.println(ConfigurationProperty.xlData.getValueFor("Credentials", "Email"));
			enterValue("Login", "Email",ConfigurationProperty.xlData.getValueFor("Credentials", "Email"));

			// Enter Password
			clearField("Login", "Password");
			enterValue("Login", "Password",ConfigurationProperty.xlData.getValueFor("Credentials", "Password"));
			clickOn("Login", "LoginButton");
//			firefoxsignin("Login", "LoginButton");
			
//			driver.refreshthePage();        
		}
	}

	public  void logout() throws InterruptedException, BiffException, IOException {
		{
			boolean isProfileAvailable = isElementPresent("MyProfile", "ProfileWithPathPicture");
			
			System.out.println("isProfileAvailable = " + isProfileAvailable);
			
			if(isProfileAvailable)
			{
			System.out.println("with profile pic");
			waitForElementPresent("MyProfile", "ProfileWithPathPicture");
			doubleClickOn("MyProfile", "ProfileWithPathPicture");
			clickOn("Login", "Logout");
			}
			else 
			{
			System.out.println("without profile pic");
			waitForElementPresent("MyProfile", "ProfileWithoutPathPicture");
			doubleClickOn("MyProfile", "ProfileWithoutPathPicture");
			clickOn("Login", "Logout");
			}
			}
		}
	}