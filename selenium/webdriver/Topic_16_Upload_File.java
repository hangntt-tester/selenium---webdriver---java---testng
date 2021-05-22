package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Topic_16_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String seleniumFileName = "selenium.png";
	String eclipseFileName = "eclipse.png";
	String javaFileName = "java.png";
	String seleniumPath = projectPath + getFileSeparator() +"uploadFile" + getFileSeparator() + seleniumFileName;
	String eclipsePath = projectPath + getFileSeparator() +"uploadFile" + getFileSeparator() + eclipseFileName;
	String javaPath = projectPath + getFileSeparator() +"uploadFile" + getFileSeparator() + javaFileName;
	
	
	//@Test
	public void TC_01_Upload_One_File_Firefox() {
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// Load file lên
		uploadFile.sendKeys(seleniumPath);
		sleepInSecond(3);
		// Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + seleniumFileName + "']")).isDisplayed());
			
		// Click start upload
		driver.findElement(By.cssSelector(".files .start")).click();
		sleepInSecond(3);
		// Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());
		
		driver.quit();
	}
	
	//@Test
	public void TC_02_Upload_One_File_Chrome() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "//BrowserDrivers//chromedriver");
		}
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// Load file lên
		uploadFile.sendKeys(seleniumPath);
		sleepInSecond(3);
		// Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + seleniumFileName + "']")).isDisplayed());
			
		// Click start upload
		driver.findElement(By.cssSelector(".files .start")).click();
		sleepInSecond(3);
		// Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());
		
		driver.quit();
	}
	
	@Test
	public void TC_03_Upload_Multiple_File() {
		// chrome chạy được, còn firefox không chạy được trên bản cũ, phải dùng firefox bản mới nhất
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "//BrowserDrivers//chromedriver");
		}
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// Load file lên
		uploadFile.sendKeys(seleniumPath + "\n" + eclipsePath + "\n" + javaPath);
		sleepInSecond(3);
		// Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + seleniumFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + eclipseFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javaFileName + "']")).isDisplayed());
			
		// Click start upload for each file
		List<WebElement> startButtons = driver.findElements(By.cssSelector(".files .start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		
		// Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + eclipseFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + javaFileName + "']")).isDisplayed());
		
		driver.quit();
	}
	
	public void sleepInSecond (long second) {
		try {
		Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	
	public String getFileSeparator() {
		return File.separator;
	}
}