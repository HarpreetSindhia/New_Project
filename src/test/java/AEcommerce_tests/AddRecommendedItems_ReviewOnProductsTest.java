package AEcommerce_tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AEcommerce.TestsComponents.BaseTest;
import AEcommerce_pageobjects.ProductPage;

public class AddRecommendedItems_ReviewOnProductsTest extends BaseTest {

	@Test(dataProvider="getData")
	public void addRecommendedItems(HashMap<String,Object> input)
	{
		landingPage.scrollWindow(7000);
		Assert.assertTrue(landingPage.addRecommItems().equalsIgnoreCase("recommended items"));
		landingPage.addRecommendItems((String)input.get("targetProduct"));
		landingPage.isProductDisplayInCart((String)input.get("targetProduct"));
	}
	
	@Test(dataProvider="getData")
	public void addProductReview(HashMap<String,Object> input)
	{
		
		ProductPage productPage = landingPage.productsTab();
		Assert.assertEquals(productPage.getProdPageTitle(), "Automation Exercise - All Products");
		productPage.clickViewProduct();
		Assert.assertTrue(productPage.isReviewLabelDisplayed());
		productPage.addReviewOnProduct((String)input.get("name"),(String)input.get("email"),(String)input.get("review"));
		Assert.assertEquals(productPage.reviewSubmitted(), "Thank you for your review.");
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\AddRecommendedItem_ReviewProduct.json");
		return new Object[][] {{data.get(0)}};
	}
	
	//String targetProduct = "Winter Top";
}
