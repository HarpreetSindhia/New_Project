package AEcommerce_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import AEcommerce.TestsComponents.BaseTest;

public class VerifyScrollFunctionTest extends BaseTest{

	@Test
	public void scrollUpWithArrow()
	{

		landingPage.scrollBottomOfPage();
		Assert.assertTrue(landingPage.getSubText().equalsIgnoreCase("Subscription"));
		landingPage.scrollUp();
		Assert.assertEquals(landingPage.verifyHomePageText(), "Full-Fledged practice website for Automation Engineers");
}
	
	@Test
	public void scrollUpWithoutTheArrow()
	{
		
		landingPage.scrollBottomOfPage();
		Assert.assertTrue(landingPage.getSubText().equalsIgnoreCase("Subscription"));
		landingPage.scrollTopOfPage();
		Assert.assertEquals(landingPage.verifyHomePageText(), "Full-Fledged practice website for Automation Engineers");
}
}
