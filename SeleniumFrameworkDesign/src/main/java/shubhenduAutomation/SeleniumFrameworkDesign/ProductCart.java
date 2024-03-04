package shubhenduAutomation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhenduAutomation.AbstractComponents.AbstractComponent;

public class ProductCart extends AbstractComponent{
	public ProductCart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	WebDriver driver;
	
	@FindBy(xpath="//div[@class=\"cartSection\"]//h3")
	List<WebElement> productInCart;
	
	@FindBy(xpath="//button[@type=\"button\" and contains(text(),'Checkout')]")
	WebElement checkOut_btn;
	
	public int verifyProductAddedToCart(String productName)
	{
		return (int) productInCart.stream().filter(s->s.getText().equalsIgnoreCase(productName)).count();
		
		
	}
	
	public CheckOut clickOnCheckoutBtn() throws InterruptedException
	{
		waitForElementClickable(checkOut_btn, 5);
		Actions a = new Actions(driver);
		Thread.sleep(2000);
		a.moveToElement(checkOut_btn).build().perform();
		a.click(checkOut_btn).build().perform();
		
		return new CheckOut(driver);
		
	}
	
	

}
