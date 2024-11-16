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

public class Login_Checkoutt2 {

	List<String> targetProducts =	Arrays.asList("Winter Top" , "Sleeveless Dress" , "Summer White Top" ,"Madame Top For Women");
	
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
	
	
	@Test
	public void registerCheckout() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(12));
		
		//Home page is visible 
		
		WebElement homePage =	driver.findElement(By.xpath("//img[@alt='demo website for practice']"));
		
		Assert.assertTrue(homePage.isDisplayed());
		
		List<WebElement> allProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
		
		for(String targetProduct : targetProducts)
		{
		
			WebElement product = 	allProducts.stream().filter(o -> o.findElement(By.xpath(".//p")).getText().equalsIgnoreCase(targetProduct))
			.findFirst().orElse(null);	
			
			if(product!= null)
			{
				WebElement addCart =	product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
				js.executeScript("arguments[0].scrollIntoView(true);", addCart);
				wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();
				
				//Dealing with Pop -ups you can go with wait.until
				
				WebElement closeModal =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));
				closeModal.click();
							
			}
		
		
		}
		
		WebElement viewCart =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@href='/view_cart']")));
		viewCart.click();
		
		driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();
		
		WebElement register_Login =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']")));
		js.executeScript("arguments[0].click();",register_Login );
		
		//Login User
		
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("amansindhia@gmail.com");
		
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("sindhiaAman55");
		
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		
		String userLogged =	driver.findElement(By.xpath("//a//i[@class='fa fa-user']//following-sibling::b")).getText();
		
		Assert.assertEquals(userLogged, "Amandeep Sindhia");
		
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		
		WebElement orderDone =	driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
		js.executeScript("arguments[0].scrollIntoView(true);", orderDone);
		js.executeScript("arguments[0].click();", orderDone);
		
		//Review Your Order
		
		List<WebElement> cartRows =	driver.findElements(By.xpath("//tbody//tr"));
		
		for(WebElement row : cartRows)
		{
			String pPrice =	row.findElement(By.xpath("//td[3]//p")).getText();
			String pQty =	row.findElement(By.xpath("//td[4]//button")).getText();
			String totalPrice =	row.findElement(By.xpath("//td[5]//p")).getText();
			
			Double pPrice1 =	Double.parseDouble(pPrice.replace("Rs.", ""));
			Integer qty =	Integer.parseInt(pQty);
			Double tPrice =	Double.parseDouble(totalPrice.replace("Rs.", ""));
			
			Assert.assertEquals(pPrice1*qty,tPrice);
			
			
		}
		
		String totalCartPrice =	driver.findElement(By.xpath("//tr//td[4]//p")).getText();
		
		Double tCPrice =	Double.parseDouble(totalCartPrice.replace("Rs.", ""));
		
		Assert.assertEquals(tCPrice, 3000);
		
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Thank you for the products");
		
		driver.findElement(By.xpath("//a[@href='/payment']")).click();
		
		driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys("Amandeep Sindhia");
		
		driver.findElement(By.xpath("//input[@data-qa='card-number']")).sendKeys("8875642541");
		
		driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("411");
		
		driver.findElement(By.xpath("//input[@name='expiry_month']")).sendKeys("02");
		
		driver.findElement(By.xpath("//input[@data-qa='expiry-year']")).sendKeys("2028");
		
		
		WebElement placeOrder =	driver.findElement(By.xpath("//button[@id='submit']"));
		
		js.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		
		js.executeScript("arguments[0].click();", placeOrder);
		
		String orderPlaced =	driver.findElement(By.xpath("//h2[@data-qa='order-placed']//following-sibling::p")).getText();
		
		Assert.assertEquals(orderPlaced, "Congratulations! Your order has been confirmed!");
		
		
		
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
			
	}
}
