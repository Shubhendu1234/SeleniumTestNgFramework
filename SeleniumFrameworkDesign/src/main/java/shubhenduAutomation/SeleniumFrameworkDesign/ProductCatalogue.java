package shubhenduAutomation.SeleniumFrameworkDesign;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhenduAutomation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(xpath="//div[@aria-label=\"Product Added To Cart\"]")
	WebElement toast;
	
	@FindBy(xpath="//button[@routerlink=\"/dashboard/cart\"]")
	WebElement cart_btn;
	
	By product = By.xpath(".//b");
	By addToCart_btn = By.xpath(".//div[@class=\"card-body\"]//button[last()]");
	
	
	public List<WebElement> returnProducts() throws InterruptedException
	{
		
		return products;
	}
	
	public WebElement getProductByName(String productName) throws InterruptedException
	{
		System.out.println(productName);
		WebElement prod =  returnProducts().stream().filter(s->s.findElement(product).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductTOCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		
		waitForElementClickable(prod.findElement(addToCart_btn), 5);
		System.out.println(prod);
		prod.findElement(addToCart_btn).click();
		
		
	}
	
	public void verifyProductAddedToCartToast(long sec)
	{
		waitForVisibilityOfElement(toast, sec);
	}
	
	public ProductCart clickOnCartBtn()
	{
		cart_btn.click();
		ProductCart productCart = new ProductCart(driver);
		return productCart;
	}

}
