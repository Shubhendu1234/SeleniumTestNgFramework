package shubhenduAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver){
		this.driver=driver;
		
	}
	
	public void waitForElementClickable(WebElement elm,long sec)
	{
		wait = new WebDriverWait(this.driver,Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(elm));
	}
	
	public void waitForElementClickable(By elm,long sec)
	{
		wait = new WebDriverWait(this.driver,Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(elm)));
	}
	
	public void textToBePresentInElement(WebElement elm,String msg,long sec)
	{
		wait = new WebDriverWait(this.driver,Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.textToBePresentInElementValue(elm, msg));
		
	}
	
	public void waitForVisibilityOfElement(WebElement elm,long sec)
	{
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(elm));
	}
}
