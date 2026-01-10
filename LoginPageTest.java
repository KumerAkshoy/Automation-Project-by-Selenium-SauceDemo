/*
 * Login Page Automation Testing:
 * Test with empty,wrong and valid credentials.
 * Test for different user like: locked_out_user, problem_user, error_user.
 */
package SauceDemoEndToEnd;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest {
	//driver is for reference variable for WebDriver
	WebDriver driver;
	@BeforeMethod
	public void SetBrowser() {
		//Firefox driver, window maximization, implicit wait, accessing the website 
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.saucedemo.com/");
	}
	//login page test with empty input fields.
	//Error message should appear for input fields, login process should not execute.
	@Test(priority = 1)
	public void login_Blank_Inputs() {
		driver.findElement(By.id("user-name")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");

		driver.findElement(By.id("login-button")).click();

		String actuaErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]"))
				.getText();
		String ExpectedErrorMessage = "Epic sadface: Username is required";
		
		//Assertion by Error message
		Assert.assertEquals(actuaErrorMessage, ExpectedErrorMessage);
		System.out.println("Error message for login with blank inputs found OK");

	}
	// Login page test with blank username and valid password
	//Error message should appear for Email Field, login process should not execute.
	@Test(priority=2)
	public void login_Blank_Username() {
		
		driver.findElement(By.id("user-name")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		String actuaErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"))
				.getText();
		String ExpectedErrorMessage = "Epic sadface: Username is required";
		
		//Assertion of error messge validation for email field.
		Assert.assertEquals(actuaErrorMessage, ExpectedErrorMessage);
		System.out.println("Error message for login with blank username found OK");
	}

	// Login page test with blank password filed and valid email.
	// Error message should appear for Email Field, login process should not execute.
	@Test(priority=3)
	public void login_Blank_Password() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("");

		driver.findElement(By.id("login-button")).click();

		String actuaErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"))
				.getText();
		String ExpectedErrorMessage = "Epic sadface: Password is required";
		
		//Assertion for blank password field with error message.
		Assert.assertEquals(actuaErrorMessage, ExpectedErrorMessage);
		System.out.println("Error message for login with blank password found OK");
	}
	// Login page test with invalid credentials
	//Error message should appear for invalid credentials, login process should not execute.
	@Test(priority=4)
	public void login_invalid_inputs() {
		driver.findElement(By.id("user-name")).sendKeys("standard");
		driver.findElement(By.id("password")).sendKeys("secret");

		driver.findElement(By.id("login-button")).click();

		String actuaErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"))
				.getText();
		String ExpectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
		
		//Assertion for invalid input fields with error message
		Assert.assertEquals(actuaErrorMessage, ExpectedErrorMessage);
		System.out.println("Error message for login with wrong password found OK");
	}
	// Login page test for locked out user
	//Error message should appear and login process should not execute.
	@Test(priority=5)
	public void login_locked_out_user() {
		driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		String actuaErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"))
				.getText();
		String ExpectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
		
		//Validation for locked out user with error message. 
		Assert.assertEquals(actuaErrorMessage, ExpectedErrorMessage);
		System.out.println("Error message for login with locked_out_user found OK");
	}
	// Login page test with blank inputs and valid password
	//Error message should appear for Email Field, login process should not execute.
	@Test(priority=6)
	public void login_problem_user() {
		//fill data for username and password and click login 
		driver.findElement(By.id("user-name")).sendKeys("problem_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();
		// click to "add to cart" button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/button")).click();
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/button")).click();
		//select the itmes by css selector
		List<WebElement> buttonitems = driver.findElements(By.cssSelector(".btn"));

		List<String> button_actual_names = new ArrayList<>();

		for (WebElement price : buttonitems) {
			button_actual_names.add(price.getText());
		}
		String ActualButtonData = button_actual_names.get(0);
		String ExpectedButtonData = "Remove";
		
		//Assertion for login Problem User
		Assert.assertEquals(ActualButtonData, ExpectedButtonData);
		System.out.println("Error found for login with problem user as expected");
	}
	//this method for error user login.
	//expected to checkout will complete without providing data for the must 
	@Test(priority=7)
	public void login_error_user() {
		driver.findElement(By.id("user-name")).sendKeys("error_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();
		// click to add to cart button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/button")).click();
		
		//click for the cart icon
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();
		
		//click for the checkout button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/button[2]")).click();
		
		//data for the first name and postal code
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/form/div[1]/div[1]/input")).sendKeys("abc");
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/form/div[1]/div[3]/input")).sendKeys("3011");
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/form/div[2]/input")).click();
		
		//getting the current url as actual error
		String actual_url=driver.getCurrentUrl();
		String expected_url="https://www.saucedemo.com/checkout-step-two.html";
		
		//Assertion 
		Assert.assertEquals(actual_url, expected_url);
		System.out.println("Assertion for Error user login completed");
	}
	// login page test with valid credentials
	//Expected to no error occur and successful user login.
	@Test(priority=8)
	public void login_valid_inputs() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//Get current url as actual url and set expected url.
		String Actual_Product_URL=driver.getCurrentUrl();
		String Expected_Product_URL="https://www.saucedemo.com/inventory.html";
		
		//Assertion for login with valid credentials
		Assert.assertEquals(Actual_Product_URL, Expected_Product_URL);
		System.out.println("Assertion for product page after successful login found as expected");
	}
	@AfterMethod
	public void closeBrowser() {
		driver.manage().window().minimize();
		driver.quit();
	}
}
