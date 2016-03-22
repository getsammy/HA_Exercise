package ha.exercise1.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EditProfilePage extends Store 
{
	public EditProfilePage(WebDriver driver) {
		super(driver);
	}
	
	
	/*WebElements*/
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement textboxFirstName;
	
	@FindBy(name="submit")
	WebElement buttonSubmit;
	
	/*Services*/
	public void UpdateFirstName(String strNewFirstName)
	{
		textboxFirstName.clear();
		
		textboxFirstName.sendKeys(strNewFirstName);
		
		buttonSubmit.click();
		
	}
	
	public String GetFirstName()
	{
		return textboxFirstName.getAttribute("value"); 
	}
	
}