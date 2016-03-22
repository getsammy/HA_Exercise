package ha.exercise1.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Store
{
	public int itemCount;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/*WebElements*/
	@FindBy(xpath=".//*[@id='checkout_page_container']/div[1]/a/span")
	WebElement buttonContinue;
	
	@FindBy(xpath="//input[@name='submit' and @value='Remove']")
	WebElement buttonRemove;
	
	@FindBy(css=".entry-content")
	WebElement textEmptyCartMessage;
	
	public CheckoutInfoPage ContinuetoCheckoutInfoPage()
	{
		buttonContinue.click();
		return new CheckoutInfoPage(driver);
	}
	
	public CheckoutPage RemoveItem()
	{
		buttonRemove.click();

		return this;
	}
	
	public String GetEmptyCartMessage()
	{
		return textEmptyCartMessage.getText();
	}
	
	public int GetItemCount()
	{
		List<WebElement> items = driver.findElements(By.xpath("//input[@name='submit' and @value='Remove']"));
		
		return items.size();
	}
	
}
