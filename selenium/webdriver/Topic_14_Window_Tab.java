package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_Window_Tab {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn/");
		
		// Before click
		String kynaID = driver.getWindowHandle();
		System.out.println("ID of page A = " + kynaID);
		
		// Click to Facebook link at Footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		
		// Switch to Facebook page (tab)
		swichToWindowByID(kynaID);
		
		String facebookID = driver.getWindowHandle();
		
		// Verify any element is corrected Facebook page
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));
		
		// Switch to Kyna page (parent)
		swichToWindowByID(facebookID);
		
		// Verify Url is Kyna page
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));
		sleepInSecond(5);
	}
	 
	@Test
	public void TC_02_Greater_Than_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn/");
		
		String kynaID = driver.getWindowHandle();
		
		// Click to Facebook link at Footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		
		// Switch to Facebook page
		switchToWindowByTitle("Đăng nhập Facebook | Facebook");
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));
		
		// Switch to Kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));
		
		// Click Youtube
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		
		// Switch to Youtube
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertTrue(driver.getCurrentUrl().contains("youtube.com"));
		
		// Switch to Kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));
		
		//Click to Primus
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='PRIMUS']")).click();
		
		// Switch to Primus
		switchToWindowByTitle("PRIMUS Homepage");
		Assert.assertTrue(driver.getCurrentUrl().contains("primus.vn"));
		
		// Close all tab without Kyna
		closeAllWindowWithoutParent(kynaID);
	}
	
	public void swichToWindowByID(String windowID) {
		// Lấy hết tất cả các ID của window/ tab đang có
		Set<String> allIDs = driver.getWindowHandles();
		
		// Dùng vòng lặp duyệt qua từng giá trị
		for (String id : allIDs) {
			
			// Mỗi lần duyệt qua 1 giá trị thì kiểm tra điều kiện
			// Nếu nó ko bằng với giá trị đang so sánh
			if (!id.equals(windowID)) {
				
				// Switch vào cái ID đó
				driver.switchTo().window(id);
				sleepInSecond(2);
				
				// Thoát khỏi vòng lặp
				// Đạt được điều kiện rồi - ko cần chạy tiếp các giá trị còn lại nữa
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(String expectedwindowTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectedwindowTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
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