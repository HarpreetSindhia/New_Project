package AEcommerce_tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AEcommerce.TestsComponents.BaseTest;
import AEcommerce_pageobjects.CartPage;
import AEcommerce_pageobjects.LoginPage;
import AEcommerce_pageobjects.PaymentPage;

public class LoginEmail_ProductCheckoutTest extends BaseTest{


	@Test(dataProvider="getData")
	public void loginCorrect(HashMap<String,Object> input) throws IOException 
	{
		Assert.assertEquals(landingPage.homePageVerify(), "Automation Exercise");
		LoginPage loginPage = landingPage.signUpLoginTab();
		Assert.assertEquals(loginPage.loginToAccount(), "Login to your account");
		loginPage.loginApplication((String)input.get("email"), (String)input.get("password"));
		Assert.assertEquals(loginPage.userLogIn(), (String)input.get("userName"));
	}

	@Test(dataProvider="getData")
	public void loginWhileCheckout(HashMap<String,Object> input)
	{
		// Verify that you are on HomePage
		Assert.assertEquals(landingPage.homePageVerify(), "Automation Exercise");
		// Add Products to Cart
		landingPage.addProductsToCart((List<String>) input.get("targetProducts"));
		CartPage cartPage = landingPage.clickViewCart();
		// Verify you are on the Cart Page
		Assert.assertEquals(cartPage.cartPageVerify(), "Automation Exercise - Checkout");
		LoginPage loginPage = cartPage.goToCheckoutBtn();
		loginPage.loginApplication((String)input.get("email"),(String)input.get("password"));
		// Verify logged in as username at top
		Assert.assertEquals(loginPage.userLogIn(), (String)input.get("userName"));
		landingPage.goToCartTab();
		cartPage.proceedToCheckout();
		// Verify Address Details
		Assert.assertTrue(cartPage.getDeliveryAddres().equalsIgnoreCase((String) input.get("expectedDeliveryAddress")));
		// Verify Billing Address
		Assert.assertTrue(cartPage.getBillingAddress().equalsIgnoreCase((String)input.get("expectedbillingAddress")));
		//Assert.assertTrue(expectedbillingAddress.equalsIgnoreCase(billingAddress));
		cartPage.scrollWindow(700);
		// Review Your Order
		List<WebElement> cartRows =	cartPage.getCartRows();
		Double calculatedTotal = 0.0;
		for(String targetProduct : (List<String>)input.get("targetProducts"))
		{
			boolean matchFound = false;
			
			for(WebElement row : cartRows)
			{
				String productName =	cartPage.getProductName(row);
				
				if(productName.equalsIgnoreCase(targetProduct))
				{
					matchFound = true;
				
				Double price =	cartPage.getProductPrice(row);
				Integer quantity =	cartPage.getProductQuantity(row);
				Double displayPrice =	cartPage.getDisplayPrice(row);

				Assert.assertEquals(price * quantity  , displayPrice , 0.01);
				
				calculatedTotal = calculatedTotal + displayPrice;
				
				break;//Exit the Inner loop one the match is found.
				
				}	
			}
		}
		
		Double parsedTotalAmount = cartPage.getTotalAmount();
		Assert.assertEquals(parsedTotalAmount, calculatedTotal, 0.01);
		cartPage.addComments("Thank you for the products");
		PaymentPage paymentPage = cartPage.placeProductOrder();
		paymentPage.cardDetails((String)input.get("cardHolderName"),(String)input.get("cardNum"),(String)input.get("cardCVC"),(String)input.get("expMonth"),(String)input.get("expYear"));
		paymentPage.submitOrder();
		Assert.assertEquals(paymentPage.getSuccessMessage(), "Congratulations! Your order has been confirmed!");
		paymentPage.invoiceContinue();
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\LoginWhileCheckout.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	/*@DataProvider
	public Object[][] getData1() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\LoginCorrectEmail.json");
		return new Object[][] {{data.get(0)}};
	}
	/*List<String> targetProducts = Arrays.asList("Pure Cotton Neon Green Tshirt", "Regular Fit Straight Jeans",
	"Green Side Placket Detail T-Shirt");

	String expectedAddress = "Mrs. Amandeep Sindhia\nAmazon\n1212 Silicon Valley\nNew Vales Toronto 56002\nCanada\n9915942850";

	String expectedDeliveryAddress = "Your delivery address\n" + expectedAddress;

	String expectedbillingAddress = "Your billing address\n" + expectedAddress;*/

}







