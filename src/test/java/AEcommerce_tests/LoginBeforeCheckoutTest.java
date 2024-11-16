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
import AEcommerce_pageobjects.ProductPage;

public class LoginBeforeCheckoutTest extends BaseTest {
	
	
	@Test(dataProvider="getData",groups= {"PurchaseOrder"})
	public void productCart(HashMap<String,Object> input) throws InterruptedException {
		
		LoginPage loginPage = landingPage.signUpLoginTab();
		loginPage.loginApplication("navsindhia@gmail.com", "sindhiaNav55");
		ProductPage productPage = landingPage.productsTab();
		productPage.addProductsIntoCart((List<String>)input.get("targetProducts"));
		CartPage cartPage = productPage.clickViewCartBtn();
		cartPage.proceedToCheckout();
		cartPage.scrollWindow(600);
		
		List<WebElement> cartRows = cartPage.getCartRows();
		Double calculatedTotal = 0.0;
		for (String targetProduct : (List<String>)input.get("targetProducts")) {
			boolean matchFound = false;
			for (WebElement row : cartRows) {
				String productName =	cartPage.getProductName(row);
				if (productName.equalsIgnoreCase(targetProduct)) {
					matchFound = true;
					// Fetch price, quantity, and total
					Double price = 	cartPage.getProductPrice(row);
					Integer quantity =	cartPage.getProductQuantity(row);
					Double displayCartPrice =	cartPage.getDisplayPrice(row);
					// Assert price calculation
					Assert.assertEquals(price * quantity, displayCartPrice, 0.01);
					System.out.println("Product: " + productName + ", Price: " + price + ", Quantity: " + quantity
							+ ", Displayed Total: " + displayCartPrice);
					calculatedTotal = calculatedTotal + displayCartPrice;
					break; // Exit inner loop once match is found and processed
				}
			}
		}
		// After product verification, check total
		
		Double totalCartPrice =	cartPage.getTotalAmount();
		Assert.assertEquals(totalCartPrice, calculatedTotal, 0.01);
		PaymentPage paymentPage = cartPage.placeProductOrder();
		paymentPage.cardDetails((String)input.get("cardHolderName"),(String)input.get("cardNum"),(String)input.get("cardCVC"),(String)input.get("expMonth"),(String)input.get("expYear"));
		paymentPage.submitOrder();
		String orderPlaced =	paymentPage.getSuccessMessage();
		Assert.assertEquals(orderPlaced, "Congratulations! Your order has been confirmed!");
		paymentPage.invoiceContinue();
		
	}
	
	@DataProvider()
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\LoginBeforeCheckout.json");
		return new Object[][] {{data.get(0)}};
	}

	//List<String> targetProducts = Arrays.asList("Premium Polo T-Shirts", "GRAPHIC DESIGN MEN T SHIRT - BLUE");
}
