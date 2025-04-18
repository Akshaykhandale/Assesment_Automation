package Assesment_Automation_Testing;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CodeToLogin {

	WebDriver driver = new ChromeDriver();
	
	@Test (priority = 1)
	public void launchBrowser() throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		
		driver.get("https://www.saucedemo.com/v1/");   
		driver.manage().window().maximize();      
		Thread.sleep(2000);    
	}
	
	@Test (priority = 2)
	public void credentials() throws InterruptedException
	{
		WebElement username = driver.findElement(By.xpath("//input[@id=\'user-name\']"));  
		username.click();
		username.sendKeys("standard_user");
		Thread.sleep(2000);
		
		WebElement password = driver.findElement(By.xpath("//input[@id=\'password\']"));
		password.click();
		password.sendKeys("secret_sauce");
		Thread.sleep(2000);
	}
	
	@Test (priority = 3)
	public void loginButton() throws InterruptedException
	{
		WebElement login = driver.findElement(By.xpath("//input[@id=\'login-button\']"));
		login.click();
		Thread.sleep(2000);
		
		String actual_string = driver.getTitle();
		String expected_string = "Swag Labs";
		if(actual_string.equals(expected_string))
		{
			System.out.println("Home page is visible");
		}
	}
	
	@Test (priority = 4)
	public void productFilter() throws InterruptedException
	{
		WebElement dropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		dropdown.click();
		
		WebElement filter = driver.findElement(By.xpath("//option[@value='lohi']"));
		filter.click();
		Thread.sleep(2000);
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='inventory_item']"));
		for(int i = 0; i < list.size(); i++)
		{
			String name = list.get(i).getText();
			System.out.println(name);
		}
	}
	
	@Test (priority = 5)
	public void cartOption() throws InterruptedException
	{
		WebElement addToCart = driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory'][1]"));
		addToCart.click();
		Thread.sleep(2000);
		
		WebElement cartIcon = driver.findElement(By.xpath("//a[@class=\"shopping_cart_link fa-layers fa-fw\"]"));
		cartIcon.click();
		Thread.sleep(2000);
		
		WebElement cartProduct = driver.findElement(By.linkText("Sauce Labs Onesie"));
		Assert.assertTrue(cartProduct.isDisplayed(), "Product not found in cart!");
	}
	
	@Test (priority = 6)
	public void checkOutProcess() throws InterruptedException
	{
		WebElement checkOutButton = driver.findElement(By.linkText("CHECKOUT"));
		checkOutButton.click();
		Thread.sleep(2000);
		
		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.click();
		firstName.sendKeys("Akshay");
		Thread.sleep(2000);
		
		WebElement lasttName = driver.findElement(By.id("last-name"));
		lasttName.click();
		lasttName.sendKeys("Khandale");
		Thread.sleep(2000);
		
		WebElement zipCode = driver.findElement(By.id("postal-code"));
		zipCode.click();
		zipCode.sendKeys("411005");
		Thread.sleep(2000);
		
		WebElement continueButton = driver.findElement(By.xpath("//input[@class='btn_primary cart_button']"));
		continueButton.click();
		Thread.sleep(2000);	
	}
	
	@Test (priority = 7)
	public void sucessMessage() throws InterruptedException
	{
		WebElement finishButton = driver.findElement(By.xpath("//a[@class='btn_action cart_button']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");
		finishButton.click();
		Thread.sleep(2000);
		
		WebElement message = driver.findElement(By.id("checkout_complete_container"));
		Assert.assertTrue(message.isDisplayed(), "Sucess message is not displayed!");
		Thread.sleep(2000);
		
		driver.quit();
	}
	
}
