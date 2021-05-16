package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Popup_Fixed() {
		driver.get("https://www.zingpoll.com/");
		
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='Login' and @aria-hidden='false']//div")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='Login' and @aria-hidden='true']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Popup_Fixed() {
		driver.get("https://bni.vn/");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		
		action.click(driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']"))).perform();
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Popup_In_Dom() {
		driver.get("https://blog.testproject.io/");
		
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
		}
		
		driver.findElement(By.xpath("//aside[@id='secondary']//input[@class='search-field']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//aside[@id='secondary']//span[@class='glass']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='post-on-archive-page']//h3//a[contains(text(),'Selenium')]")).isDisplayed());
	}
	
	@Test
	public void TC_04_Popup_Not_In_Dom() {
		driver.get("https://tiki.vn");
		
		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		
		Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']/div")).size(), 0);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long second) {
		try {
		Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
}