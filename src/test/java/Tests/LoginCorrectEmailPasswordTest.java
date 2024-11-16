package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginCorrectEmailPasswordTest {
	
	@Test
	public void loginCorrect()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		//Verify Home Page is visible successfully
		WebElement homeLogo =	driver.findElement(By.xpath("//img[@alt='demo website for practice']"));
		Assert.assertTrue(homeLogo.isDisplayed());
		driver.findElement(By.xpath("//a//i[@class='fa fa-lock']")).click();
		String loginAccount =	driver.findElement(By.xpath("//div[@class='login-form']//h2")).getText();
		Assert.assertEquals(loginAccount, "Login to your account");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("amansindhia@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("sindhiaAman55");
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		WebElement userLoggedIn =	driver.findElement(By.xpath("//a//i[@class='fa fa-user']"));
		Assert.assertTrue(userLoggedIn.isDisplayed());	
		
	}
	
	
	
	
	

}
