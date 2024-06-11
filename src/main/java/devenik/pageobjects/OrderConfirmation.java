package devenik.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import devenik.abstractcomponents.AbstractComponent;

public class OrderConfirmation extends AbstractComponent{
		
	private WebDriver driver;
		public OrderConfirmation(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(css=".hero-primary")
		private WebElement confirmationMessage;
		
		public String validateOrder () {
			String actualMessage = confirmationMessage.getText();
			return actualMessage;
		} 	

}
