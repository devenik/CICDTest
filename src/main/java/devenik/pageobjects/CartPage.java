package devenik.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devenik.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	private WebDriver driver;
	public CartPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".totalRow button")
	private WebElement Payment;
	
	private By cartSectionBy = By.cssSelector(".cartSection h3");
	
	public List<WebElement> getCartList(){
		List<WebElement> cartList = driver.findElements(cartSectionBy);
		return cartList;
	}
	
	public Boolean findProductInCart(String productName) {
		List<WebElement> cartList = getCartList();
		Boolean match = cartList.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage goToPayment() {
		Payment.click();
		PaymentPage pp = new PaymentPage(driver);
		return pp;
	}
	
}
