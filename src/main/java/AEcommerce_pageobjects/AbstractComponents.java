package AEcommerce_pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This Java class acts as a Parent class to all Page Object Classes and has been created to place all the generic locators and
//methods.
public class AbstractComponents {

	WebDriver driver;
	JavascriptExecutor js;
	Actions actions;
	
	public AbstractComponents(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor)driver;
		this.actions = new Actions(driver);
	}
	
	//Page Factory
	
	//driver.findElement(By.xpath("//a[@href='/login'])).click();
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement signup_Login;
	
	public LoginPage signUpLoginTab()
	{
		signup_Login.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
		
	}
	
	//WebElement userLoggedIn =	driver.findElement(By.xpath("//a[@href='/login']));
	
	@FindBy(xpath="//i[@class='fa fa-user']//following-sibling::b")
	WebElement userName;
	
	public String userLogIn()
	{
		return userName.getText();
	}
	
	//driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
	
	@FindBy(xpath="//a[@href='/view_cart']")
	WebElement cartTab;
	
	public void goToCartTab()
	{
		cartTab.click();
	}
	
	//driver.findElement(By.xpath("//a//i[@class='fa fa-envelope']")).click();
	
	@FindBy(xpath="//a//i[@class='fa fa-envelope']")
	WebElement contactUsTab;
	
	public ContactUsPage contactUs()
	{
		contactUsTab.click();
		ContactUsPage contactUsPage = new ContactUsPage(driver);
		return contactUsPage;
	}
	
	//driver.findElement(By.xpath("//a[@href='/products']")).click();
	
	@FindBy(xpath="//a[@href='/products']")
	WebElement productsTab;
	
	public ProductPage productsTab()
	{
		productsTab.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	public void waitForTheWebElementToBeClickable(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForTheElementToBeClickable(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForTheWebElementToBeVisible(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	public void scrollWindow(int dimension)
	{
		
		js.executeScript("window.scrollBy(0,"+dimension+")");
	}
	
	public void scrollIntoView(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void elementToBeClickable(WebElement element)
	{
		js.executeScript("arguments[0].click();", element );
	}
	
	public void elementToBeByClickable(By findBy)
	{
		js.executeScript("arguments[0].click();", findBy );
	}
	
	public void scroll(String name)
	{
		js.executeScript("arguments[0].scrollIntoView(true);", name);
	}
	
	public void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}
	
	public void scrollBottomOfPage()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void scrollTopOfPage()
	{
		js.executeScript("window.scrollTo(0,0);");
	}
}
