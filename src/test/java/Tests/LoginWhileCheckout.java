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

public class LoginWhileCheckout {

	List<String> targetProducts = Arrays.asList("Pure Cotton Neon Green Tshirt", "Regular Fit Straight Jeans",
			"Green Side Placket Detail T-Shirt");

	String expectedAddress = "Mrs. Amandeep Sindhia\nMicrosoft\n15 Charlses Babbage Town\nFrank Street\nNorfolk Virginia 885541\nUnited States\n9988764845";

	@Test
	public void loginCheckout()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(12));
		//Verify that you are on HomePage
		String homePage =	driver.getTitle();
		Assert.assertEquals(homePage, "Automation Exercise");
		//Add Products to Cart
		List<WebElement> allProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
		for(String targetProduct : targetProducts)
		{
			WebElement product =	allProducts.stream().filter(r -> r.findElement(By.xpath(".//p")).getText().equalsIgnoreCase(targetProduct))
			.findFirst().orElse(null);
			
			WebElement addToCart =	product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
			js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
			wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
			//Since there is a pop up dialog for continue shopping go with wait.until
			WebElement closeModal =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));
			closeModal.click();	
		}
		//Since viewcart is also inside a pop up dialog just go with wait.until
		WebElement viewCart =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
		viewCart.click();
		//Verify you are on the Cart Page
		String cartPage =	driver.getTitle();
		Assert.assertEquals(cartPage,"Automation Exercise - Checkout");
		driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();
		//Since register/signup is inside a pop up dialog go with wait.until
		WebElement registerSignup =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']")));
		js.executeScript("arguments[0].click();", registerSignup);
		
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("amansindhia@gamil.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("sindhiaAmo55");
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		//Verify logged in as username at top
		String userName =	driver.findElement(By.xpath("//i[@class='fa fa-user']//following-sibling::b")).getText();
		Assert.assertEquals(userName, "Amandeep Sindhia");
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();
		//Verify Address Details
		String expectedDelAddress = "Your delivery address\n" + expectedAddress;
		String addressDel =	driver.findElement(By.xpath("//ul[@id='address_delivery']")).getText().trim();		
		Assert.assertTrue(expectedDelAddress.equalsIgnoreCase(addressDel));
		//Verify Billing Address
		String expectedbillingAddress = "Your billing address\n" + expectedAddress;
		String billingAddress =	driver.findElement(By.xpath("//ul[@id='address_invoice']")).getText().trim();
		Assert.assertTrue(expectedbillingAddress.equalsIgnoreCase(billingAddress));
		//Review Your Order
		List<WebElement> cartRows =	driver.findElements(By.xpath("//tbody//tr"));
		Double calculatedTotal = 0.0;
		
		for(String targetProduct : targetProducts)
		{
			boolean matchFound = false;
			
			for(WebElement row : cartRows)
			{
				
				String productName = row.findElement(By.xpath(".//td[@class='cart_description']//h4")).getText();
				
				if(productName.equalsIgnoreCase(targetProduct))
				{
					matchFound = true;
				
					//Fetch price , quantity , displayPrice
				String productPrice = row.findElement(By.xpath(".//td[@class='cart_price']//p")).getText();
				String productQuantity = row.findElement(By.xpath(".//td[@class='cart_quantity']//button")).getText();
				String displayPrice = row.findElement(By.xpath(".//td[@class='cart_total']//p")).getText();
				
				
				//Convert string into numbers	
					
					Double price =	Double.parseDouble(productPrice.replace("Rs.", "").trim());
					Integer quantity =	Integer.parseInt(productQuantity.trim());
					Double displayPrice1 =	Double.parseDouble(displayPrice.replace("Rs.", "").trim());
					
					Assert.assertEquals(price * quantity, displayPrice1,0.01);
					
					System.out.println("Product :" + productName + ",Price :" + price + ",Quantity :" + quantity + ",DispalyPrice :" + displayPrice1);
					
					calculatedTotal = calculatedTotal + displayPrice1;
					
					break;
				}
			}
		}
		
		String totalAmount = driver.findElement(By.xpath("//td[@colspan='2']//following-sibling::td//p")).getText();
		Double parsedTotalAmount =	Double.parseDouble(totalAmount.replace("Rs.", "").trim());
		Assert.assertEquals(parsedTotalAmount , calculatedTotal , 0.01);
		
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Thank you");
		driver.findElement(By.cssSelector(".check_out")).click();
		
		driver.findElement(By.xpath("//input[@data-qa='name-on-card']")).sendKeys("Amandeep Sindhia");
		driver.findElement(By.xpath("//input[@data-qa='card-number']")).sendKeys("778899224446");
		driver.findElement(By.xpath("//input[@data-qa='cvc']")).sendKeys("412");
		driver.findElement(By.xpath("//input[@data-qa='expiry-month']")).sendKeys("11");
		driver.findElement(By.xpath("//input[@data-qa='expiry-year']")).sendKeys("2028");
		
		WebElement placeOrder =	driver.findElement(By.xpath("//button[@data-qa='pay-button']"));
		js.executeScript("arguments[0].click();", placeOrder);
		String successMessage =	driver.findElement(By.xpath("//h2[@data-qa='order-placed']//following-sibling::p")).getText().trim();
		Assert.assertEquals(successMessage, "Congratulations! Your order has been confirmed!");
		
		driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();
		
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		
	}
}
