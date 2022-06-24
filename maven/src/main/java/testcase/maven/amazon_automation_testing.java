

package testcase.maven;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
public class amazon_automation_testing {
	static WebDriver driver;
	static Actions act;
	static String pathToChromeDriverExe;
	static String email;
	static String email_password;
	static String details_name;
	static String details_phone;
	static String details_pin;
	static String details_addressLine1;
	static String details_addressLine2;
	static String details_city;
	
	@BeforeMethod
	public static void launch(){
		System.out.println("Before starting the test,Make sure you have average internet speed to test.");
		inputDetails();
//		System.out.println("Enter path to chromedriver.exe file:");
//	    pathToChromeDriverExe = sc.nextLine();
		System.setProperty("webdriver.chrome.driver",pathToChromeDriverExe);
	    driver = new ChromeDriver();
	    driver.get("https://www.amazon.in/");
	    act = new Actions(driver);
	    System.out.println("Test Started!!");
	    }
	
	public static void inputDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Give path to chrome driver dot exe file:");
		pathToChromeDriverExe = sc.nextLine();
		System.out.println("Enter email:");
		email = sc.nextLine();
		System.out.println("Enter password of email(password will not be shared to anyone):");
		email_password = sc.nextLine();
		System.out.println("Enter name:");
		details_name = sc.nextLine();
		System.out.println("Enter phone number:");
		details_phone = sc.nextLine();
		System.out.println("Enter postal code:");
		details_pin = sc.nextLine();
		System.out.println("Enter house/flat no. of delivery:");
		details_addressLine1 = sc.nextLine();
		System.out.println("Enter area:");
		details_addressLine2 = sc.nextLine();
		System.out.println("Enter city:");
		details_city = sc.nextLine();
		sc.close();
		
	}
	
	@AfterMethod
	public static void close() {
		 driver.quit();
		 System.out.println("Test completed!!");
	}
	
	@Test
	public static void site() throws InterruptedException{
        driver.manage().window().maximize();//to maximize the window
        String currentHandle= driver.getWindowHandle();
        Thread.sleep(1000);
       
        search();
        productSelection();
        //to handle window
	    Set<String> handles=driver.getWindowHandles();
	    for(String actual: handles) {
	    if(!actual.equalsIgnoreCase(currentHandle)) {
	    //Switch to the opened tab
	    driver.switchTo().window(actual);
	    currentHandle = actual;
	    }
	   } 
	    
	   addToCart();
	   proceedToCart();
	   login();
	   address();
	   Thread.sleep(5000);
	}
	
	
	public static void search() {
		 WebElement adv = driver.findElement(By.id("twotabsearchtextbox"));
	        act.moveToElement(adv).click().build().perform();
	        act.moveToElement(adv).sendKeys("iphone 13",Keys.ENTER).build().perform();
	        //we can also use the bellow to submit
//	        WebElement button =  driver.findElement(By.cssSelector("#nav-search-submit-button"));
//	        act.moveToElement(button).click().build().perform();
	}
	public static void productSelection() throws InterruptedException {
		WebElement product = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span"));
	    act.moveToElement(product).click().build().perform();
	    Thread.sleep(1000);
	}
	
	public static void addToCart() throws InterruptedException {
		WebElement cart = driver.findElement(By.id("add-to-cart-button")); 
	    act.moveToElement(cart).doubleClick().build().perform();
	    Thread.sleep(5000);
	}
	
	public static void proceedToCart() throws InterruptedException {
		WebElement proceedToCart = driver.findElement(By.id("attach-sidesheet-checkout-button")); 
	    act.moveToElement(proceedToCart).doubleClick().build().perform();
	    Thread.sleep(1000);
	}
	
	public static void login() throws InterruptedException {
		driver.findElement(By.id("ap_email")).sendKeys(email,Keys.ENTER);
		   Thread.sleep(1000);
		   driver.findElement(By.id("ap_password")).sendKeys(email_password,Keys.ENTER);
		    Thread.sleep(1000);
	}
	
	public static void address() throws InterruptedException {
		act.sendKeys(Keys.DOWN).build().perform();
		countries();
	    Thread.sleep(1000);
		name();
	    Thread.sleep(2000);
	    phone();
	    Thread.sleep(2000);
	    pin();
	    Thread.sleep(2000);
	    addressline();
	    Thread.sleep(2000);
	    useThisAddress();
        Thread.sleep(2000);
	}
	public static void countries() {
		List<WebElement> countries = driver.findElements(By.id("address-ui-widgets-countryCode"));
	    for (int i = 0; i < countries.size(); i++) {
	        String temp = countries.get(i).getText();
	        if (temp.equals("India")) {
	            countries.get(i).click();
	            break;
	        }
	    }
	}
	public static void name() {
		WebElement fullname = driver.findElement(By.xpath("//*[@id=\"address-ui-widgets-enterAddressFullName\"]"));
		act.moveToElement(fullname).doubleClick().build().perform();
	    act.moveToElement(fullname).sendKeys(details_name).build().perform();
	}
	public static void phone() {
		WebElement mobileNumber = driver.findElement(By.xpath("//*[@id=\"address-ui-widgets-enterAddressPhoneNumber\"]")); 
	    act.moveToElement(mobileNumber).doubleClick().build().perform();
	    act.moveToElement(mobileNumber).sendKeys(details_phone).build().perform();
	}
	public static void pin() {
		 WebElement pincode = driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode"));
		    act.moveToElement(pincode).click().build().perform();
		    act.moveToElement(pincode).sendKeys(details_pin).build().perform();
	}
	public static void addressline() throws InterruptedException {
		WebElement houseNumber = driver.findElement(By.id("address-ui-widgets-enterAddressLine1"));
	    act.moveToElement(houseNumber).click().build().perform();
	    act.moveToElement(houseNumber).sendKeys(details_addressLine1).build().perform();
	    Thread.sleep(1000);
	    WebElement area = driver.findElement(By.id("address-ui-widgets-enterAddressLine2"));
	    act.moveToElement(area).click().build().perform();
	    act.moveToElement(area).sendKeys(details_addressLine2).build().perform();
	    Thread.sleep(1000);
	    WebElement City = driver.findElement(By.id("address-ui-widgets-enterAddressCity"));
	    act.moveToElement(City).click().build().perform();
	    act.moveToElement(City).sendKeys(details_city).build().perform();
	    Thread.sleep(1000);
	}
	public static void useThisAddress() {
		WebElement useThisAddress =  driver.findElement(By.id("address-ui-widgets-form-submit-button"));
        act.moveToElement(useThisAddress).click().build().perform();
	}
}

