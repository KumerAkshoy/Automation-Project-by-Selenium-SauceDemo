package SauceDemoEndToEnd;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownAssertion {
	WebDriver driver;

	@Test(priority = 1)
	public void BrowserSetup() {
		driver = new FirefoxDriver();
		// maximize the browser window
		driver.manage().window().maximize();
		// Implicit wait strategy used for whole program
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// visit the swag labs website
		driver.get("https://www.saucedemo.com/");
	}

	@Test(priority = 2)
	public void login() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		System.out.println("Login Successfully");
		System.out.println();
	}

	@Test(priority = 3)
	public void sortingItemsHightoLow() throws InterruptedException {
		WebElement dropdownIcon = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select"));

		Select dropdownItems = new Select(dropdownIcon);

		System.out.println("Dropdown:Price High to Low");
		// Item access by Index and value
		// dropdownItems.selectByIndex(3);
		dropdownItems.selectByValue("hilo");
		// create a list for pricelist by class name
		List<WebElement> pricelistH_L = driver.findElements(By.className("inventory_item_price"));
		// Declaring Empty string array list
		List<String> ActualPriceListH_L = new ArrayList<>();
		// Text getting by For loop
		for (WebElement price : pricelistH_L) {
			ActualPriceListH_L.add(price.getText());
		}
		// Actual Price for High to Low
		String ActualPriceH_L = ActualPriceListH_L.get(0);
		// Expected Price for High to Low
		String ExpectedPriceH_L = "$49.99";
		// Assertion for high to low
		Assert.assertEquals(ActualPriceH_L, ExpectedPriceH_L);
		System.out.println("Sorting By Price High to Low is found OK");

		Thread.sleep(5000);
	}

	@Test(priority = 4)
	public void sortingItemsLowtoHigh() throws InterruptedException {
		WebElement dropdownIcon = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select"));
		Select dropdownItems = new Select(dropdownIcon);
		// sorting by price low to high portion start form here
		System.out.println("Dropdown:Price Low to High");
		// Item access by Index and value
		// dropdownItems.selectByIndex(2);
		dropdownItems.selectByValue("lohi");
		// create a list for pricelist by class name
		List<WebElement> pricelistL_H = driver.findElements(By.className("inventory_item_price"));
		// Declaring Empty string array list
		List<String> ActualPriceListL_H = new ArrayList<>();
		// Text getting by For loop
		for (WebElement price : pricelistL_H) {
			ActualPriceListL_H.add(price.getText());
		}
		// Actual Price for High to Low
		String ActualPriceL_H = ActualPriceListL_H.get(0);
		// Expected Price for High to Low
		String ExpectedPriceL_H = "$7.99";
		// Assertion for high to low
		Assert.assertEquals(ActualPriceL_H, ExpectedPriceL_H);
		System.out.println("Sorting By Price Low to High is found OK");
		
		Thread.sleep(3000);
	}
	
	@Test(priority=5)
	public void sortItemsZtoA() throws InterruptedException {
		WebElement dropdownIcon = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select"));
		Select dropdownItems = new Select(dropdownIcon);
		// sorting by Name Z to A portion start form here
		System.out.println("Dropdown:Name Z to A");
		// Item access by Index and value
		// dropdownItems.selectByIndex(1);
		dropdownItems.selectByValue("za");
		// create a list for pricelist by class name
		List<WebElement> itemListZ_A = driver.findElements(By.className("inventory_item_price"));
		// Declaring Empty string array list
		List<String> ActualpriceListZ_A = new ArrayList<>();
		// Text getting by For loop
		for (WebElement price : itemListZ_A) {
			ActualpriceListZ_A.add(price.getText());
		}
		// Actual Price for Name Z to A
		String ActualPriceZ_A = ActualpriceListZ_A.get(0);
		// Expected Price for Name Z to A
		String ExpectedPriceZ_A = "$15.99";
		// Assertion for Name Z to A
		Assert.assertEquals(ActualPriceZ_A, ExpectedPriceZ_A);
		System.out.println("Sorting By Name Z to A is found OK");
		
		Thread.sleep(3000);
	}
	
	@Test(priority=6)
	public void sortbyNameAtoZ() throws InterruptedException {
		WebElement dropdownIcon = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select"));
		Select dropdownItems = new Select(dropdownIcon);
		// sorting by Name A to Z portion start form here
		System.out.println("Dropdown:Name A to Z");
		// Item access by Index and value
		// dropdownItems.selectByIndex(1);
		dropdownItems.selectByValue("az");
		// create a list for pricelist by class name
		List<WebElement> itemListA_Z = driver.findElements(By.className("inventory_item_price"));
		// Declaring Empty string array list
		List<String> ActualpriceListA_Z = new ArrayList<>();
		// Text getting by For loop
		for (WebElement price : itemListA_Z) {
			ActualpriceListA_Z.add(price.getText());
		}
		// Actual Price for Name A to Z
		String ActualPriceA_Z = ActualpriceListA_Z.get(0);
		// Expected Price for Name A to Z
		String ExpectedPriceA_Z = "$29.99";
		// Assertion for Name A to Z
		Assert.assertEquals(ActualPriceA_Z, ExpectedPriceA_Z);
		System.out.println("Sorting By Name A to Z is found OK");
		
		Thread.sleep(3000);
	}

	@Test(priority = 7)
	public void CloseBrowser() {
		driver.manage().window().minimize();
		driver.quit();
	}
}
