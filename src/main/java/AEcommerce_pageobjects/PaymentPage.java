package AEcommerce_pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponents {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@data-qa='name-on-card']")
	WebElement cardHolder;
	
	@FindBy(xpath="//input[@data-qa='card-number']")
	WebElement cardNumber;
	//expiry_year
	@FindBy(xpath="//input[@data-qa='cvc']")
	WebElement cvc;
	
	@FindBy(xpath="//input[@data-qa='expiry-month']")
	WebElement month;
	
	@FindBy(css=".expiration input[name='expiry_year']")
	WebElement year;
	
	public void cardDetails(String cardHolderName , String cardNum , String cardCVC , String expMonth , String expYear)
	{
		
		cardHolder.sendKeys(cardHolderName);
		cardNumber.sendKeys(cardNum);
		cvc.sendKeys(cardCVC);
		month.sendKeys(expMonth);
		scrollIntoView(year);
		year.sendKeys(expYear);
	
	}
	
	//WebElement placeOrder = driver.findElement(By.xpath("//button[@data-qa='pay-button']"));
	//js.executeScript("arguments[0].click();", placeOrder);
	
	@FindBy(xpath="//button[@data-qa='pay-button']")
	WebElement submitOrder;
	
	public void submitOrder()
	{
		elementToBeClickable(submitOrder);
	}
	
	//String successMessage = driver.findElement(By.xpath("//h2[@data-qa='order-placed']//following-sibling::p"))
			//.getText().trim();
	
	@FindBy(xpath="//h2[@data-qa='order-placed']//following-sibling::p")
	WebElement successMessage;
	
	public String getSuccessMessage()
	{
		return successMessage.getText().trim();
	}
	
	//driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();

	//driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
	
	@FindBy(xpath="//a[@class='btn btn-default check_out']")
	WebElement downloadInvoice;
	
	@FindBy(xpath="//a[@data-qa='continue-button']")
	WebElement cont;
	
	public void invoiceContinue()
	{
		downloadInvoice.click();
		cont.click();
	}
	
}
