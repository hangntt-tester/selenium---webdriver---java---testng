package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_II_Find_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://facebook.com/");
	}
	
	//@Test
	public void TC_01_Find_Element() { 
		// 1 - Tìm được 1 element
		// Nếu như tìm thấy ngay thì không cần chờ hết timeout
		// Nếu như chưa tìm thấy thì tiếp tục chờ - trong thời gian chờ mỗi nửa s sẽ tìm lại 1 lần
		// Nếu như tìm thấy vẫn đang còn trong thời gian chờ thì ko cần chờ hết timeout
		// Chuyển qua step tiếp theo
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("End - " + getDateTimeNow());
		
		// 2 - Tìm được nhiều hơn 1 element
		// Nó sẽ thao tác vs element đầu tiên (ko quan tâm đến element ẩn hay hiện)
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("dam@gmail.com");;
		System.out.println("End - " + getDateTimeNow());
		
		// 3 - Không tìm thấy element nào
		// Chờ hết timeout rồi mà vẫn ko tìm thấy element
		// Đánh testcase này fail ngay lập tức tại đúng step này
		// Throw ra 1 exception: No such element
		System.out.println("Start - " + getDateTimeNow());
		try {
			driver.findElement(By.xpath("//label"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("End - " + getDateTimeNow());
		}
	}

	@Test
	public void TC_02_Find_Elements() { 
		// 1 - Tìm được 1 element
		// Nếu như tìm thấy ngay thì không cần chờ hết timeout
		// Nếu như chưa tìm thấy thì tiếp tục chờ - trong thời gian chờ mỗi nửa s sẽ tìm lại 1 lần
		// Nếu như tìm thấy vẫn đang còn trong thời gian chờ thì ko cần chờ hết timeout
		// Chuyển qua step tiếp theo		
		// Lưu nó vào trong List (Chỉ có duy nhất 1 element)
		System.out.println("Start - " + getDateTimeNow());
		List<WebElement> elements = driver.findElements(By.id("email"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
		
		// 2 - Tìm được nhiều hơn 1 element
		// Lưu vào trong List (Chứa các element thỏa mãn điều kiện)
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
		
		// 3 - Không tìm thấy element nào
		// Chờ hết timeout rồi mà vẫn ko tìm thấy element
		// Ko đánh fail testcase
		// Ko throw exception
		// Chỉ là 1 List rỗng
		// Chuyển qua step tiếp theo để chạy tiếp
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//label"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}