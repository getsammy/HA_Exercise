/**
 * Submit an order for an Apple iPhone4s 16GB SIM-Free – Black (known issue with State drop-down, selecting Country is adequate) 
 * and verify the Total Price: given is correct (assume shipping cost is correct based on your choice). You may assume prices shown 
 * on product pages are the correct price.
 * 
 * @pre-requisites 
 * 	- User already have account with e-commerce website (AUT)
 *  - Account details are provided.
 */

package ha.exercise1.tests;

import ha.exercise1.PageObjects.CategoryIPhone;
import ha.exercise1.PageObjects.CheckoutInfoPage;
import ha.exercise1.PageObjects.CheckoutPage;
import ha.exercise1.PageObjects.MyAccountPage;
import ha.exercise1.PageObjects.PurchasePage;
import ha.exercise1.PageObjects.StoreLoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestPurchase 
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
	public void PurchaseProduct() throws InterruptedException
	{
		/*Login with valid user credentials*/
		StoreLoginPage storeLoginPage = new StoreLoginPage(driver);
		MyAccountPage myAccountPage = storeLoginPage.Login();
		
		/*Category-iPhone page*/		
		CategoryIPhone categoryIPhone = myAccountPage.OpenIPhoneProductCategory();		
		
		//Add "Apple iPhone 4S 16GB SIM-Free – Black" to cart
		CheckoutPage checkoutPage = categoryIPhone.AddProductToCartAndCheckout("product_96");
		
		CheckoutInfoPage checkoutInfoPage = checkoutPage.ContinuetoCheckoutInfoPage();
		
		//Verify mandatory form fields
		checkoutInfoPage.VerifyFormFields();
		
		//Verify total price on the checkout page. Test will fail if price verification unsuccessful.
		checkoutInfoPage.verifyTotalPrice();
		
		//Click on Purchase button
		PurchasePage puchasePage = checkoutInfoPage.Purchase();
		
		//Verify purchase success.
		puchasePage.verifyPurchase();
		
		/*Logout*/
		myAccountPage.LogoutFromStore();
		
	}
	
	@AfterTest
	public void TearDown()
	{
		driver.quit();
	}

}
