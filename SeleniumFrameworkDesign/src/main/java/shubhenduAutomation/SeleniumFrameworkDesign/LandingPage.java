package shubhenduAutomation.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhenduAutomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(xpath="//div[@id=\"toast-container\"]//div[@role=\"alert\"]")
	WebElement errorToast;
	
	@FindBy(xpath="//button[contains(text(),'ORDERS')]")
	WebElement orders_btn;
	
	public ProductCatalogue login(String username,String password)
	{
		email.sendKeys(username);
		pass.sendKeys(password);
		submit.click();	
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMsg()
	{
		waitForVisibilityOfElement(errorToast,5);
		String error = errorToast.getText();
		return error;
	}
	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public OrderPage clickOnOrderbtn()
	{
		orders_btn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
