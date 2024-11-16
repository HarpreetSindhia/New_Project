package AEcommerce_tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AEcommerce.TestsComponents.BaseTest;
import AEcommerce_pageobjects.CartPage;
import AEcommerce_pageobjects.ProductDetailsPage;
import AEcommerce_pageobjects.ProductPage;

public class Product_ProductsDetailPage_ProductsIntoCart_UpdateCartQuantityTest extends BaseTest {

	@Test
	public void verifyProductDetail()
	{
		
		ProductPage productPage = landingPage.productsTab();
		ProductDetailsPage productDetailsPage = productPage.clickViewProduct();
		Assert.assertTrue(productDetailsPage.getProductDetails().isDisplayed());
		Assert.assertTrue(productDetailsPage.getProdCategory().isDisplayed());
		Assert.assertTrue(productDetailsPage.getProdPrice().isDisplayed());
		Assert.assertTrue(productDetailsPage.getprodAvailability().isDisplayed());
		Assert.assertTrue(productDetailsPage.prodCondition().isDisplayed());		
	}

	
	@Test(dataProvider="getData")
	public void productCart(HashMap<String,Object> input) throws InterruptedException
	{
		
		ProductPage productPage = landingPage.productsTab();
		productPage.addProductsIntoCart((List<String>) input.get("targetProducts"));
		CartPage cartPage = productPage.clickViewCartBtn();
		//Verify if both the products are added to the Cart.
		List<WebElement> cartRows =	driver.findElements(By.xpath("//tbody//tr"));
		cartPage.getCartRows();
		//We will make use of nested For Loop 
		for(String targetProduct : (List<String>)input.get("targetProducts"))
		{
			boolean matchFound = false;
			
			for(WebElement row : cartRows)
			{
				String productName = cartPage.getProductName(row);
				
				if(productName.equalsIgnoreCase(targetProduct))
				{
					matchFound = true;
					
					Double productPrice = 	cartPage.getProductPrice(row);
					
					Integer productQuantity = cartPage.getProductQuantity(row);
					
					Double displayCartPrice = cartPage.getDisplayPrice(row);
				
					Assert.assertEquals(productPrice * productQuantity, displayCartPrice, 0.01);
					
					System.out.println("Product : " +productName+ ", Price : " +productPrice+ ", Quantity : " +productQuantity+ ", DispalyPrice :" +displayCartPrice+ "");
				}
			}
		}		
	}
		
		@Test
		public void updateQuantity()
		{
			
			ProductDetailsPage productDetailsPage = landingPage.clickViewProduct();
			//Verify Product Detail is opened
			Assert.assertEquals(productDetailsPage.getProdPageTitle(), "Automation Exercise - Product Details");
			Assert.assertTrue(productDetailsPage.getProductDetails().isDisplayed());
			Assert.assertTrue(productDetailsPage.getProdCategory().isDisplayed());
			Assert.assertTrue(productDetailsPage.getProdPrice().isDisplayed());
			Assert.assertTrue(productDetailsPage.getprodAvailability().isDisplayed());
			Assert.assertTrue(productDetailsPage.prodCondition().isDisplayed());
			Assert.assertTrue(productDetailsPage.prodBrand().isDisplayed());
			//Update the quantity
			productDetailsPage.updateQuantity();
			productDetailsPage.clickAddToCart();
			productDetailsPage.continueShopping();
			productDetailsPage.clickViewCartBtn();
			CartPage cartPage = new CartPage(driver);
			Assert.assertEquals(cartPage.getQuantity(), "4");			
	}
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\ProductIntoCart.json");
			return new Object[][] {{data.get(0)}};
		}
		
		
		//List<String> targetProducts =	Arrays.asList("Stylish Dress" , "Long Maxi Tulle Fancy Dress Up Outfits -Pink");
}
