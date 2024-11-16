package Tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AEcommerce_pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubsriptionTest {

	@Test
	public void verifySubscription()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goToApplication();
		Assert.assertTrue(landingPage.getSubText().equalsIgnoreCase("Subscription"));
		landingPage.subscribe("harrysindhia@gmail.com");
		Assert.assertEquals(landingPage.getSubscribeMessage(), "You have been successfully subscribed!");	
	}
}
