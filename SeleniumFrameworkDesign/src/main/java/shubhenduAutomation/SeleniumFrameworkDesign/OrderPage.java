package shubhenduAutomation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhenduAutomation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//tr//td[2]")
	List<WebElement> orderedProd_names;
	


	WebDriver driver;
	
	public int findOrderPresent(String productName)
	{
	int count = (int) orderedProd_names.stream().filter(s-> s.getText().equalsIgnoreCase(productName)).count();
	return count;
	}
	
	

}
