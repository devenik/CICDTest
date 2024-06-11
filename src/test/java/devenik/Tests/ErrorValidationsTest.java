package devenik.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import devenik.TestComponents.BaseTest;
import devenik.TestComponents.Retry;
import devenik.pageobjects.CartPage;
import devenik.pageobjects.ProductCatalog;


public class ErrorValidationsTest extends BaseTest{

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		//1. Log in
		lp.loginAppBadPass();
		
		//2.Validate error
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test(dataProvider="getData")
	public void productErrorValidation(HashMap<String,String> input) throws IOException, InterruptedException {
		
		//Variable definition
		String productName = "ZARA COAT 3";		

		//1. Log in
		ProductCatalog pc = lp.loginApp(input.get("email"),input.get("password"));
		
		//2. Adding products
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCartPage();
		
		//4.Validating the correct product was added and continuing to checkout
		Boolean match = cp.findProductInCart(productName);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/devenik/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
