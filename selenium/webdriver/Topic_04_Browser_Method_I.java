package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Method_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
	}

	@Test
	public void TC_01() {
		// Các hàm/method/ command để tương tác với browser
		
		// Mở ra 1 ứng dụng web/ page
		driver.get("http://live.demoguru99.com/"); //**
		
		// Đóng trình duyệt
		// Đóng tab đang mở- nếu có 1 tab = đóng browser
		//driver.close();
		
		// Đóng trình duyệt
		// ko quan tâm tab - đóng tất cả các tab- đóng browser
		//driver.quit();  //**
		
		// Mở trang mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		// Kiểm tra được cái Url của page mới mở có đúng k?
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/mobile.html");
		System.out.println(driver.getCurrentUrl());
		
		// Lấy ra title của page hiện tại
		driver.getTitle(); //*
		
		// Lấy ra source code của page hiện tại
		driver.getPageSource();
		
		// Lấy ra ID của tab/window nó đang đứng (active)
		driver.getWindowHandle(); //**
		 
		// Lấy ra tất cả ID của tab/window nó đang đứng (active)
		driver.getWindowHandles(); //**
		
		//30s
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30000,TimeUnit.MILLISECONDS);
		
		// implicitlywait: chờ cho element được xuất hiện để tương tác trong vòng ... s
		// findElement/ findElements
		
		// Chờ cho page được load trong vòng ... s
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		// Chờ cho 1 đoạn script của JavascriptExecutor thực thi xong trong vòng .... s
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		// Phóng to cái browser
		driver.manage().window().maximize(); //**
		
		// F11
		driver.manage().window().fullscreen();
		
		// Kích thước của trình duyệt
		// Test Responsive
		// 1366x720 1366x768 1920x1080 1440x1080 ....
		// setSize
		// getSize
		
		// Vị trí của trình duyệt so với màn hình hiện tại
		// SetPosition
		// GetPosition
		
		// Back lại trang trước đó
		driver.navigate().back(); //*
		
		// Tiếp trang trước đó
		driver.navigate().forward(); //*
		
		// Tải lại trang đó (F5/ Reload)
		driver.navigate().refresh(); //*
		
		// Mở ra 1 Url: tracking history tốt hơn
		driver.navigate().to("http://live.demoguru99.com/"); //*
		
		// Thao tác với Alert
		driver.switchTo().alert(); //**
		
		// Thao tác với Frame/ Iframe
		driver.switchTo().frame(0); //**
		
		// Thao tác với Window/ Tab
		driver.switchTo().window(""); //**
	}

}