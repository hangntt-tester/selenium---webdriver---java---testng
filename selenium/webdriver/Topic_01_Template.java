package webdriver;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
public class Topic_01_Template {
WebDriver driver;
 
@BeforeClass
public void beforeClass() {
driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
 
@Test
public void TC_01_ID() {
	// Để thao tác vs 1 Element ở trên page
	
}
 
@Test
public void TC_02_Classname() {
 driver.get("");
}
 
@Test
public void TC_03_Name() {
	
}


@AfterClass
public void afterClass() {
driver.quit();
}
 
}