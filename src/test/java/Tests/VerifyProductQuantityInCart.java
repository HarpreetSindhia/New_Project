package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyProductQuantityInCart {

	@Test
	public void viewQuantity()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement viewProduct =	driver.findElement(By.xpath("//a[@href='/product_details/7']"));
		js.executeScript("arguments[0].scrollIntoView(true);", viewProduct);
		wait.until(ExpectedConditions.elementToBeClickable(viewProduct)).click();
		
		//Verify Product Detail is opened
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/products']")).isEnabled());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-information']//h2")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-information']//p[1]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p//following-sibling::span//span")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-information']//p[2]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-information']//p[3]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-information']//p[4]")).isDisplayed());
		
		//Update the quantity
		
		WebElement quantity = driver.findElement(By.xpath("//input[@id='quantity']"));
		Actions actions = new Actions(driver);
		actions.click(quantity).sendKeys(Keys.BACK_SPACE).sendKeys("4").perform();
		
		driver.findElement(By.xpath("//button[@class='btn btn-default cart']")).click();
		WebElement closeModal =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));	
		closeModal.click();
		
		WebElement viewCart =	driver.findElement(By.xpath("//a[@href='/view_cart']"));
		viewCart.click();
		
		String qtyValue =	driver.findElement(By.xpath("//tr//td[4]//button")).getText();
		Assert.assertEquals(qtyValue, "4");
		
		
		
		
		}
	}

