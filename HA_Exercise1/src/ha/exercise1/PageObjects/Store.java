package ha.exercise1.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Store 
{
	WebDriver driver;
	
	/*Constructor*/
	public Store(WebDriver driver)
	{
		this.driver=driver;
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	/*WebElelements*/
	@FindBy(id="wp-admin-bar-site-name")
	WebElement linkOnlineStore;
	
	@FindBy(id="wp-admin-bar-my-account")
	WebElement linkProfileMenupopup;
	
	@FindBy(xpath=".//*[@id='account_logout']/a")
	WebElement linkLogout;
	
	@FindBy(linkText="Product Category")
	WebElement linkProductCategory;
	
	@FindBy(linkText="iPhones")
	WebElement linkIPhones;
	
	@FindBy(xpath=".//*[@id='account']/a")
	WebElement linkMyAccount;
	
	@FindBy(css=".cart_icon")
	WebElement linkCheckout;
	
	public void LogoutFromStore()
	{
		linkLogout.click();
//		Actions action = new Actions(driver);
//		action.moveToElement(linkProfileMenupopup).click(linkLogout).build().perform();
	}
	
	public StoreHomePage AccessOnlineStore()
	{
		Actions action = new Actions(driver);
		action.moveToElement(linkOnlineStore).click(linkOnlineStore).build().perform();
		
		return new StoreHomePage(driver);
	}
	
	public CategoryIPhone OpenIPhoneProductCategory()
	{
		//Open Product Category to make 'iPhones' menu visible
		Actions openProductCategoryMenu = new Actions(driver);
		openProductCategoryMenu.moveToElement(linkProductCategory).build().perform();
		
		this.waittillResultload("iPhones", "link");
		linkIPhones.click();
		
		return new CategoryIPhone(driver);
	}
	
	public void OpenMyAccount()
	{
		linkMyAccount.click();
	}
	
	protected void waittillResultload(String strLocator, String strLocatorType)
	{
		WebDriverWait wait=new WebDriverWait(this.driver, 60);
		
		//switch
		if(strLocatorType == "link")
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(strLocator)));
		if(strLocatorType == "id")
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(strLocator)));
		if(strLocatorType == "css")
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(strLocator)));
	}
	
	public CheckoutPage OpenCart()
	{
		linkCheckout.click();
		
		return new CheckoutPage(driver);
	}
	
	public String getTitle() {
		
		return (this.driver.getTitle());
	}		
}
