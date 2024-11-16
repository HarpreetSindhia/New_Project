package AEcommerce_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import AEcommerce.TestsComponents.BaseTest;
import AEcommerce_pageobjects.ProductPage;

public class ViewCategory_BrandProductsTest extends BaseTest {
	
	@Test
	public void viewCategoryProducts()
	{
		
		Assert.assertTrue(landingPage.isCategoryLabelDispalyed());
		landingPage.goToWomenDress();
		Assert.assertFalse(landingPage.areProductsDisplayed());
		landingPage.goToMenJeans();
		Assert.assertFalse(landingPage.areMenProductDisplayed());
		
	}
	
	@Test
	public void viewBrandProducts()
	{
		
		ProductPage productPage = landingPage.productsTab();
		Assert.assertTrue(productPage.areBrandsDispalyed());
		productPage.clickonBrandName();
		Assert.assertFalse(productPage.areProductsDisplayed());
		productPage.clickBrandName();
		Assert.assertTrue(productPage.getProductLabel().equalsIgnoreCase("Brand - Mast & Harbour Products"));
		Assert.assertFalse(productPage.areBrandProductDisplayed());	
	}
}


