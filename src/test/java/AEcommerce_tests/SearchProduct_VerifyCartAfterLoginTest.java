package AEcommerce_tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AEcommerce.TestsComponents.BaseTest;
import AEcommerce_pageobjects.CartPage;
import AEcommerce_pageobjects.LoginPage;
import AEcommerce_pageobjects.ProductPage;

public class SearchProduct_VerifyCartAfterLoginTest extends BaseTest {
	
	
	@Test(dataProvider="getData")
	public void searchProduct(HashMap<String,Object> input) 
	{
		
		ProductPage productPage = landingPage.productsTab();
		Assert.assertEquals(productPage.getProdPageTitle(), "Automation Exercise - All Products");
		productPage.search((String)input.get("searchProduct"));
		// Verify all the Products related to search are visible
		Boolean matchProduct =	productPage.areSearchProductsVisible((String)input.get("searchProduct"));
		Assert.assertTrue(matchProduct);
		// Add searched Products to Cart
		productPage.addSearchProductsToCart((String)input.get("searchProduct"));
		CartPage cartPage = productPage.clickViewCartBtn();
		// Verify added products are visible in the Cart
		cartPage.verifyProductsIntoCart((String)input.get("searchProduct"));
		LoginPage loginPage = cartPage.signUpLoginTab();
		loginPage.loginApplication((String)input.get("email"), (String)input.get("password"));
		landingPage.clickViewCart();
		//Verify those searched Products are visible in the cart after login
		cartPage.verifyProductsIntoCart((String)input.get("searchProduct"));
	}
	
	
	@Test(dataProvider="getData")
	public void removeProduct(HashMap<String,Object> input)
	{
		
		landingPage.addProductsToCart((List<String>)input.get("targetProducts"));
		CartPage cartPage = landingPage.clickViewCart();
		//Verify we are on cart page
		Assert.assertEquals(cartPage.cartPageVerify(), "Automation Exercise - Checkout");
		cartPage.deleteCartRows();
		Assert.assertEquals(cartPage.getTextInfo(), "Cart is empty!");
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\SearchProduct_RemoveProduct.json");
		return new Object[][] {{data.get(0)}};
	}
	
	//List<String> targetProducts = Arrays.asList("Sleeveless Unicorn Patch Gown - Pink" , "Blue Cotton Indie Mickey Dress", "Premium Polo T-Shirts");
	
	//String searchProduct = "saree";
}


