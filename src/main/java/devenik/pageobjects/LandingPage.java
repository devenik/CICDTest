package devenik.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devenik.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	private WebDriver driver;
	
	public LandingPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Variable definition
	private String iun = "dev@email.com";
	private String ipw = "Pass123";
	
	//Page Factory
	@FindBy(id="userEmail")
	private WebElement user;
	
	@FindBy(id="userPassword")
	private WebElement password;
	
	@FindBy(id="login")
	private WebElement submit;
	
	@FindBy(css="[class*='flyInOut'")
	private WebElement loginError;
	
	
	
	public ProductCatalog loginApp(String un, String pw) {
		
		user.sendKeys(un);
		password.sendKeys(pw);
		submit.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForElementToAppearWE(loginError);
		return loginError.getText();
		 
	}
	
public ProductCatalog loginAppBadPass() {
		
		user.sendKeys(iun);
		password.sendKeys(ipw);
		submit.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
	}
}
