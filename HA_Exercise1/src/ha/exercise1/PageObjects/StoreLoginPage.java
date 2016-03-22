package ha.exercise1.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StoreLoginPage
{
	WebDriver driver;
	
	/*Constructor*/
	public StoreLoginPage(WebDriver driver)
	{
		this.driver=driver;
		this.driver.get("http://store.demoqa.com/products-page/your-account/");
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	/*WebElements*/
	@FindBy(id="log")
	WebElement textboxUsername ;
	
	@FindBy(id="pwd")
	WebElement textboxPassword;
	
	@FindBy(id="rememberme")
	WebElement checkboxRememberMe;
	
	@FindBy(id="login")
	WebElement buttonLogIn;
	
	/*Services*/
	public MyAccountPage LoginToStore(String strUsername, String strPassword)
	{
		textboxUsername.sendKeys(strUsername);
		textboxPassword.sendKeys(strPassword);
		
		buttonLogIn.click();
		
		return new MyAccountPage(driver);
	}
	
	/**
	 * Login function - Opens login page, enters credentials and logs in.
	 * @return
	 */
	public MyAccountPage Login()
	{
		MyAccountPage myAccountPage = this.LoginToStore("testCyb1", "testcyb!@#$12");
		WebDriverWait wait=new WebDriverWait(this.driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your Details")));
		
		Assert.assertEquals(myAccountPage.getTitle(), "Your Account | ONLINE STORE");
		
		return myAccountPage;
	}
}
