package Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RemoveProductsFromCartTest {

	List<String> targetProducts = Arrays.asList("Sleeveless Unicorn Patch Gown - Pink" , "Blue Cotton Indie Mickey Dress", "Premium Polo T-Shirts");
	
	
	@Test
	public void removeProduct()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		
		List<WebElement> allProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
		
		for(String targetProduct : targetProducts)
		{
			WebElement product =	allProducts.stream().filter(t -> t.findElement(By.xpath(".//p")).getText().equalsIgnoreCase(targetProduct))
			.findFirst().orElse(null);
			
			if(product!= null)
			{
				WebElement addCart =	product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
				js.executeScript("arguments[0].scrollIntoView(true);", addCart);
				wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();
				
				WebElement closeModal =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));
				closeModal.click();
			}
		}
		
		//View Cart is written outside the for Loop as we need to view cart after adding all the products to the Cart
		
		WebElement viewCart =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
		viewCart.click();
		
		WebElement cartTab =	driver.findElement(By.xpath("//a[@href='/view_cart']"));
		Assert.assertTrue(cartTab.isEnabled());
		
		while(true)
		{
			List<WebElement> cartRows =	driver.findElements(By.xpath("//tbody//tr"));
			if(cartRows.isEmpty())
			{
				break;
			}
			
			try {
				
				WebElement deleteItem =	cartRows.get(0); // Grabs the first element
				WebElement itemDeleted =	deleteItem.findElement(By.xpath("//td[6]//a"));
				js.executeScript("arguments[0].scrollIntoView(true);", itemDeleted);
				js.executeScript("arguments[0].click();", itemDeleted);
				
			}
			catch(StaleElementReferenceException e)
			{
				System.out.println("Stale Element");
			}
		}
		
		
		WebElement emptyCart =	driver.findElement(By.xpath("//p//b"));
		
		Assert.assertTrue(emptyCart.isDisplayed());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*while (true) {
	        List<WebElement> cartProductsDelete = driver.findElements(By.xpath("//tbody//tr"));
	        if (cartProductsDelete.isEmpty()) {
	            break;
	        }
	        try {
	            WebElement deleteItem = cartProductsDelete.get(0); // Always get the first item
	            WebElement deleted = deleteItem.findElement(By.xpath("//td[6]//a"));
	            js.executeScript("arguments[0].scrollIntoView(true);", deleted);
	            js.executeScript("arguments[0].click();", deleted);

	            // Optional: Wait for the item to disappear to avoid stale element error
	          
	            wait.until(ExpectedConditions.stalenessOf(deleteItem));
	        } catch (StaleElementReferenceException e) {
	            // Re-attempt if stale element exception occurs
	            System.out.println("Encountered StaleElementReferenceException, retrying...");
	        }
	    }*/
	}
}
