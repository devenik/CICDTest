package devenik.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devenik.abstractcomponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	
	private WebDriver driver;
	public PaymentPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".user__address input")
	private WebElement countryField;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']/span")
	private WebElement countryOptions;
	
	@FindBy(css=".btnn")
	private WebElement confirmation;
	
	private By results = By.cssSelector(".ta-results");
	
	
	public void countrySelector(String country) {
		Actions action = new Actions(driver);
		action.click(countryField).sendKeys(country).build().perform();
		waitForElementToAppear(results);
		action.click(countryOptions).build().perform();
	}
	
	public OrderConfirmation goToConfirmation() {
		confirmation.click();
		OrderConfirmation oc = new OrderConfirmation(driver);
		return oc;
		
	}
}
