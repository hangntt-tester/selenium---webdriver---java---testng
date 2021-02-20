package webdriver;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
 
public class Topic_03_Run_On_Browser {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	
	//@Test
	public void TC_01_Run_On_Firefox() {
		//Firefox 47 + Selenium 2.xx + ko dùng geckodriver
		driver = new FirefoxDriver();
		
		//Firefox 48 + Selenium 3.xx + dùng geckodriver
		System.setProperty("webdriver.gecko.driver", "//path_of_geckodriver");
		
		driver.get("https://google.com");
		driver.quit();
	}
	
	@Test
	public void TC_02_Run_On_Chrome() {
		//01 = Chỉ trên 1 máy
		//System.setProperty("webdriver.chrome.driver", "D:\\AUTOMATION TESTING\\02 - Selenium API\\selenium-webdriver-java-testng\\BrowserDrivers\\chromedriver.exe");
		
		//02 = Tất cả các máy
		//System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		// . = đại diện cho project location
		
		//03 = Tất cả các máy
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\BrowserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("https://google.com");
		driver.quit();
	}
	
}
