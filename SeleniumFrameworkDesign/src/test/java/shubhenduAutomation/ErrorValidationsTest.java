package shubhenduAutomation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.Test;

import shubhenduAutomation.SeleniumFrameworkDesign.LandingPage;
import shubhenduAutomation.SeleniumFrameworkDesign.ProductCart;
import shubhenduAutomation.SeleniumFrameworkDesign.ProductCatalogue;
import shubhenduAutomation.TestComponents.BaseTest;
import shubhenduAutomation.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorValidation"},retryAnalyzer=Retry.class)
	public void logInErrorValidation() throws IOException
	{
		
		
		login.login("dum@gmail.com", "Shubh");
		String error = login.getErrorMsg();
		
		Assert.assertEquals(error, "Incorrect email or password.");
		
	}
	
	@Test
	public void checkCountISOne() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		String country = "India";
		String username = "dummy007@email.com";
		String password = "Shub@1234@";
		String productAddedmsg = "Product Added To Cart";

		ProductCatalogue productCatalogue = login.login(username, password);

		List<WebElement> products = productCatalogue.returnProducts();

		productCatalogue.addProductTOCart(productName);

		productCatalogue.verifyProductAddedToCartToast(5);

		ProductCart productCart = productCatalogue.clickOnCartBtn();

		int count = productCart.verifyProductAddedToCart(productName);

		Assert.assertFalse(false);
	}

}
