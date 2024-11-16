package AEcommerce_pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractComponents {

	WebDriver driver;
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//String productsPage = 	driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
	
	
	
	
	//WebElement viewProduct =	driver.findElement(By.xpath("//a//i[@class='fa fa-plus-square']"));
	
	@FindBy(xpath="//a//i[@class='fa fa-plus-square']")
	WebElement viewProduct;
	
	public ProductDetailsPage clickViewProduct()
	{
		scrollIntoView(viewProduct);
		waitForTheWebElementToBeClickable(viewProduct);
		elementToBeClickable(viewProduct);
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		return productDetailsPage;
	}
	
	//List<WebElement> allProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
	
	//WebElement closeModal =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));)
	@FindBy (xpath="//button[@data-dismiss='modal']")
	WebElement clseModal;
	
	@FindBy(xpath="//div[@class='single-products']")
	List<WebElement> allProducts;
	
	public void addProductsIntoCart(List<String> targetProducts)
	{
		for(String targetProduct : targetProducts)
		{
			WebElement product =	allProducts.stream().filter(o -> o.findElement(By.xpath(".//p")).getText().equalsIgnoreCase(targetProduct))
			.findFirst().orElse(null);
			
			if(product != null)
			{
				WebElement addCart =	product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
				js.executeScript("arguments[0].scrollIntoView(true);", addCart);
				waitForTheWebElementToBeClickable(addCart);
				addCart.click();
				
				//An Pop up appears for Continue Shopping click on that 
				
				waitForTheWebElementToBeClickable(clseModal);
				clseModal.click();
			}
		}
	}
	
	//WebElement viewCart =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
	

	@FindBy(xpath="//a[@href='/view_cart']")
	WebElement viewCart;

	public CartPage clickViewCartBtn()
	{
		waitForTheWebElementToBeClickable(viewCart);
		viewCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	//String productDetailsPage = driver.getTitle();
	
	public String getProdPageTitle()
	{
		return driver.getTitle();
	}
	
	//driver.findElement(By.xpath("//input[@name='search']")).sendKeys(searchProduct);
	
	@FindBy(xpath="//input[@name='search']")
	WebElement searchInput;
	
	//driver.findElement(By.xpath("//button[@id='submit_search']")).click();
	
	@FindBy(xpath="//button[@id='submit_search']")
	WebElement srchBtn;
	public void search(String input)
	{
		searchInput.sendKeys(input);
		srchBtn.click();
	}
	
	//List<WebElement> searchProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
	
	@FindBy(xpath="//div[@class='single-products']")
	List<WebElement> searchProducts;
	
	public boolean areSearchProductsVisible(String searchProduct)
	{
	for(WebElement product : searchProducts)
	{
		String productName = product.findElement(By.xpath(".//p")).getText();
		boolean matchProduct =	productName.toLowerCase().contains(searchProduct);
		if(!matchProduct)
		{
			return false;
		}
		
	}
	return true;
	}
	
	
	public void addSearchProductsToCart(String searchProduct)
	{
		for (WebElement product : searchProducts) 
		{
			String productName = product.findElement(By.xpath(".//p")).getText();
			if (productName.toLowerCase().contains(searchProduct))
			{
				WebElement addCart = product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
				scrollIntoView(addCart);
				waitForTheWebElementToBeClickable(addCart);
				elementToBeClickable(addCart);
				waitForTheWebElementToBeClickable(clseModal);
				elementToBeClickable(clseModal);

		}
	}	
}
	
	//WebElement reviewLabel =	driver.findElement(By.xpath("//a[@href='#reviews']"));
	//js.executeScript("arguments[0].scrollIntoView(true);", reviewLabel);
	//Assert.assertTrue(reviewLabel.isDisplayed());
	
	@FindBy(xpath="//a[@href='#reviews']")
	WebElement reviewLabel;
	
	public boolean isReviewLabelDisplayed()
	{
		return reviewLabel.isDisplayed();
	}
	

	//driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Harpreet Sindhia");
	//driver.findElement(By.xpath("//input[@id='email']")).sendKeys("harrysindhia@gmail.com");
	//driver.findElement(By.xpath("//textarea[@name='review']")).sendKeys("I liked the product");
	//driver.findElement(By.xpath("//button[@id='button-review']")).click();
	
	@FindBy(xpath="//input[@id='name']")
	WebElement name;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	
	@FindBy(xpath="//textarea[@name='review']")
	WebElement review;
	
	@FindBy(xpath="//button[@id='button-review']")
	WebElement submitReview;
	
	public void addReviewOnProduct(String name , String email , String review)
	{
		
		this.name.sendKeys(name);
		this.email.sendKeys(email);
		this.review.sendKeys(review);
		elementToBeClickable(submitReview);
	}
	
	//String successMessage =	driver.findElement(By.xpath("//div[@class='alert-success alert']//span")).getText();
	
	@FindBy(xpath="//div[@class='alert-success alert']//span")
	WebElement reviewSubmitMsg;
	
	public String reviewSubmitted()
	{
		return reviewSubmitMsg.getText();
	}
	
	//WebElement brands =	driver.findElement(By.xpath("//div[@class='brands_products']"));
	
	@FindBy(xpath="//div[@class='brands_products']")
	WebElement brands;
	
	public boolean areBrandsDispalyed()
	{
		return brands.isDisplayed();
	}
	
	//WebElement productName =	driver.findElement(By.xpath("//a[text()='H&M']"));
	
	//js.executeScript("arguments[0].click();", productName);
	
	@FindBy(xpath="//a[text()='H&M']")
	WebElement productName;
	
	public void clickonBrandName()
	{
		elementToBeClickable(productName);
	}
	
	
	//String textMessage =	driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
	
	@FindBy(xpath="//h2[@class='title text-center']")
	WebElement brandLabel;
	
	public String isBrandLabelProductDisplayed()
	{
		return brandLabel.getText();
	}
	
	//List<WebElement> displayedProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
	
		@FindBy(xpath="//div[@class='single-products']")
		List<WebElement> displayedProducts;
		
		public boolean areProductsDisplayed()
		{
			
			return displayedProducts.isEmpty();
		}
		
		//WebElement brandName = driver.findElement(By.xpath("//a[text()='Mast & Harbour']"));
		
		//js.executeScript("arguments[0].click();", brandName);
		
		@FindBy(xpath="//a[text()='Mast & Harbour']")
		WebElement brandName;
		
		public void clickBrandName()
		{
			waitForTheWebElementToBeClickable(brandName);
			elementToBeClickable(brandName);
		}
		
		//String productsLabel =	driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
		
		@FindBy(xpath="//h2[@class='title text-center']")
		WebElement productLabel;
		
		public String getProductLabel()
		{
			return productLabel.getText();
		}
		
		//List<WebElement> mastHarbourProduct =	driver.findElements(By.xpath("//div[@class='single-products']"));
		
		@FindBy(xpath="//div[@class='single-products']")
		List<WebElement> mastHarbourProduct;
		
		public boolean areBrandProductDisplayed()
		{
			return mastHarbourProduct.isEmpty();
		}
		
		
}
