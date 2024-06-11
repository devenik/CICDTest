package devenik.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import devenik.TestComponents.BaseTest;
import devenik.abstractcomponents.OrderPage;
import devenik.pageobjects.CartPage;
import devenik.pageobjects.OrderConfirmation;
import devenik.pageobjects.PaymentPage;
import devenik.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	// Variable definition
	String countryCO = "United States";
	String expectedMessage = "THANKYOU FOR THE ORDER.";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		// 1. Log in
		ProductCatalog pc = lp.loginApp(input.get("email"),input.get("password"));

		// 2. Adding products
		pc.addProductToCart(input.get("product"));
		CartPage cp = pc.goToCartPage();

		// 4.Validating the correct product was added and continuing to checkout
		Boolean match = cp.findProductInCart(input.get("product"));
		Assert.assertTrue(match);
		PaymentPage pp = cp.goToPayment();

		// 6.Filling out required info (Country) and placing order
		pp.countrySelector(countryCO);
		OrderConfirmation oc = pp.goToConfirmation();

		// 8.Validating the order placement
		String actualMessage = oc.validateOrder();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
	}

	@Test(dataProvider="getData",dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest (HashMap<String,String> input) throws IOException, InterruptedException{
		
		//1. Log in
		lp.goTo();
		ProductCatalog pc = lp.loginApp(input.get("email"),input.get("password"));
		
		//2. Going to order page to validation
		OrderPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.validateOrderDisplay(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/devenik/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
