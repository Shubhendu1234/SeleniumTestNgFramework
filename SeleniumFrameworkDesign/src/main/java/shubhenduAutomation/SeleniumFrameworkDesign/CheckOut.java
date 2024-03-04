package shubhenduAutomation.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhenduAutomation.AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOut(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
		
	}
	
	@FindBy(xpath="//input[@placeholder=\"Select Country\"]")
	WebElement SelectCountry;
	
	@FindBy(xpath="//a[contains(text(),'Place Order')]")
	WebElement placeOrder_btn;
	
	@FindBy(xpath="//div[contains(@class,'toast-title')]")
	WebElement orderPlaced_toast;
	
	public void selectCountryFromDropDown(String CountryName)
	{
		SelectCountry.sendKeys(CountryName);
		waitForVisibilityOfElement(SelectCountry, 5);
		driver.findElement(By.xpath("//button//span[text()=' India']")).click();
	}
	
	public void clickOnPlaceOrder()
	{
		Actions a = new Actions(this.driver);
		a.moveToElement(placeOrder_btn).build().perform();
		a.click(placeOrder_btn).build().perform();
	}
	
	public void orderPlacedToast()
	{
		waitForVisibilityOfElement(orderPlaced_toast, 5);
	}

}
