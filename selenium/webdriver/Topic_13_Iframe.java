package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Iframe {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.face-content")).isDisplayed());
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div._1drq")).getText(), "169K likes");
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		
		driver.findElement(By.xpath("//div[@class='editing field profile_field']//input[@placeholder='Nhập tên của bạn']")).sendKeys("Wick");
		
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0986666888");
		
		driver.findElement(By.xpath("//select[@id='serviceSelect']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//select[@id='serviceSelect']/option[text()='TƯ VẤN TUYỂN SINH']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Test");
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[@class='logged_in_name ng-binding' and text()='Wick']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[@class='logged_in_phone ng-binding' and text()='0986666888']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[(@name='message' and text()='Test')]")).isDisplayed());
	}
		
		@Test
		public void TC_02_Iframe() {
		driver.get("https://kyna.vn/");
		
		driver.findElement(By.xpath("//i[@class='far fa-search']")).click();
		
		driver.findElement(By.xpath("//input[@id='m-live-search-bar']")).sendKeys("Excel");
		
		action.sendKeys(Keys.ENTER).perform();
		
		List<WebElement> excelText = driver.findElements(By.cssSelector(".content>h4"));
		for (WebElement text : excelText) {
			Assert.assertTrue(text.getText().contains("Excel"));
		}	
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