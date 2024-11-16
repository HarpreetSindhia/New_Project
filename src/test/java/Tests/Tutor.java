package Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tutor {

	public class AddProductsIntoCART {
	    List<String> targetProducts = Arrays.asList("Blue Top", "Sleeveless Dress");

	   // @Test
	    public void addProducts() throws InterruptedException {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get("https://automationexercise.com/");

	        // Click on the Products button
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.findElement(By.xpath("//a//i[@class='material-icons card_travel']")).click();
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        for (String targetProduct : targetProducts) {
	            // Find and add each target product to the cart
	            List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='single-products']"));
	            WebElement product = allProducts.stream()
	                    .filter(p -> p.findElement(By.tagName("p")).getText().equalsIgnoreCase(targetProduct))
	                    .findFirst()
	                    .orElse(null);

	            if (product != null) {
	                // Scroll to and click "Add to Cart"
	                WebElement addToCart = product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
	                js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
	                wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();

	                // Close the modal
	                WebElement continueShop = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-success close-modal btn-block']")));
	                continueShop.click();

	                // Ensure modal is closed before proceeding
	                wait.until(ExpectedConditions.invisibilityOf(continueShop));

	                // Pause between actions
	                Thread.sleep(1000);
	            }
	        }

	        // Click view cart to verify added products
	        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
	        viewCart.click();

	       // driver.quit();
	    }
	    
	
	        @Test
	        public void addressDetails()
	        {
	        	  WebDriverManager.chromedriver().setup();
	            // Set the path to the chromedriver executable
	            //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	            // Initialize the ChromeDriver
	            WebDriver driver = new ChromeDriver();

	            // Open the webpage containing the address details
	            driver.get("https://automationexercise.com/");
	            
	            driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
	            
	            driver.findElement(By.xpath("//a[@class=btn btn-default check_out']")).click();

	            // Locate the delivery address element
	            WebElement deliveryAddressElement = driver.findElement(By.xpath("//div[contains(text(), 'YOUR DELIVERY ADDRESS')]/following-sibling::div"));

	            // Locate the billing address element
	            WebElement billingAddressElement = driver.findElement(By.xpath("//div[contains(text(), 'YOUR BILLING ADDRESS')]/following-sibling::div"));

	            // Expected address details
	            String expectedAddress = "Mrs. Amandeep Sindhia\nMcLaren\n223, Kingfisher Avenue\nTownsVile, Brisbane\nBrisbane Florida 7894003\nAustralia\n9875495822";

	            // Get the text from the delivery address element
	            String deliveryAddress = deliveryAddressElement.getText().trim();

	            // Get the text from the billing address element
	            String billingAddress = billingAddressElement.getText().trim();

	            // Verify the delivery address
	            Assert.assertEquals(deliveryAddress, expectedAddress, "Delivery address is incorrect.");

	            // Verify the billing address
	            Assert.assertEquals(billingAddress, expectedAddress, "Billing address is incorrect.");

	            // Close the browser
	            driver.quit();
	        }
	    }

	}


