package ha.exercise1.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DriverInitialize 
{
	public WebDriver driver;
	
	@BeforeTest
	public void start()
	{
		//Set system property
		System.setProperty("webdriver.chrome.driver", "..\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();		
	}

	@AfterTest
	public void TearDown()
	{
		driver.quit();
	}

}
