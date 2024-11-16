package AEcommerce_pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This is the Home Page of the portal
public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void goToApplication()
	{
		driver.get("https://automationexercise.com/");
	}
	
	//String homePage =	driver.getTitle();
	
	public String homePageVerify()
	{
		return driver.getTitle();
	}
	
	//List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='single-products']"));
	
	//WebElement closeModal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));
	
	//WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
	
	@FindBy(xpath="//a[@href='/view_cart']")
	WebElement viewCart;
	
	@FindBy(xpath="//button[@data-dismiss='modal']")
	WebElement closeModal;
	
	@FindBy(xpath="//div[@class='single-products']")
	List<WebElement> allProducts;
	
	public void addProductsToCart(List<String> targetProducts)
	{
		
		for (String targetProduct : targetProducts) {
		WebElement product = allProducts.stream()
				.filter(r -> r.findElement(By.xpath(".//p")).getText().equalsIgnoreCase(targetProduct)).findFirst()
				.orElse(null);

		WebElement addToCart = product.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
		scrollIntoView(addToCart);
		waitForTheWebElementToBeClickable(addToCart);
		addToCart.click();
		// Since there is a pop up dialog for continue shopping go with wait.until
		waitForTheWebElementToBeClickable(closeModal);
		closeModal.click();
	}
		
	}
	
	public CartPage clickViewCart()
	{
		waitForTheWebElementToBeClickable(viewCart);
		viewCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	//String cartPage = driver.getTitle();
	
	public String verifyCartPage()
	{
		return driver.getTitle();
	}
	
	//String homePage =	driver.findElement(By.xpath("//div[@class='col-sm-6']/h2")).getText();
	
	@FindBy(xpath="//div[@class='col-sm-6']/h2")
	WebElement homePage;
	
	public String verifyHomePageText()
	{
		waitForTheWebElementToBeVisible(homePage);
		return homePage.getText();
	}

	//WebElement viewProduct =	driver.findElement(By.xpath("//div[@class='choose']//a"));
	//js.executeScript("arguments[0].scrollIntoView(true);", viewProduct);
	//wait.until(ExpectedConditions.elementToBeClickable(viewProduct)).click();	
		
	@FindBy(xpath="//div[@class='choose']//a")
	WebElement viewProduct;
	
	public ProductDetailsPage clickViewProduct()
	{
		scrollIntoView(viewProduct);
		waitForTheWebElementToBeClickable(viewProduct);
		viewProduct.click();
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		return productDetailsPage;
	}
	
	//String text =	driver.findElement(By.xpath("//div[@class='single-widget']//h2")).getText();
	
	@FindBy(xpath="//div[@class='single-widget']//h2")
	WebElement subscribe;
	
	public String getSubText()
	{
		waitForTheWebElementToBeVisible(subscribe);
		return subscribe.getText();
	}	
	//driver.findElement(By.xpath("//a[@id='scrollUp']")).click();
	
	@FindBy(xpath="//a[@id='scrollUp']")
	WebElement scrollUpArrow;
	
	public void scrollUp()
	{
		waitForTheWebElementToBeVisible(scrollUpArrow);
		scrollIntoView(scrollUpArrow);
		elementToBeClickable(scrollUpArrow);
	}
	
	//String	recomItems =	driver.findElement(By.xpath("//div[@class='recommended_items']//h2[@class='title text-center']")).getText();

	@FindBy(xpath="//div[@class='recommended_items']//h2[@class='title text-center']")
	WebElement recommItems;
	
	public String addRecommItems()
	{
		return recommItems.getText();
	}
	
	//List<WebElement> recommProd =	driver.findElements(By.xpath("//div[@class='single-products']"));
	
	@FindBy(xpath="//div[@class='single-products']")
	List<WebElement> recommProd;
	
	public void addRecommendItems(String targetProduct)
	{
		WebElement prod =	recommProd.stream().filter(r -> r.findElement(By.xpath(".//p")).getText().equalsIgnoreCase(targetProduct))
			.findFirst().orElse(null);
	
		if(prod!= null)
		{
			WebElement addCart =	prod.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
			scrollIntoView(addCart);
			waitForTheWebElementToBeClickable(addCart);
			elementToBeClickable(addCart);
			
			waitForTheWebElementToBeClickable(closeModal);
			elementToBeClickable(closeModal);
			waitForTheWebElementToBeClickable(viewCart);
			waitForTheWebElementToBeClickable(viewCart);
			elementToBeClickable(viewCart);
			
		}
	}

	
	//List<WebElement> cartProduct =	driver.findElements(By.xpath("//tbody//tr"));
	
	@FindBy(xpath="//tbody//tr")
	List<WebElement> cartProduct;
	
	public boolean isProductDisplayInCart(String targetProduct)
	{
		Boolean match = cartProduct.stream().anyMatch(e -> e.findElement(By.xpath("//td[@class='cart_description']//h4")).getText().equalsIgnoreCase(targetProduct));
		if(!match)
		{
			return false;
		}
		
		return true;
	}
	
	//driver.findElement(By.xpath("//input[@id='susbscribe_email']")).sendKeys("harrysindhia@gmail.com");
	//driver.findElement(By.xpath("//button[@id='subscribe']")).click();
	
	@FindBy(xpath="//input[@id='susbscribe_email']")
	WebElement subscribeEmail;
	
	@FindBy(xpath="//button[@id='subscribe']")
	WebElement subscribeBtn;
	
	public void subscribe(String email)
	{
		subscribeEmail.sendKeys(email);
		subscribeBtn.click();
	}
	
	//String successSubscribe =	driver.findElement(By.xpath("//div[@class='alert-success alert']")).getText();
	
	@FindBy(xpath="//div[@class='alert-success alert']")
	WebElement successSubscribe;
	
	public String getSubscribeMessage()
	{
		waitForTheWebElementToBeVisible(successSubscribe);
		return successSubscribe.getText();
	}
	

	//WebElement category =	driver.findElement(By.xpath("//div[@class='left-sidebar']//h2[1]"));
	
	@FindBy(xpath="//div[@class='left-sidebar']//h2[1]")
	WebElement category;
	
	public boolean isCategoryLabelDispalyed()
	{
		return category.isDisplayed();
	}
	
	//WebElement womenProducts =	driver.findElement(By.xpath("//a[@href='#Women']"));
	//js.executeScript("arguments[0].scrollIntoView(true);",womenProducts );
	//js.executeScript("arguments[0].click();", womenProducts);
	
	//driver.findElement(By.xpath("//a[@href='/category_products/1']")).click();
	
	@FindBy(xpath="//a[@href='/category_products/1']")
	WebElement womenDress;
	
	@FindBy(xpath="//a[@href='#Women']")
	WebElement womenProducts;
	
	public void goToWomenDress()
	{
		scrollIntoView(womenProducts);
		elementToBeClickable(womenProducts);
		womenDress.click();
	}
	
	//List<WebElement> displayedProducts =	driver.findElements(By.xpath("//div[@class='single-products']"));
	
	@FindBy(xpath="//div[@class='single-products']")
	List<WebElement> displayedProducts;
	
	public boolean areProductsDisplayed()
	{
		return displayedProducts.isEmpty();
	}
	
	
	//driver.findElement(By.xpath("//a[@href='#Men']")).click();
	
	//driver.findElement(By.xpath("//a[text()='Jeans ']")).click();
	
	@FindBy(xpath="//a[@href='#Men']")
	WebElement menProducts;
	
	@FindBy(xpath="//a[text()='Jeans ']")
	WebElement menJeans;
	
	public void goToMenJeans()
	{
		elementToBeClickable(menProducts);
		elementToBeClickable(menJeans);
	}
	
	//List<WebElement> menProduct =	driver.findElements(By.xpath("//div[@class='single-products']"));
	
	@FindBy(xpath="//div[@class='single-products']")
	List<WebElement> menProduct;
	
	public boolean areMenProductDisplayed()
	{
		return menProduct.isEmpty();
	}
}

