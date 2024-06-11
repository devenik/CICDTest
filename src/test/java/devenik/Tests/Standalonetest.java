package devenik.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalonetest {

	public static void main(String[] args) throws InterruptedException {
		
		//Variable definition
		String productName = "ZARA COAT 3";
		String countryCO = "United States";
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		
		//Downloading and creating a WebDriver instance
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//driver configs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		//Launching driver
		driver.get("https://rahulshettyacademy.com/client");
		
		//1. Log in
		driver.findElement(By.id("userEmail")).sendKeys("devenik@email.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password123");
		driver.findElement(By.id("login")).click();
		
		//2. Selecting products
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3")); //Getting the product list
		//Selecting the product Web element desired
		WebElement prod = productList.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //Adding to cart
		
		//3.Validating the product was added
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//4.Validating the correct product was added
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//5.Continuing to checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//6.Filling out required info (Country)
		Actions action = new Actions(driver);
		action.click(driver.findElement(By.cssSelector(".user__address input"))).sendKeys(countryCO).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		action.click(driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']/span"))).build().perform();
		
		//7.Placing order
		driver.findElement(By.cssSelector(".btnn")).click();
		
		//8.Validating the order placement
		String finalMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(finalMessage.equalsIgnoreCase(expectedMessage)); 	
		
		
		//Quitting the driver
		Thread.sleep(2000);
		driver.quit();
	}

}
