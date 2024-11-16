package AEcommerce_pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductDetailsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public String getProdPageTitle()
	{
		return driver.getTitle();
	}
	
	@FindBy(xpath="//div[@class='product-information']//h2")
	WebElement prodName;
	
	@FindBy(xpath="//h2//following-sibling::p[1]")
	WebElement prodCategory;
	
	@FindBy(xpath="//label//preceding-sibling::span")
	WebElement prodPrice;
	
	@FindBy(xpath="//div[@class='product-information']//p[2]")
	WebElement prodAvailability;
	
	@FindBy(xpath="//div[@class='product-information']//p[3]")
	WebElement prodCondition;
	
	@FindBy(xpath="//div[@class='product-information']//p[4]")
	WebElement prodBrand;
	
	public WebElement getProductDetails()
	{
		return prodName;	
	}
	
	public WebElement getProdCategory()
	{
		return prodCategory;
	}
	
	public	WebElement getProdPrice()
	{
		return prodPrice;
	}
	
	public WebElement getprodAvailability()
	{
		return prodAvailability;
	}
	
	public WebElement prodCondition()
	{
		return prodCondition;
	}
	
	public WebElement prodBrand()
	{
		return prodBrand;
	}
	

	//WebElement quantity = driver.findElement(By.xpath("//input[@id='quantity']"));
	
	@FindBy(xpath="//input[@id='quantity']")
	WebElement quantity;
	
	public void updateQuantity()
	{
		actions.click(quantity).sendKeys(Keys.BACK_SPACE).sendKeys("4").perform();
	}
	
	//driver.findElement(By.xpath("//button[@class='btn btn-default cart']")).click();
	
	@FindBy(xpath="//button[@class='btn btn-default cart']")
	WebElement addCart;
	
	public void clickAddToCart()
	{
		waitForTheWebElementToBeClickable(addCart);
		addCart.click();
	}
	
	//WebElement closeModal =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));)
		@FindBy (xpath="//button[@data-dismiss='modal']")
		WebElement clseModal;

	public void continueShopping()
	{
		waitForTheWebElementToBeClickable(clseModal);
		clseModal.click();
	}
	
	@FindBy(xpath="//a[@href='/view_cart']")
	WebElement viewCart;
	
	public CartPage clickViewCartBtn()
	{
		waitForTheWebElementToBeClickable(viewCart);
		viewCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
}
