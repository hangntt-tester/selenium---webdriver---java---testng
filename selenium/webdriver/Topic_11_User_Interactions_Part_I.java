package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interactions_Part_I {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");	
	}
	
	@Test
	public void TC_02_Hover_And_Click() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");	
	}
	
	@Test
	public void TC_03_Hover_Mouse() {
		driver.get("https://hn.telio.vn/");
		
		action.moveToElement(driver.findElement(By.xpath("//main[@class='page-main']//span[text()='Đồ uống']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//main[@class='page-main']//a[text()='Cà phê']")).getText(), "Cà phê");
	}
	
	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Assert.assertEquals(numberSelect.size(), 12);
		
		action.clickAndHold(numberSelect.get(0)).moveToElement(numberSelect.get(3)).release().perform();
		sleepInSecond(3);

		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);
		
		for (WebElement number : numberSelected) {
			System.out.println(number.getText());
		}
	}
	
	@Test
	public void TC_05_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Assert.assertEquals(numberSelect.size(), 12);
		
		// Nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Nhấn chọn các số: 2, 7, 9, 10
		action.click(numberSelect.get(1)).click(numberSelect.get(6)).click(numberSelect.get(8)).click(numberSelect.get(9)).perform();
		
		// Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);
		
		for (WebElement number : numberSelected) {
			System.out.println(number.getText());
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