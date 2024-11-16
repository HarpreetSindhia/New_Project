package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddReviewOnProduct {

	@Test
	public void addProductOnReview()
	{
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get("https://automationexercise.com/");
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	driver.findElement(By.xpath("//a[@href='/products']")).click();
	
	String homePage =	driver.getTitle();
	
	Assert.assertEquals(homePage, "Automation Exercise");
	
	WebElement viewProduct =	driver.findElement(By.xpath("//a[text()='View Product']"));
	js.executeScript("arguments[0].scrollIntoView(true);", viewProduct);
	js.executeScript("arguments[0].click();", viewProduct);
	
	WebElement reviewLabel =	driver.findElement(By.xpath("//a[@href='#reviews']"));
	js.executeScript("arguments[0].scrollIntoView(true);", reviewLabel);
	Assert.assertTrue(reviewLabel.isDisplayed());
	
	driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Harpreet Sindhia");
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("harrysindhia@gmail.com");
	driver.findElement(By.xpath("//textarea[@name='review']")).sendKeys("I liked the product");
	driver.findElement(By.xpath("//button[@id='button-review']")).click();
	
	String successMessage =	driver.findElement(By.xpath("//div[@class='alert-success alert']//span")).getText();
	Assert.assertEquals(successMessage, "Thank you for your review.");
	
	
	
	
	}
}
