package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitwait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitwait = new WebDriverWait(driver, 15);
	}
	
	@Test
	public void TC_01_Visible() { 
		// 1. Phải có trên UI(bắt buộc)  2. Phải có trong DOM(bắt buộc)   3. Người dùng có thể thao tác và nhìn thấy được
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Chờ cho Submit button được visible trong vòng 15s
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));		
	}
	
	@Test
	public void TC_02_Invisible() { 
		// 1. Ko có trên UI(bắt buộc)  2. Có thể có hoặc ko có trong DOM  3. Người dùng ko thể thao tác và ko nhìn thấy được
		
		driver.get("http://live.demoguru99.com/");
		
		// element ko có trên UI nhưng có trong DOM
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
		
		// element ko có trên UI và ko có trong DOM
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));
	}
	
	@Test
	public void TC_03_Presence() {
		// 1. Có thể có hoặc ko có trên UI(bắt buộc)  2. Phải có trong DOM(bắt buộc)
		
		driver.get("http://live.demoguru99.com/");
		
		// element ko có trên UI nhưng có trong DOM
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
		
		// element có trên UI và có trong DOM
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search']")));
	}

	@Test
	public void TC_04_Staleness() {
		 // Staleness: Người dùng thao tác hoặc load lại trang làm cho element bị thay đổi trạng thái
		// Điều kiện bắt buộc: không có ở trong DOM
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
		
		WebElement errorMessage = driver.findElement(By.xpath("//li[text()='An email address required.']"));
		
		driver.navigate().refresh();
		
		explicitwait.until(ExpectedConditions.stalenessOf(errorMessage));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}