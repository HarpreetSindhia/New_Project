package AEcommerce_tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AEcommerce.TestsComponents.BaseTest;
import AEcommerce_pageobjects.ContactUsPage;


public class ContactUsForm_SubscriptionTest extends BaseTest {
	
	
	@Test(dataProvider="getData")
	public void contactUs(HashMap<String,Object> input) throws AWTException, InterruptedException
	{
		
		ContactUsPage contactUsPage = landingPage.contactUs();
		Assert.assertTrue(contactUsPage.contactUsText().equalsIgnoreCase("Get In Touch"));
		contactUsPage.contactDetails((String)input.get("user"),(String)input.get("email"),(String)input.get("subject"),(String)input.get("textArea"),
				(String)input.get("uploadFile"));
		
		//Alert Handling
		
		contactUsPage.acceptAlert();
		Assert.assertEquals(contactUsPage.getSuccessMessage(), "Success! Your details have been submitted successfully.");
		contactUsPage.homeTab();
		Assert.assertEquals(landingPage.verifyHomePageText(), "Full-Fledged practice website for Automation Engineers");
}
	
	@Test(dataProvider="getData")
	public void verifySubscription(HashMap<String,Object> input)
	{
		
		Assert.assertTrue(landingPage.getSubText().equalsIgnoreCase("Subscription"));
		landingPage.subscribe((String)input.get("email"));
		Assert.assertEquals(landingPage.getSubscribeMessage(), "You have been successfully subscribed!");	
	}
	
	@DataProvider()
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\ContactDetails.json");
		return new Object[][] {{data.get(0)}};
	}
}
