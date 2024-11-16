package Tests;

import java.time.Duration;
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

public class SearchProducts {

	String searchProduct = "saree";

	@Test
	public void searchProduct() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		String productPage = driver.getTitle();
		Assert.assertEquals(productPage, "Automation Exercise - All Products");
		driver.findElement(By.xpath("//input[@id='search_product']")).sendKeys(searchProduct);
		driver.findElement(By.xpath("//button[@id='submit_search']")).click();
		String textLabel = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
		Assert.assertTrue(textLabel.equalsIgnoreCase("Searched Products"));
		// Verify all the Products related to search are visible
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='single-products']"));
		for (WebElement product : allProducts) {
			String productName = product.findElement(By.xpath(".//p")).getText();
			boolean matchProduct = productName.toLowerCase().contains(searchProduct);
			Assert.assertTrue(matchProduct);

		}

		// Add searched Products to Cart

		for (WebElement product : allProducts) {
			String productName = product.findElement(By.xpath(".//p")).getText();
			if (productName.toLowerCase().contains(searchProduct)) {
				WebElement addCart = product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
				js.executeScript("arguments[0].scrollIntoView(true);", addCart);
				wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();

				WebElement closeModal = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@data-dismiss='modal']")));
				closeModal.click();

			}
		}

		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
		viewCart.click();

		// Verify added products are visible in the Cart

		List<WebElement> cartRows = driver.findElements(By.xpath("//tbody//tr"));

		for (WebElement row : cartRows) {
			String productName = row.findElement(By.xpath(".//td[@class='cart_description']//a")).getText();
			boolean matchFound = productName.toLowerCase().contains(searchProduct);
			Assert.assertTrue(matchFound);
		}

		driver.findElement(By.xpath("//a[@href='/login']")).click();

		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("harrysindhia@gmail.com");

		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("sindhiaHarry55");

		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		
		//Verify those searched Products are visible in the cart after login
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//tbody//tr"));

		for (WebElement row : cartProducts) {
			String productName = row.findElement(By.xpath(".//td[@class='cart_description']//a")).getText();
			boolean matchFound = productName.toLowerCase().contains(searchProduct);
			Assert.assertTrue(matchFound);
		}
		
		driver.quit();

	}
}
