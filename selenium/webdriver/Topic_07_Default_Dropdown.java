package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	Select select;
	String firstName, lastName, date, month, year, email, companyName, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		firstName = "John";
		lastName = "Wick";
		date = "25";
		month = "May";
		year = "1994";
		email = "johnwick" + getRandomNumber() + "@hotmail.com" ;
		companyName ="Holywood";
		password = "123456";
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register() {
		/* 1 - Mở ra trang Register */
		driver.findElement(By.cssSelector(".ico-register")).click();
		sleepInSecond(3);
		
		/* 2 - Điền thông tin vào các field required */
		checkToCheckBoxOrRadio(By.cssSelector("#gender-female"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		// Khởi tạo biến select để thao tác với dropdown Date
		select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthDay']")));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		// Khởi tạo biến select để thao tác với dropdown Month
		select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		// Khởi tạo biến select để thao tác với dropdown Year
		select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		checkToCheckBoxOrRadio(By.id("Newsletter"));
		
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
				
		/* 3 - Đăng kí */
		driver.findElement(By.id("register-button")).click();
		
		/* 4 - Kiểm tra message đăng kí thành công */
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		/* 5 - Vào trang My Account */
		driver.findElement(By.cssSelector(".ico-account")).click();
		
		/* 6 - Kiểm tra đúng với thông tin đã đăng kí */
		Assert.assertTrue(driver.findElement(By.cssSelector("#gender-female")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("#Newsletter")).isSelected());
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}
	
	@Test
	public void TC_02_Register() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.name("user_job2")));
		
		ArrayList<String> allItemText = new ArrayList<String>();
		allItemText.add("Automation");
		allItemText.add("Mobile");
		allItemText.add("Perfomance");
		allItemText.add("Functional UI");
		
		// Chọn 4 item
		for (String item : allItemText) {
			select.selectByVisibleText(item);
		}
		sleepInSecond(5);
		
		// Kiểm tra dropdown có hỗ trợ multiple select
		Assert.assertTrue(select.isMultiple());
		
		// Kiểm tra đã chọn đúng 4 item thành công
		List<WebElement> allSelectedItems = select.getAllSelectedOptions();
		ArrayList<String> allSelectedText = new ArrayList<String>();
		
		for (WebElement item : allSelectedItems) {
			allSelectedText.add(item.getText());
		}
		
		Assert.assertEquals(allSelectedText.size(), 4);
		Assert.assertEquals(allSelectedText, allItemText);
	}
	
	public void checkToCheckBoxOrRadio (By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckBox (By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void sleepInSecond (long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber( ) {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}	
	