package ha.exercise1.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends Store 
{
	public MyAccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*WebElements*/
	@FindBy(linkText="Your Details")
	WebElement linkYourDetails;
	
	public EditProfilePage OpenAccountDetails()
	{
		linkYourDetails.click();
		return new EditProfilePage(driver);
	}

}
