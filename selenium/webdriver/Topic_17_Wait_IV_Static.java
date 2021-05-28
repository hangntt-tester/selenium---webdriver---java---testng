package webdriver;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void TC_01_Enough() { 
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	
	@Test
	public void TC_02_Less() { 
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		sleepInSecond(1); // Testcase này fail vì 1s chưa đủ để hiện kết quả
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	
	@Test
	public void TC_03_Greater() { 
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		System.out.println("Start - " + getDateTimeNow());
		sleepInSecond(5);
		System.out.println("End - " + getDateTimeNow());
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long timeOutInSecond) {
		try {
		Thread.sleep(timeOutInSecond * 1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}