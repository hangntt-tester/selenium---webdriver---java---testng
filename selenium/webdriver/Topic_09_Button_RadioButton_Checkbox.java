package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_RadioButton_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		By loginButton = By.cssSelector(".fhs-btn-login");
		
		// Verify login Button disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		// Nhập mail, password
		driver.findElement(By.cssSelector("#login_username")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("automation");
		sleepInSecond(2);
		
		// Verify login Button enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Refresh lại trang
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		removeDisableAtributeByJS(loginButton);
		sleepInSecond(2);
		
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");	
	}
	
	@Test
	public void TC_02_Default_all_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Click to all checkboxes
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
		
		// Verify all checkboxes are selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Click to checkbox
		driver.findElement(By.xpath("//input[@value='Gallstones']")).click();
		sleepInSecond(1);
		
		//verify checkbox is Selected
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Gallstones']")).isSelected());
		
		// Bỏ chọn
		driver.findElement(By.xpath("//input[@value='Gallstones']")).click();
		sleepInSecond(1);
		
		//verify checkbox is De-Selected
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Gallstones']")).isSelected());
		
		// Click radio button
		driver.findElement(By.xpath("//input[@value='I have a loose diet']")).click();
		sleepInSecond(1);
		
		//verify radio button is Selected
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='I have a loose diet']")).isSelected());
	}
	
	@Test
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		// 1- Thẻ input ko click được nhưng lại dùng để verify được
		//Click radio button
		// driver.findElement(By.xpath("//input[@value='Spring']")).click();
		
		// Verify - isSelected -> kiểm tra được 1 radio/checkbox là thẻ input. Dùng các thẻ khác ko verify được
		// Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
		
		// 2- Thẻ span click được nhưng lại ko verify được
		// driver.findElement(By.xpath("//input[@value='Spring']//preceding-sibling::span[@class='mat-radio-outer-circle']")).click();
		
		// Verify
		// Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']//preceding-sibling::span[@class='mat-radio-outer-circle']")).isSelected());
		
		// 3- Dùng kết hợp cả 2 (Khi làm dự án: 1 element mà mình phải design tới 2 loại locator 
		//driver.findElement(By.xpath("//input[@value='Spring']//preceding-sibling::span[@class='mat-radio-outer-circle']")).click();
		//sleepInSecond(2);
		
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
		
		// 4- Dùng hàm click của JS - dùng thẻ input để click và verify luôn
		By springRadio = By.xpath("//input[@value='Spring']");
		clickByJS(springRadio);
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(springRadio).isSelected());
	}
	
	@Test
	public void TC_04_Custom_Radio_Checkbox_II() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		// Before click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		// Click
		driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
		sleepInSecond(2);
		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void removeDisableAtributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	// Ko care element hidden/ visibale
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
		
	public void sleepInSecond (long second) {
		try {
		Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
}