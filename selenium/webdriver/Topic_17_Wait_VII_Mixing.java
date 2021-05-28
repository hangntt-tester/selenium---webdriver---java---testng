package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_VII_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	//@Test
	public void TC_01_Element_Found() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		explicitWait = new WebDriverWait(driver, 5);
		
		System.out.println("Start time (ex): " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='FirstName']")));
		System.out.println("End time (ex): " + getDateTimeNow());
		
		System.out.println("Start time (im): " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		System.out.println("End time (im): " + getDateTimeNow());
	}
	
	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("Start time (im): " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='Country']")).sendKeys("Automation");;
		System.out.println("End time (im): " + getDateTimeNow());
	}
	
	//@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 7);
		
		System.out.println("Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Country']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End time: " + getDateTimeNow());
	}
	
	//@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		explicitWait = new WebDriverWait(driver, 7);
		
		System.out.println("Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Country']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End time: " + getDateTimeNow());
	}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit_FindElement() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		explicitWait = new WebDriverWait(driver, 7);
		
		System.out.println("Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement((By.xpath("//input[@id='Country']")))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End time: " + getDateTimeNow());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}