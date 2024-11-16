package AEcommerce_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ContactUsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//String textMsg =	driver.findElement(By.xpath("//div[@class='contact-form']//h2")).getText();
	
	@FindBy(xpath="//div[@class='contact-form']//h2")
	WebElement textMsg;
	
	public String contactUsText()
	{
		return textMsg.getText();
	}
	
	
	//driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Harpreet Sindhia");
	//driver.findElement(By.xpath("//input[@name='email']")).sendKeys("harrysindhia@gmail.com");
	//driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("Logout not working");
	//driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Please fix the logout button");
	
	//JavascriptExecutor js = (JavascriptExecutor)driver;
	//WebElement uploadFile =	driver.findElement(By.xpath("//input[@name='upload_file']"));
	
	//uploadFile.sendKeys("C:\\Users\\Dell\\Pictures\\Screenshots\\ErrorLogout.png");
	
	//WebElement submitBtn = driver.findElement(By.xpath("//input[@name='submit']"));
	//js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
	//js.executeScript("arguments[0].click();", submitBtn);
	
	@FindBy(xpath="//input[@name='name']")
	WebElement user;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='subject']")
	WebElement subject;
	
	@FindBy(xpath="//textarea[@id='message']")
	WebElement textArea;
	
	@FindBy(xpath="//input[@name='upload_file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//input[@name='submit']")
	WebElement submitButton;
	
	public void contactDetails(String user , String email , String subject , String textArea , String uploadFile )
	{
		
		this.user.sendKeys(user);
		this.email.sendKeys(email);
		this.subject.sendKeys(subject);
		this.textArea.sendKeys(textArea);
		this.uploadFile.sendKeys(uploadFile);
		scrollIntoView(submitButton);
		elementToBeClickable(submitButton);	
		
	}
	
	//String successMessage =	driver.findElement(By.xpath("//div[@class='status alert alert-success']")).getText();
	
	@FindBy(xpath="//div[@class='status alert alert-success']")
	WebElement successMessage;
	
	public String getSuccessMessage()
	{
		return successMessage.getText();
	}
	
	//driver.findElement(By.xpath("//a//i[@class='fa fa-angle-double-left']")).click();
	
	@FindBy(xpath="//a//i[@class='fa fa-angle-double-left']")
	WebElement homeTab;
	
	public void homeTab()
	{
		homeTab.click();
	}
	
}
