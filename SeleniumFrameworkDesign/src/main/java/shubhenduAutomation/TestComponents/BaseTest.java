package shubhenduAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import shubhenduAutomation.SeleniumFrameworkDesign.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage login;
	public WebDriver intilializeDriver() throws IOException
	{
		
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\shubhenduAutomation\\Resources\\global.properties");
		property.load(fis);
		
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):property.getProperty("browser");
		
		
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
			driver = new ChromeDriver(options);
			}
			else
			{
				driver = new ChromeDriver();
			}
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().setSize(new Dimension(1440,900));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		
		return driver;
	}
	
	public List<HashMap<String, String>> getJSONDataToHashMap(String fileName) throws IOException
	{
		//reading json to string
		String file = FileUtils.readFileToString(new File(fileName),
				StandardCharsets.UTF_8);
		//convert string to json
		
		//we have to use jaxson data bind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(file, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
	}
	
	public String getScreenShot(String testName,WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testName+".png");
	
		System.out.println(System.getProperty("user.dir"));
		FileUtils.copyFile(src, dest);
		
		return System.getProperty("user.dir")+"//reports//"+testName+".png"	;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		try {
			driver = intilializeDriver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		login = new LandingPage(driver);
		
		login.goTo();
		
		return login;
	}
	
	@AfterMethod(alwaysRun = true)
	public void taerDown()
	{
		driver.close();
	}

}
