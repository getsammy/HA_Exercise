package ha.exercise1.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class StoreLogoutPage 
{
	WebDriver driver;
	
	/*Constructor*/
	public StoreLogoutPage(WebDriver driver)
	{
		this.driver=driver;
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	public void quitBrowser() 
	{
		driver.close();		
	}
}
