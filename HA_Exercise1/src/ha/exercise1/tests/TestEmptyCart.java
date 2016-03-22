/**
 * Verify removing all items from your cart produces an empty cart message.
 * @author samirk 
 */

package ha.exercise1.tests;

import ha.exercise1.PageObjects.CategoryIPhone;
import ha.exercise1.PageObjects.CheckoutPage;
import ha.exercise1.PageObjects.MyAccountPage;
import ha.exercise1.PageObjects.StoreLoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestEmptyCart
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
	public void verifyEmptyCart() throws InterruptedException
	{
		/*Login with valid user credentials*/
		/*Login with valid user credentials
		 *After login user lands on profile page
		 * */
		StoreLoginPage storeLoginPage = new StoreLoginPage(driver);
		MyAccountPage myAccountPage = storeLoginPage.Login();
		
		/*Category-iPhone page*/		
		CategoryIPhone categoryIPhone = myAccountPage.OpenIPhoneProductCategory();		
		
		//Add products to cart
		categoryIPhone = categoryIPhone.AddProductToCartAndContinue("product_96");
		CheckoutPage checkoutPage = categoryIPhone.AddProductToCartAndCheckout("product_40");
		
		int intItemCount = checkoutPage.GetItemCount();
		
		if(intItemCount != 0)
		{
			for(int i=0; i<intItemCount; i++)
				checkoutPage = checkoutPage.RemoveItem();
		
			String strMessage = checkoutPage.GetEmptyCartMessage();
			
			//check cart empty message
			Assert.assertEquals(strMessage, "Oops, there is nothing in your cart.");
		}
		else
			System.out.println("No items in the cart.");
		
		
		/*Logout*/
		myAccountPage.LogoutFromStore();
		
	}
	
	@AfterTest
	public void TearDown()
	{
		driver.quit();
	}
	
	
}
