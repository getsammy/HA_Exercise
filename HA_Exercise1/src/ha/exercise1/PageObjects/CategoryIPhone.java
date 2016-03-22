/**
 * Product Category page for iPhones
 * 
 * @param linkIPhone4S16GBlack - Product details link for Apple iPhone 4S 16GB SIM-Free – Black
 * @param inputAddToCart - Add to Cart button for Apple iPhone 4S 16GB SIM-Free – Black
 * @param buttonCheckout - Checkout button
 * @param buttonContinueShopping - Continue Shopping button
 */
package ha.exercise1.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryIPhone extends Store
{
	public CategoryIPhone(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/*WebElement*/
	@FindBy(linkText="Apple iPhone 4S 16GB SIM-Free – Black")
	WebElement linkIPhone4S16GBlack;
	
	@FindBy(xpath=".//*[@name='product_96']/div[2]/div/span/input")
	WebElement inputAddToCart;
	
	@FindBy(css=".go_to_checkout")
	WebElement buttonCheckout;
	
	@FindBy(css=".continue_shopping")
	WebElement buttonContinueShopping;	
	
	/*Services*/
	/*Add given product to cart and checkout*/
	public CheckoutPage AddProductToCartAndCheckout(String strProductName)
	{
		waittillResultload(".//*[@name='"+strProductName+"']/div[2]/div/span/input", "xpath");
		
		inputAddToCart.click();
		
		waittillResultload(".go_to_checkout", "css");
		
		buttonCheckout.click();
		
		return new CheckoutPage(driver);
		
	}
	
	/*Add givn product to cart and continue shopping*/
	public CategoryIPhone AddProductToCartAndContinue(String strProductName)
	{
		
		waittillResultload(".//*[@name='"+strProductName+"']/div[2]/div/span/input", "xpath");
		
		inputAddToCart.click();
		
		waittillResultload(".continue_shopping", "css");
		
		buttonContinueShopping.click();
		
		return this;
		
	}
	
}
