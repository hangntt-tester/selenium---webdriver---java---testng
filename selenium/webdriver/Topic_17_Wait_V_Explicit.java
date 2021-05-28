package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_Enough() {
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		explicitWait = new WebDriverWait(driver, 2);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	
	@Test
	public void TC_02_Less() {
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		explicitWait = new WebDriverWait(driver, 1); // Fail do timeout sau 1s
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	@Test
	public void TC_03_Greater() {
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		explicitWait = new WebDriverWait(driver, 10);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}