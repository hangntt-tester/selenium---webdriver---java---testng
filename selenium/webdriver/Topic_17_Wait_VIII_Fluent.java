package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_VIII_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebElement> fluentElement;
	long timeout = 15;
	long interval = 1;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	//@Test
	public void TC_01_Fluent() { 
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentElement = new FluentWait<WebElement>(driver.findElement(By.id("javascript_countdown_time")));
		
		fluentElement.withTimeout(12, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.getText().endsWith("00");
				System.out.println("Time = " + element.getText());
				return flag;
			}		
		});
	}
	
	@Test
	public void TC_02_Fluent() { 
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		waitForElementAndClick(By.xpath("//div[@id='start']/button"));
		
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public WebElement getWebElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(interval, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		} );
		return element;
	}
	
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(interval, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		} );
		element.click();
	}
	
	public boolean waitForElementAndDisplayed(By locator) {
		WebElement element = getWebElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(interval, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		} );
		return isDisplayed;
	}
}