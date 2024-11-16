package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class View_CartBrandProducts {

	@Test
	public void viewCartBrand()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement brands =	driver.findElement(By.xpath("//div[@class='brands_products']"));
		Assert.assertTrue(brands.isDisplayed());
		
		WebElement productName =	driver.findElement(By.xpath("//a[text()='H&M']"));
		
		js.executeScript("arguments[0].click();", productName);
		
		String textMessage =	driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
		
		Assert.assertTrue(textMessage.equalsIgnoreCase("Brand - H&M Products"));
		
		List<WebElement> displayedProd =	driver.findElements(By.xpath("//div[@class='single-products']"));
		
		Assert.assertFalse(displayedProd.isEmpty());
		
		WebElement brandName = driver.findElement(By.xpath("//a[text()='Mast & Harbour']"));
		
		js.executeScript("arguments[0].click();", brandName);
		
		String productsLabel =	driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
		
		Assert.assertTrue(productsLabel.equalsIgnoreCase("Brand - Mast & Harbour Products"));
		
		
		
		
		
		
		
	}
}
