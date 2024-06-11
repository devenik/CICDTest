package devenik.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import devenik.TestComponents.BaseTest;
import devenik.pageobjects.CartPage;
import devenik.pageobjects.LandingPage;
import devenik.pageobjects.OrderConfirmation;
import devenik.pageobjects.PaymentPage;
import devenik.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDI extends BaseTest {
	
	public LandingPage lp;
	public ProductCatalog pc;
	public OrderConfirmation oc;

	@Given("I landed on E-commerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
	lp = launchDriver();
		
	}
	
	@Given ("^logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String user, String pass) {
		pc = lp.loginApp(user,pass);		
	}
	
	@When("^I add the product (.+) from catalogue to the cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		pc.addProductToCart(productName);
		
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.findProductInCart(productName);
		Assert.assertTrue(match);
		PaymentPage pp = cp.goToPayment();
		pp.countrySelector("United States");
		oc = pp.goToConfirmation();
	}
	
	@Then("I verify {string} message is displayed.")
	public void message_displayed_confirmation_page(String string) {
		String actualMessage = oc.validateOrder();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@When ("logged in with a bad username and password")
	public void logged_in_bad_user_pass () {
		lp.loginAppBadPass();
	}
	
	@Then ("{string} message is displayed")
	public void bad_message_is_displayed(String string) {
		Assert.assertEquals(string, lp.getErrorMessage());
		driver.quit();
	}
	
}
