package ha.exercise1.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class StoreHomePage extends Store
{
	public StoreHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;
	
	/*Constructor*/
	/*
	public StoreHomePage(WebDriver driver)
	{
		this.driver=driver;
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	*/
	
	/*WebElements*/
	
	
	/*Services*/
	
	

}
