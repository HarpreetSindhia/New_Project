package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterUser {
	
	@Test
	public void registerUser()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		//Verify that home page is visible successfully
		
		String homePage =	driver.getTitle();
		Assert.assertEquals(homePage, "Automation Exercise");
		//WebElement homeLogo =	driver.findElement(By.cssSelector("img[alt='demo website for practice']"));
		//Assert.assertTrue(homeLogo.isDisplayed());
		//Click on Signup/Login
		driver.findElement(By.xpath("//a//i[@class='fa fa-lock']")).click();
		//New User Signup is visible
		String newSignup =	driver.findElement(By.xpath("//div[@class='signup-form']//h2")).getText();
		Assert.assertEquals(newSignup, "New User Signup!");
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Navdeep Sindhia");
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("navsindhia@gmail.com");
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
		//Verify Enter account information is visible
		WebElement accountInfo =	driver.findElement(By.xpath("//b[text()='Enter Account Information']"));
		Assert.assertTrue(accountInfo.isDisplayed());
		driver.findElement(By.xpath("//input[@value='Mr']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("sindhiaNav55");
		//Dropdown for Date of Birth
		Select dropDown = new Select(driver.findElement(By.xpath("//select[@id='days']")));
		dropDown.selectByVisibleText("2");
		String day =	dropDown.getFirstSelectedOption().getText();
		Assert.assertEquals(day, "2");
		Select dropDown1 = new Select(driver.findElement(By.xpath("//select[@id='months']")));
		dropDown1.selectByVisibleText("September");
		String month =	dropDown1.getFirstSelectedOption().getText();
		Assert.assertEquals(month, "September");
		Select dropDown2 = new Select(driver.findElement(By.xpath("//select[@id='years']")));
		dropDown2.selectByVisibleText("1998");
		String year =	dropDown2.getFirstSelectedOption().getText();
		Assert.assertEquals(year, "1998");
		//WebDriver Wait 
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(15));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement checkBox =	driver.findElement(By.xpath("//input[@id='newsletter']"));
		js.executeScript("arguments[0].scrollIntoView(true);", checkBox);
		wait.until(ExpectedConditions.elementToBeClickable(checkBox));
		js.executeScript("arguments[0].click();", checkBox);
		//checkBox.click();
		WebElement checkBox1 =	driver.findElement(By.xpath("//input[@id='optin']"));
		wait.until(ExpectedConditions.elementToBeClickable(checkBox1));
		checkBox1.click();
		
		
		driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Navdeep");
		driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Sindhia");
		driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Tesla");
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("223, Kingfisher Avenue");
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys("TownsVile , Brisbane");
		//DropDown select Country
		Select dropDown4 = new Select(driver.findElement(By.xpath("//select[@id='country']")));
		dropDown4.selectByVisibleText("Australia");
		String countrySelect =	dropDown4.getFirstSelectedOption().getText();
		Assert.assertEquals(countrySelect, "Australia");
		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Florida");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Brisbane");
		driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("7894003");
		driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("9875495822");
		WebElement createAccount =	driver.findElement(By.xpath("//button[@data-qa='create-account']"));
		js.executeScript("arguments[0].scrollIntoView(true);", createAccount);
		wait.until(ExpectedConditions.elementToBeClickable(createAccount));
		createAccount.click();
		WebElement accCreate =	driver.findElement(By.xpath("//b"));
		Assert.assertTrue(accCreate.isDisplayed());
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		
		
		WebElement userLoggedIn =	driver.findElement(By.xpath("//a//i[@class='fa fa-user']"));
		Assert.assertTrue(userLoggedIn.isDisplayed());	
		
	}

}
