package devenik.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import devenik.pageobjects.CartPage;

public class AbstractComponent {
	
	WebDriver driver;
	int sec = 5;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orders;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppearWE(WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(we));
	}
	
	public void waitForElementToDisappear(WebElement wb) throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(sec));
		//wait.until(ExpectedConditions.invisibilityOf(wb));
		Thread.sleep(5000);
	}
	
	public CartPage goToCartPage() {
		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrdersPage() {
		orders.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}

	
}
