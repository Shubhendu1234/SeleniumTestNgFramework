package shubhenduAutomation;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import shubhenduAutomation.SeleniumFrameworkDesign.CheckOut;
import shubhenduAutomation.SeleniumFrameworkDesign.LandingPage;
import shubhenduAutomation.SeleniumFrameworkDesign.OrderPage;
import shubhenduAutomation.SeleniumFrameworkDesign.ProductCart;
import shubhenduAutomation.SeleniumFrameworkDesign.ProductCatalogue;
import shubhenduAutomation.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	// String productName = "ZARA COAT 3";
	String country = "India";
	// String username = "dummy007@email.com";
//	String password = "Shub@1234@";
	String productAddedmsg = "Product Added To Cart";

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "getData", groups = "dataSet")
	public void standAloneTest(HashMap<String, String> input) throws IOException, InterruptedException, NoSuchFieldException, SecurityException {

		ProductCatalogue productCatalogue = login.login(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.returnProducts();

		productCatalogue.addProductTOCart(input.get("productName"));

		productCatalogue.verifyProductAddedToCartToast(5);

		ProductCart productCart = productCatalogue.clickOnCartBtn();

		int count = productCart.verifyProductAddedToCart(input.get("productName"));

		if (count > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(true);
		}

		CheckOut checkout = productCart.clickOnCheckoutBtn();
		checkout.selectCountryFromDropDown(country);
		checkout.clickOnPlaceOrder();
		
		checkout.orderPlacedToast();

	}

	@SuppressWarnings("deprecation")
	@Test(dependsOnMethods = { "standAloneTest" }, dataProvider = "getDataOject")
	public void orderValidation(String username, String password, String productName) {
		login.login(username, password);
		OrderPage orderpage = login.clickOnOrderbtn();
		int count = orderpage.findOrderPresent(productName);

		if (count > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(true);
		}

	}
	// through object

	@DataProvider
	public Object[][] getDataOject() {
		Object[][] obj = { { "dummy007@email.com", "Shub@1234@", "ZARA COAT 3" },
				{ "dummy008@email.com", "Shub@1234@#", "ADIDAS ORIGINAL" } };

		return obj;
	}

	// through HashMap
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<>();
//		map.put("email", "dummy007@email.com");
//		map.put("password", "Shub@1234@");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<>();
//		map1.put("email", "dummy008@email.com");
//		map1.put("password", "Shub@1234@#");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		Object[][] obj = { { map }, { map1 } };
List<HashMap<String,String>> data = getJSONDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\TestFiles\\testData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
