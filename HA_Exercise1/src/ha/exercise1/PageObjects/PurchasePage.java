package ha.exercise1.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PurchasePage extends Store
{
	public PurchasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/*WebElements*/
	@FindBy(css=".wpsc-transaction-results-wrap>p:nth-of-type(1)")
	WebElement labelThankYou;
	
	/*Services*/
	public void verifyPurchase()
	{
		Assert.assertEquals(labelThankYou.getText(), "Thank you, your purchase is pending. You will be sent an email once the order clears.");
	}
}
