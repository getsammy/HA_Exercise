/**
 * Information page in Checkout wizard.
 * 
 * @param labelShippingcost - Product shipping cost
 * @param labelTotalItemPrice - Total item price
 * @param labelTotalPrice - Total price
 * @param buttonPurchase - Purchase button
 */
package ha.exercise1.PageObjects;



import ha.exercise1.utilities.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CheckoutInfoPage extends Store
{
	public CheckoutInfoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/*WebElement*/
	@FindBy(xpath=".//tr[@class='total_price total_shipping']/td[2]/span/span")
	WebElement labelShippingcost;
	
	@FindBy(xpath=".//tr[@class='total_price total_item']/td[2]/span/span")
	WebElement labelTotalItemPrice;
	
	@FindBy(xpath=".//tr[@class='total_price']/td[2]/span/span")
	WebElement labelTotalPrice;
	
	@FindBy(css="input[value='Purchase']")
	WebElement buttonPurchase;
	
	@FindBy(css="select[title='billingcountry']")
	WebElement selectCountry;
	
	@FindBy(css="#change_country>input")
	WebElement buttonContinue;
	
	@FindBy(css="input[title='billingemail']")
	WebElement textboxEmail;
	
	@FindBy(css="input[title='billingfirstname']")
	WebElement textboxFirstName;
	
	@FindBy(css="input[title='billinglastname']")
	WebElement textboxLastName;
	
	@FindBy(css="textarea[title='billingaddress']")
	WebElement textboxAddress;
	
	@FindBy(css="input[title='billingcity']")
	WebElement textboxCity;
	
	@FindBy(css="input[title='billingphone']")
	WebElement textboxPhone;
	
	@FindBy(css="#shippingSameBilling")
	WebElement checkboxShippingSameBilling;
	
	/*Services*/
	/*Verify Total Price*/
	public void verifyTotalPrice()
	{
		try
		{
			double iShippingCost = GetNumbersFromString(labelShippingcost.getText());
			
			double iTotalItemPrice = GetNumbersFromString(labelTotalItemPrice.getText());
			
			double iTotalPrice = GetNumbersFromString(labelTotalPrice.getText()); 
			
			double iActualTotalPrice = iShippingCost + iTotalItemPrice;
			
			Assert.assertEquals(iActualTotalPrice, iTotalPrice);
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	
	/*Verify mandatory fields are not empty*/
	public void VerifyFormFields()
	{
		this.VerifyField(textboxEmail, "Test"+Utility.randomString(Utility.strLetters,10)+"@test.com");
		this.VerifyField(textboxFirstName, "Test"+Utility.randomString(Utility.strLetters,10));
		this.VerifyField(textboxLastName, "Test"+Utility.randomString(Utility.strLetters,10));
		this.VerifyField(textboxAddress, Utility.randomString(Utility.strNumbers,4)+", Test"+Utility.randomString(Utility.strLetters,10));
		this.VerifyField(textboxCity,"Test"+Utility.randomString(Utility.strLetters,10));
		
		if(textboxPhone.getAttribute("value").isEmpty())
		{
			/*Generate phone no. (123-12-1234 format)*/
			String strPhoneNo = Utility.randomString(Utility.strNumbers,3)+"-"+Utility.randomString(Utility.strNumbers,2)+"-"+Utility.randomString(Utility.strNumbers,4);
			textboxPhone.sendKeys(strPhoneNo);
		}
		
		Select select = new Select(driver.findElement(By.xpath(".//*[@id='wpsc_checkout_form_7']")));
		String strSelected = select.getFirstSelectedOption().getText();
		
		if(!strSelected.isEmpty())
		{
			/*Select country-USA*/
			/**
			 * Selection of country can be randomized.
			 */
			select.selectByVisibleText("USA");
		}
	}
	
	public void VerifyField(WebElement element,String strInputValue)
	{
		if(element.getAttribute("value").isEmpty())
		{
			element.sendKeys(strInputValue);
		}
	}
	
	/*Extract numbers from alphanumeric string*/
	public double GetNumbersFromString(String strAlphaNum)
	{
		return Double.parseDouble(strAlphaNum.replaceAll("[^0-9.]", ""));
	}
	
	/*Click Purchase button*/
	public PurchasePage Purchase()
	{
		this.waittillResultload(".make_purchase.wpsc_buy_button", "css");
		buttonPurchase.click();
			
		return new PurchasePage(driver);
	}
	
}
