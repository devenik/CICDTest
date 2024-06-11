package devenik.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devenik.abstractcomponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	private WebDriver driver;
	
	public ProductCatalog (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory
	@FindBy(css=".mb-3")
	private List<WebElement> productList;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	private By productsBy = By.cssSelector(".mb-3");
	private By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	private By toastContainer = By.cssSelector(".toast-container");
	private By waitAnimation = By.cssSelector(".ngx-spinner-overlay");
	private By submit = By.cssSelector("[routerlink*='cart']");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return productList;
		
	}
	
	public WebElement getProductByName(String productName) {
		
		//Selecting the product Web element desired
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement selectedProduct = getProductByName(productName);
		selectedProduct.findElement(addToCartBy).click(); //Adding to cart
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(spinner);
	}

}
