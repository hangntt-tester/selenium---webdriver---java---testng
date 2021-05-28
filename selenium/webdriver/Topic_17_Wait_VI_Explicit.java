package webdriver;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_VI_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String CV1FileName = "CV1.pdf";
	String CV2FileName = "CV2.pdf";
	String CV1Path = projectPath + getFileSeparator() +"uploadFile" + getFileSeparator() + CV1FileName;
	String CV2Path = projectPath + getFileSeparator() +"uploadFile" + getFileSeparator() + CV2FileName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// Wait for Date Picker visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		// Verify text in 'Selected Dates'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		// Wait for Current Date clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Sunday, May 16, 2021']")));
		
		driver.findElement(By.xpath("//td[@title='Sunday, May 16, 2021']")).click();
		
		// Wait for Ajax loading icon invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		// Wait for Curent Date is selected
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Sunday, May 16, 2021' and @class='rcSelected']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@title='Sunday, May 16, 2021' and @class='rcSelected']")).isDisplayed());
		
		// Verify text in 'Selected Dates'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Sunday, May 16, 2021");
	}
	
	@Test
	public void TC_02() {
		driver.get("https://filebin.net/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(CV1Path + "\n" + CV2Path);
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='progress']"))));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + CV1FileName + "']")));
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + CV2FileName + "']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + CV1FileName + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + CV2FileName + "']")).isDisplayed());
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getFileSeparator() {
		return File.separator;
	}
}