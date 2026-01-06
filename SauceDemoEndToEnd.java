/*
 In this project I have done full End to End testing including login to logout.
 The Roadmap: Login -> Add Product -> Cart -> Checkout -> Back Home -> Log Out
 */
package SauceDemoEndToEnd;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoEndToEnd {
	WebDriver driver;

	@Test(priority = 1)
	public void BrowserSetUp() {
		// launch Chrome browser
		driver = new FirefoxDriver();
		// maximize the browser window
		driver.manage().window().maximize();
		// Implicit wait strategy used for whole program
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// visit the swag labs website
		driver.get("https://www.saucedemo.com/");
		System.out.println("Website loaded Successfully\n");
	}

	@Test(priority = 2)
	public void Login() {
		// Assertions For login page - Assertion 1
		// get the actual url
		String ActualURL = driver.getCurrentUrl();
		// initialize the expected url
		String ExpectedURL = "https://www.saucedemo.com/";
		// Hard Assertion for Assertion 1
		Assert.assertEquals(ActualURL, ExpectedURL);
		System.out.println("Login page Assertion via URL completed");

		// Assertion 2
		WebElement button = driver.findElement(By.id("login-button"));
		String actualB_title = button.getAccessibleName();
		String expectedButton_title = "Login";
		// Hard assertion for Assertion 2
		Assert.assertEquals(actualB_title, expectedButton_title);
		System.out.println("Login page Assertion via login button completed");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		System.out.println("Login Successfully");
		System.out.println();

	}
	
	@Test(priority=3)
	public void AddProduct() {
		//Assertion for Product page after successful Login 
		//Assertion 1
		String Actual_addproductURL=driver.getCurrentUrl();
		String Expected_addproductURL="https://www.saucedemo.com/inventory.html";
		
		Assert.assertEquals(Actual_addproductURL, Expected_addproductURL);
		System.out.println("Assertion for Add Product via URL successful");
		
		//Assertion 2
		WebElement title=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
		String actualProductPageTitle=title.getText();
		String expectProductPageTitle="Products";
		
		Assert.assertEquals(actualProductPageTitle,expectProductPageTitle);
		System.out.println("Assertion for Add Product via page title successful.");
		
		
		//Add product to the cart
		System.out.println("Add first product to cart");
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		System.out.println("Add 2nd product to cart");
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		// Click on cart icon
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();
		System.out.println("Entered into Your Cart page.\n");
		
		// Assertion for Your Cart
		//Assertion 1 via url
		String ActualCartURL=driver.getCurrentUrl();
		String ExpectedCartURL="https://www.saucedemo.com/cart.html";
		//Hard Assertion
		Assert.assertEquals(ActualCartURL, ExpectedCartURL);
		System.out.println("Assertion for Cart via URL completed");
		
		//Assetion 2 via Page Title
		WebElement cartPageTitle=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
		String ActualCartPageTitle=cartPageTitle.getText();
		String ExpectedCartPageTitle="Your Cart";
		//Hard Assertion
		Assert.assertEquals(ActualCartPageTitle, ExpectedCartPageTitle);
		System.out.println("Assertion via Cart page title Completed.\n");
	}
	
	@Test(priority=4)
	public void Checkout() {
		System.out.println("Move for Checkout.");
		driver.findElement(By.name("checkout")).click();
		
		// Assertion for Checkout Information Page
		//Assertion 1 via URL of info page
		String ActualInfoURL=driver.getCurrentUrl();
		String ExpectedInfoURL="https://www.saucedemo.com/checkout-step-one.html";
		Assert.assertEquals(ActualInfoURL, ExpectedInfoURL);
		System.out.println("Assertion of checkout information page via URL completed.");
		
		//Assertion 2 via 
		WebElement CheckoutInfoPageTitle=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
		String ActualCheckoutInfoPageTitle=CheckoutInfoPageTitle.getText();
		String ExpectedCheckoutInfoPageTitle="Checkout: Your Information";
		Assert.assertEquals(ActualCheckoutInfoPageTitle, ExpectedCheckoutInfoPageTitle);
		System.out.println("Assertion of checkout information page via Page title completed");
		
		//Set the Information for Checkout- FirsName->LastName->PostalCode->Continue Button
		
		driver.findElement(By.id("first-name")).sendKeys("Keramot");
		driver.findElement(By.id("last-name")).sendKeys("Ullah");
		driver.findElement(By.id("postal-code")).sendKeys("6677");
		driver.findElement(By.id("continue")).click();
		
		// Assertion for Checkout Overview page
		//Assertion 1
		String ActualOverviewURL=driver.getCurrentUrl();
		String ExpectedOverviewURL="https://www.saucedemo.com/checkout-step-two.html";
		Assert.assertEquals(ActualOverviewURL, ExpectedOverviewURL);
		System.out.println("Assertion of Checkout Overview page via URL Completed");
		
		//Assertion 2
		WebElement CheckoutOverviewPageTitle=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
		String ActualCheckoutOverviewPageTitle=CheckoutOverviewPageTitle.getText();
		String ExpectedCheckoutOverviewPageTitle="Checkout: Overview";
		Assert.assertEquals(ActualCheckoutOverviewPageTitle, ExpectedCheckoutOverviewPageTitle);
		System.out.println("Assertion of checkout information page via Page title completed\n");
		
		System.out.println("Click Finish Button");
		driver.findElement(By.id("finish")).click();
		
		// Assertion for Chcekout:complete page
		// Assertion 1 
		String ActualCheckCompleteURL=driver.getCurrentUrl();
		String ExpectedCheckCompleteURL="https://www.saucedemo.com/checkout-complete.html";
		Assert.assertEquals(ActualCheckCompleteURL, ExpectedCheckCompleteURL);
		System.out.println("Assertion via URL completed");
		
		//Assertion 2
		WebElement CheckoutCompletePageTitle=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
		String ActualCheckoutCompletePageTitle=CheckoutCompletePageTitle.getText();
		String ExpectedCheckoutCompletePageTitle="Checkout: Complete!";
		Assert.assertEquals(ActualCheckoutCompletePageTitle, ExpectedCheckoutCompletePageTitle);
		System.out.println("Assertion of final page of checkout via Page title completed");
		
		//Assertion 3
		WebElement CheckoutCompleteheader=driver.findElement(By.xpath("/html/body/div/div/div/div[2]/h2"));
		String ActualCheckoutCompleteheader=CheckoutCompleteheader.getText();
		String ExpectedCheckoutCompleteheader="Thank you for your order!";
		Assert.assertEquals(ActualCheckoutCompleteheader, ExpectedCheckoutCompleteheader);
		System.out.println("Assertion of complete checkout via header completed");

	}
	
	@Test(priority=5)
	public void Logout() {
		driver.findElement(By.id("back-to-products")).click();
		// Assertion after back to product page
		//Assertion 1
		String ActualInventoryURL=driver.getCurrentUrl();
		String ExpectedInventoryURl="https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(ActualInventoryURL, ExpectedInventoryURl);
		System.out.println("Assertion of inventory via URL completed");
		
		//Assertion 2
		WebElement inventoryTitle=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
		String ActualinventoryTitle=inventoryTitle.getText();
		String ExpectedinventoryTitle="Products";
		Assert.assertEquals(ActualinventoryTitle, ExpectedinventoryTitle);
		System.out.println("Assertion of inventory page via page title completed");
		
		//Click on the side bar menu 
		driver.findElement(By.id("react-burger-menu-btn")).click();
		//	Assertion for side menu bar options.
		//Assertion for "All Items" menu available
		WebElement navMenuItem1=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[2]/div[1]/nav/a[1]"));
		String ActualnavMenuItem1Name=navMenuItem1.getText();
		String ExpectednavMenuItem1Name="All Items";
		Assert.assertEquals(ActualnavMenuItem1Name, ExpectednavMenuItem1Name);
		System.out.println("All Items option available for user");
		
		//Assertion for Logout option available 
		WebElement navMenuItem2=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[2]/div[1]/nav/a[3]"));
		String ActualnavMenuItem2Name=navMenuItem2.getText();
		String ExpectednavMenuItem2Name="Logout";
		Assert.assertEquals(ActualnavMenuItem2Name, ExpectednavMenuItem2Name);
		System.out.println("All Items option available for user");
		
		// Click on logout link for Logging out from the system
		driver.findElement(By.id("logout_sidebar_link")).click();
		// Assertion after logout 
		//Assertion 1
		String ActualLoginURL = driver.getCurrentUrl();
		String ExpectedloginURL = "https://www.saucedemo.com/";
		Assert.assertEquals(ActualLoginURL,ExpectedloginURL);
		System.out.println("Asertion after logout completed via url");
		
		//Assertion 2
		WebElement buttontitle = driver.findElement(By.id("login-button"));
		String actualButtonTitle = buttontitle.getAccessibleName();
		String expectedButtonTitle = "Login";
		Assert.assertEquals(actualButtonTitle, expectedButtonTitle);
		System.out.println("Asertion after logout completed via login button accessible text");
	}

	@Test(priority = 6)
	public void ClosetheBrowser() {
		driver.manage().window().minimize();
		driver.quit();
	}
}
