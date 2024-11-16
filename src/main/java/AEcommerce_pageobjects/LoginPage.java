package AEcommerce_pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//All Locators and actions that belong to Login Module will be placed in Login Java class.
public class LoginPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
				
	}

	//Page Factory
	//driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("amansindhia@gmail.com");
	
	//driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("sindhiaAman55");
	
	//driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
	
	@FindBy(xpath="//input[@placeholder='Email Address']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement userPassword;
	
	@FindBy(xpath="//button[@data-qa='login-button']")
	WebElement submit;
	
	public void loginApplication(String email , String password)
	{
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
	}
	
	//String loginAccount =	driver.findElement(By.xpath("//div[@class='login-form']//h2")).getText();
	
	@FindBy(xpath="//div[@class='login-form']//h2")
	WebElement loginAccountLabel;
	
	public String loginToAccount()
	{
		return loginAccountLabel.getText();
	}
	
	//driver.findElement(By.xpath("//input[@type='password']//following-sibling::p")).getText();
	
	@FindBy(xpath="//input[@type='password']//following-sibling::p")
	WebElement errorMessage;
	
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}
	
	//String newSignup =	driver.findElement(By.xpath("//div[@class='signup-form']//h2")).getText();
	
	@FindBy(xpath="//div[@class='signup-form']//h2")
	WebElement newSignup;
	
	public String registerExistingEmail()
	{
		return newSignup.getText();
	}
	
	
	//driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Aman Sindhia");
	//driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("harrysindhia@gmail.com");
	//driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
	
	@FindBy(xpath="//input[@placeholder='Name']")
	WebElement uName;
	
	@FindBy(xpath="//input[@data-qa='signup-email']")
	WebElement uEmail;
	
	@FindBy(xpath="//button[@data-qa='signup-button']")
	WebElement signUp;
	
	public void registerUser(String uName , String uEmail)
	{
		this.uName.sendKeys(uName);
		this.uEmail.sendKeys(uEmail);
		signUp.click();
	}
	
	//String userAlreadyExist =	driver.findElement(By.xpath("//input[@value='signup']//following-sibling::p")).getText();
	
	@FindBy(xpath="//input[@value='signup']//following-sibling::p")
	WebElement existingUser;
	
	public String emailExist()
	{
		return existingUser.getText();
	}
}
