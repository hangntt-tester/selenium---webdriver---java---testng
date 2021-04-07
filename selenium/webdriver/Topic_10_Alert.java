package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecond(5);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}
	
	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSecond(5);
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}
	
	@Test
	public void TC_03_Prompt_Alert() {
		String alertText = "automation Test 2021";
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(2);
		
		alert.sendKeys(alertText);
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + alertText);
	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		// Selenium By Pass
		// http://username:password@the-internet.herokuapp.com/basic_auth
		// Đối với TH pass chứa kí tự đặc biệt @ -> phải encoding html password -> URL Encode
		// Ví dụ: p@auto1! -> encoding thành: p%40auto1%21
		
		// user, password đều là: admin
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_05_Authentication_Alert_II() {
		driver.get("http://the-internet.herokuapp.com/");
		
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getCredentialToUrl(url, "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_06_Authentication_AutoIT() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String autoITScriptPath = projectPath + "\\autoIT\\authen_firefox.exe";
		
		Runtime.getRuntime().exec(new String[] { autoITScriptPath, "admin", "admin"});
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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
	
	public String getCredentialToUrl(String url, String username, String password) {
		String[] newUrl = url.split("//");
		url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		return url;
	}
}