package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_III_Find_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void TC_01_Dont_Set_Implicit() { // Testcase này fail do locator có sẵn rồi, nó sẽ tìm ra dấu "." luôn
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	
	@Test
	public void TC_02_Invalid_Locator() { // Testcase này fail do locator có sẵn rồi, nó sẽ tìm ra dấu "." luôn
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	
	@Test
	public void TC_03_Valid_Locator() { // Testcase này pass
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='ng-binding' and text()='11']")).isDisplayed());
	}

	@Test
	public void TC_04_Valid_Locator() { // Testcase này fail chỉ chờ 1s thì chưa kịp load ra kết quả 11
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.xpath("//button[@id='gobutton']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='ng-binding' and text()='11']")).isDisplayed());
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}