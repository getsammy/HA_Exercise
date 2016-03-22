/**
 * Verify updating user account details is saved and retrieved after logging out and back in using the My Account link.
 */
package ha.exercise1.tests;

import ha.exercise1.PageObjects.EditProfilePage;
import ha.exercise1.PageObjects.MyAccountPage;
import ha.exercise1.PageObjects.StoreLoginPage;
import ha.exercise1.utilities.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestProfileUpdate
{
	WebDriver driver;
	
	@BeforeTest
	public void start()
	{
		//Set system property
		System.setProperty("webdriver.chrome.driver", "..\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();		
	}
	
	@Test
	public void verifyUpdateProfile() throws InterruptedException
	{
		/*Login with valid user credentials
		 * 
		 * After login user lands on profile page
		 * */
		
		/*Login with valid user credentials*/
		StoreLoginPage storeLoginPage = new StoreLoginPage(driver);
		MyAccountPage myAccountPage = storeLoginPage.Login();
		
		EditProfilePage editProfilePage = myAccountPage.OpenAccountDetails();
		
		//Generate first name
		String strNewFirstName = "testNewFirstName_" + Utility.randomString(Utility.strLetters,4);
		
		editProfilePage.UpdateFirstName(strNewFirstName);
		
		/*Logout*/
		myAccountPage.LogoutFromStore();		
		
		/*Login Again*/
		myAccountPage = storeLoginPage.Login();
		
		editProfilePage = myAccountPage.OpenAccountDetails();
		
		/*verify updated profile*/
		Assert.assertEquals(editProfilePage.GetFirstName(), strNewFirstName);
		
		/*Logout*/
		myAccountPage.LogoutFromStore();
	}
	
	@AfterTest
	public void TearDown()
	{
		driver.quit();
	}
	
}
