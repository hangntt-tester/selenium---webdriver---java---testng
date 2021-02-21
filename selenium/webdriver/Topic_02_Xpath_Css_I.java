package webdriver;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
public class Topic_02_Xpath_Css_I {
WebDriver driver;
 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fdigital");
	}
 
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation Testing");
		driver.findElement(By.id("Email")).sendKeys("automationtest@gmail.com");
	}
 
	@Test
	public void TC_02_Classname() {
		driver.findElement(By.className("search-box-text")).sendKeys("macbook");
	}
 
	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("Company")).sendKeys("Selenium");
	}

	@Test
	public void TC_04_Tagname() {
		System.out.println(driver.findElements(By.tagName("select")).size());
	}

	@Test
	public void TC_05_Link_Text() {
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
	}

	@Test
	public void TC_06_Partial_Link_Text() {
		Assert.assertTrue(driver.findElement(By.partialLinkText("Digital ")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.partialLinkText("downloads")).isDisplayed());
	}

	@Test
	public void TC_07_Css() {
		driver.findElement(By.cssSelector("#Password")).sendKeys("Pass@1234");
		driver.findElement(By.cssSelector("input[name='ConfirmPassword']")).sendKeys("Pass@1234");
	}

	@Test
	public void TC_08_Xpath() {
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).clear();
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("Pass@1234");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 
}