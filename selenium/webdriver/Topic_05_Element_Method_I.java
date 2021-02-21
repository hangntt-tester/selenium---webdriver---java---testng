package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Element_Method_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_WebElement_Command() {
		WebElement element = driver.findElement(By.xpath(""));
		
		// Xóa dữ liệu đã nhập trong 1 textbox/ textarea/ dropdown (editable)
		element.clear(); //**
		
		// Nhập dữ liệu vào 1 textbox/ textarea/ dropdown (editable)
		element.sendKeys("Hằng Nguyễn"); //**
		
		// Click vào button/ checkbox/ radio button/ link/ dropdown/ image/..
		element.click(); //**
		
		// Lấy ra giá trị nằm trong 1 attribute
		element.getAttribute("placeholder"); //**
		// Search entire store here...
		
		// Lấy ra style của 1 element (font/ size/ color/ background/  -> GUI)
		element.getCssValue("background");
		element.getCssValue("color");
		element.getCssValue("font-size");
		// #3399cc
		// #FFFF
		// 13px
		
		//GUI
		element.getLocation();
		element.getRect();
		element.getSize();
		
		// Chụp hình lỗi đưa vào report -> Framework
		// element.getScreenshotAs(target);
		
		String emailTextboxTagname = element.getTagName();
		Assert.assertEquals(emailTextboxTagname, "input");
		
		// input/ div/ span
		// Đầu ra của hàm này -> tên thẻ -> đầu vào của 1 element khác (Tagname trong xpath)
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));
		
		// Lấy ra text của 1 element bất kì (label/ header/ span/ div/...) - text ko nằm trong attribute
		element.getText(); //**
		
		// Kiểm tra mong muốn element đang hiển thị
		Assert.assertTrue(element.isDisplayed()); //**
		
		// Có thể thao tác được
		Assert.assertTrue(element.isEnabled()); //**
		
		// Đã được chọn thành công
		Assert.assertTrue(element.isSelected()); //**
		
		// Kiểm tra ko mong muốn element hiển thị
		Assert.assertFalse(element.isDisplayed());
		
		// ENTER vào trong 1 form (chỉ dùng được với form)
		element.submit();
	}	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}