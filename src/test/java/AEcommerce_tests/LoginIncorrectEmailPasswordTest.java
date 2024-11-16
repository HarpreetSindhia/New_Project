package AEcommerce_tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AEcommerce.TestsComponents.BaseTest;
import AEcommerce.TestsComponents.Retry;
import AEcommerce_pageobjects.LoginPage;

public class LoginIncorrectEmailPasswordTest extends BaseTest {

	@Test(dataProvider="getData",groups= {"Error Handling"},retryAnalyzer=Retry.class)
	public void loginIncorrect(HashMap<String,Object> input)
	{	
		
		LoginPage loginPage = landingPage.signUpLoginTab();
		loginPage.loginToAccount();
		Assert.assertEquals(loginPage.loginToAccount(), "Login to your account");
		loginPage.loginApplication((String)input.get("email"),(String)input.get("password"));
		Assert.assertEquals(loginPage.getErrorMessage(), "Your email or password is incorrect!");
		
	}
	
	@Test(dataProvider="getData")
	public void registerExistingEmail(HashMap<String,Object> input)
	{
		LoginPage loginPage = landingPage.signUpLoginTab();
		loginPage.registerUser((String)input.get("uName"),(String)input.get("uEmail"));
		Assert.assertEquals(loginPage.emailExist(), "Email Address already exist!");
		
	}
	
	@DataProvider()
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,Object>> data =	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AEcommerce\\data\\ErrorHandling.json");
		return new Object[][] {{data.get(0)}};
	}
}
