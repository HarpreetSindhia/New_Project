package AEcommerce_pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//String cartPage = driver.getTitle();
	
		public String cartPageVerify()
		{
			return driver.getTitle();
		}

	
	//By.xpath("//a[@href='/login']");
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement register_Login;
	
	//driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();
	
	@FindBy(xpath="//a[@class='btn btn-default check_out']")
	WebElement proceedCheckout;
	
	public LoginPage goToCheckoutBtn()
	{
		proceedCheckout.click();
		waitForTheWebElementToBeClickable(register_Login);
		elementToBeClickable(register_Login);
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
		
	}
	
	//WebElement orderDone =	driver.findElement(By.css(".check_out"));
	//js.executeScript("arguments[0].scrollIntoView(true);", orderDone);
	//js.executeScript("arguments[0].click();", orderDone);
	
	//By.xpath("//a[@class='btn btn-default check_out']"
	
	
	
	@FindBy(css=".check_out")
	WebElement checkout;
	
	public void proceedToCheckout()
	{
		scrollIntoView(checkout);
		elementToBeClickable(checkout);
	}
	
	//String addressDel = driver.findElement(By.xpath("//ul[@id='address_delivery']")).getText().trim();
	
	//String billingAddress = driver.findElement(By.xpath("//ul[@id='address_invoice']")).getText().trim();
	
	@FindBy(xpath="//ul[@id='address_delivery']")
	WebElement yourdeliveryAddress;
	
	public String getDeliveryAddres()
	{
		waitForTheWebElementToBeVisible(yourdeliveryAddress);
		return yourdeliveryAddress.getText().trim();
	}
	
	@FindBy(xpath="//ul[@id='address_invoice']")
	WebElement yourbillingAddress;
	
	public String getBillingAddress()
	{
		waitForTheWebElementToBeVisible(yourbillingAddress);
		return yourbillingAddress.getText().trim();
	}
	
	//List<WebElement> cartRows = driver.findElements(By.xpath("//tbody//tr"));
	
	@FindBy(xpath="//tbody//tr")
	List<WebElement> cartRows;
	
	public List<WebElement> getCartRows()
	{
		return cartRows;
	}
	
	//String productName = row.findElement(By.xpath(".//td[@class='cart_description']//h4")).getText();
	
	public String getProductName(WebElement row)
	{
		return row.findElement(By.xpath(".//td[@class='cart_description']//a")).getText();
	}
	
	//String productPrice = row.findElement(By.xpath(".//td[@class='cart_price']//p")).getText();
	
	public double getProductPrice(WebElement row)
	{
		String productPrice = row.findElement(By.xpath(".//td[@class='cart_price']//p")).getText();
		return Double.parseDouble(productPrice.replace("Rs.", "").trim());
	}
	
	public int getProductQuantity(WebElement row)
	{
		String productQuantity = row.findElement(By.xpath(".//td[@class='cart_quantity']//button")).getText();
		return Integer.parseInt(productQuantity.trim());
	}
	
	public double getDisplayPrice(WebElement row)
	{
		String displayPrice = row.findElement(By.xpath(".//td[@class='cart_total']//p")).getText();
		return Double.parseDouble(displayPrice.replace("Rs.", "").trim());
	}
	
	public double getTotalAmount()
	{
		String totalAmount = driver.findElement(By.xpath("//td[@colspan='2']//following-sibling::td//p")).getText();
		return Double.parseDouble(totalAmount.replace("Rs.", "").trim());
	}
	
	@FindBy(xpath="//textarea[@name='message']")
	WebElement textArea;
	
	public void addComments(String text)
	{
		textArea.sendKeys(text);
	}
	
	//driver.findElement(By.cssSelector(".check_out")).click();
	
	@FindBy(css=".check_out")
	WebElement placeOrder;
	
	public PaymentPage placeProductOrder()
	{
		elementToBeClickable(placeOrder);
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}
	
	//String qtyValue =	driver.findElement(By.xpath("//td[@class='cart_quantity']//button")).getText();
	
		@FindBy(xpath="//tbody//tr//td[@class='cart_quantity']//button")
		WebElement value;
		
		public String getQuantity()
		{
			return value.getText();
		}
		
		//List<WebElement> cartRowProducts = driver.findElements(By.xpath("//tbody//tr"));
		
		@FindBy(xpath="//tbody//tr")
		List<WebElement> cartRowProducts;

		public boolean verifyProductsIntoCart(String searchProduct)
		{
			for (WebElement row : cartRowProducts) {
			String productName = row.findElement(By.xpath(".//td[@class='cart_description']//a")).getText();
			boolean matchFound = productName.toLowerCase().contains(searchProduct);
			if(!matchFound)
			{
				return false;
			}
		}
			
			return true;

		}
		
		
		public void deleteCartRows()
			{

				while(true)
				{
					List<WebElement> cartRows =	driver.findElements(By.xpath("//tbody//tr"));
					if(cartRows.isEmpty())
					{
						break;
					}
			
					try 
					{
				
				WebElement deleteItem =	cartRows.get(0); // Grabs the first element
				WebElement itemDeleted =	deleteItem.findElement(By.xpath("//td[6]//a"));
				scrollIntoView(itemDeleted);
				elementToBeClickable(itemDeleted);	
			}
			catch(StaleElementReferenceException e)
			{
				System.out.println("Stale Element");
			}
		}

			}
		
		//String emptyCart =	driver.findElement(By.xpath("//p[@class='text-center']//b")).getText();
		
		@FindBy(xpath="//p[@class='text-center']//b")
		WebElement text;
		
		public String getTextInfo()
		{
			return text.getText();
		}
}
	//driver.findElement(By.xpath("//input[@data-qa='name-on-card']")).sendKeys("Amandeep Sindhia");
	//driver.findElement(By.xpath("//input[@data-qa='card-number']")).sendKeys("778899224446");
	//driver.findElement(By.xpath("//input[@data-qa='cvc']")).sendKeys("412");
	//driver.findElement(By.xpath("//input[@data-qa='expiry-month']")).sendKeys("11");
	//driver.findElement(By.xpath("//input[@data-qa='expiry-year']")).sendKeys("2028");

	
	//List<WebElement> cartRows =	driver.findElements(By.xpath("//tbody//tr"));
	
	//WebElement nameElement = wait.until(ExpectedConditions.visibilityOf(row.findElement(By.xpath(".//td[@class='cart_description']//a"))));
	

		
		/*public void getProductDetails(List<String> targetProducts , double calculatedTotal)
		{
		
		for (String targetProduct : targetProducts) 
		{
			boolean matchFound = false;

			for (WebElement row : cartRows) {

				String productName = row.findElement(By.xpath(".//td[@class='cart_description']//h4")).getText();

				if (productName.equalsIgnoreCase(targetProduct)) {
					matchFound = true;

					// Fetch price , quantity , displayPrice
					String productPrice = row.findElement(By.xpath(".//td[@class='cart_price']//p")).getText();
					String productQuantity = row.findElement(By.xpath(".//td[@class='cart_quantity']//button"))
							.getText();
					String displayPrice = row.findElement(By.xpath(".//td[@class='cart_total']//p")).getText();

					// Convert string into numbers

					Double price = Double.parseDouble(productPrice.replace("Rs.", "").trim());
					Integer quantity = Integer.parseInt(productQuantity.trim());
					Double displayPrice1 = Double.parseDouble(displayPrice.replace("Rs.", "").trim());

					//Assert.assertEquals(price * quantity, displayPrice1, 0.01);

					System.out.println("Product :" + productName + ",Price :" + price + ",Quantity :" + quantity
							+ ",DispalyPrice :" + displayPrice1);

					calculatedTotal = calculatedTotal + displayPrice1;

					break;
				}
			}
		}
		
		
		
	}
}*/
		
		
	//driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Thank you for the products");
	
	//driver.findElement(By.xpath("//a[@href='/payment']")).click();
	
	//@FindBy(xpath="//textarea[@name='message']")
	//WebElement comments;
	
	//@FindBy(xpath="//a[@href='/payment']")
	//WebElement placeOrder;
	
	/*@FindBy(xpath=".//td[@class='cart_description']//a")
	WebElement nameElement;
	
	@FindBy(xpath="//tbody//tr")
	List<WebElement> cartRows;
	
	
	 public List<WebElement> getCartRows() 
	 {
	        return cartRows;
	 }
	
	public String getProductName(WebElement row) 
	{
        return row.findElement(By.xpath(".//td[@class='cart_description']//h4")).getText();
    }

    public double getProductPrice(WebElement row)
    {
        String productPrice = row.findElement(By.xpath(".//td[@class='cart_price']//p")).getText();
        return Double.parseDouble(productPrice.replace("Rs.", "").trim());
    }

    public int getProductQuantity(WebElement row)
    {
        String productQuantity = row.findElement(By.xpath(".//td[@class='cart_quantity']//button")).getText();
        return Integer.parseInt(productQuantity.trim());
    }

    public double getDisplayPrice(WebElement row)
    {
        String displayPrice = row.findElement(By.xpath(".//td[@class='cart_total']//p")).getText();
        return Double.parseDouble(displayPrice.replace("Rs.", "").trim());
    }
    
}*/
	
	//WebElement register_Login =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']")));
	
	
	
	//js.executeScript("arguments[0].click();",register_Login );
	
	



