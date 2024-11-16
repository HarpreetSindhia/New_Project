package AEcommerce.TestsComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AEcommerce_pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	@Test
	public WebDriver initializeDriver() throws IOException

	{
		//Global Properties will be read by Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\globalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null?(System.getProperty("browser")):prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;	
	}
	
	@Test
	public List<HashMap<String, Object>> getJsonDataToMap(String filePath) throws IOException
	{
		//Read Json File to String
		String jsonContent = 	FileUtils.readFileToString(new File(filePath)
				,StandardCharsets.UTF_8);
		
		//Convert String to List of HashMap -> Depenedency Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,Object>> data =	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,Object>>>(){});
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName , WebDriver driver) throws IOException
	{
		File src =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File des =	new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+"\\.png");
		
		FileUtils.copyFile(src, des);
		
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+"\\.png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToApplication();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
}
