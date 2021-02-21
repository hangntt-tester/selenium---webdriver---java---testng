package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Element_Method_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Check_Element_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		boolean emailTextboxStatus = driver.findElement(By.cssSelector("#mail")).isDisplayed();
		if (emailTextboxStatus == true) {
			driver.findElement(By.cssSelector("#mail")).sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		boolean AgeundereighteenradiobuttonStatus = driver.findElement(By.xpath("//input[@value='under_18']")).isDisplayed();
		if (AgeundereighteenradiobuttonStatus == true) {
			driver.findElement(By.xpath("//input[@value='under_18']")).click();
			System.out.println("Under 18 is displayed");
		} else {
			System.out.println("Under 18 is not displayed");
		}
		
		boolean EducationTextAreaStatus = driver.findElement(By.cssSelector("#edu")).isDisplayed();
		if (EducationTextAreaStatus == true) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("Automation Testing");
			System.out.println("Education textbox is displayed");
		} else {
			System.out.println("Education textbox is not displayed");
		}
	}	
	
	@Test
	public void TC_02_Check_Element_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		boolean emailTextboxStatus = driver.findElement(By.cssSelector("#mail")).isEnabled();
		if (emailTextboxStatus) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}
		
		boolean AgeundereighteenradiobuttonStatus = driver.findElement(By.cssSelector("#under_18")).isEnabled();
		if (AgeundereighteenradiobuttonStatus) {
			System.out.println("Under 18 is enabled");
		} else {
			System.out.println("Under 18 is disabled");
		}
		
		boolean EducationTextAreaStatus = driver.findElement(By.cssSelector("#edu")).isEnabled();
		if (EducationTextAreaStatus) {
			System.out.println("Education is enabled");
		} else {
			System.out.println("Education is disabled");
		}
		
		boolean JobRoleoneDropdownStatus = driver.findElement(By.cssSelector("#job1")).isEnabled();
		if (JobRoleoneDropdownStatus) {
			System.out.println("Job Role 01 is enabled");
		} else {
			System.out.println("Job Role 01 is disabled");
		}
		
		boolean JobRoletwoDropdownStatus = driver.findElement(By.cssSelector("#job2")).isEnabled();
		if (JobRoletwoDropdownStatus) {
			System.out.println("Job Role 02 is enabled");
		} else {
			System.out.println("Job Role 02 is disabled");
		}
		
		boolean DevelopmentCheckboxStatus = driver.findElement(By.cssSelector("#development")).isEnabled();
		if (DevelopmentCheckboxStatus) {
			System.out.println("Development is enabled");
		} else {
			System.out.println("Development is disabled");
		}
		
		boolean SlideroneStatus = driver.findElement(By.cssSelector("#slider-1")).isEnabled();
		if (SlideroneStatus) {
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is disabled");
		}
		
		boolean PasswordtextboxStatus = driver.findElement(By.cssSelector("#password")).isEnabled();
		if (PasswordtextboxStatus) {
			System.out.println("Password is enabled");
		} else {
			System.out.println("Password is disabled");
		}
		
		boolean AgeradiobuttonStatus = driver.findElement(By.cssSelector("#radio-disabled")).isEnabled();
		if (AgeradiobuttonStatus) {
			System.out.println("Radiobutton is enabled");
		} else {
			System.out.println("Radiobutton is disabled");
		}
		
		boolean BiographytextareaStatus = driver.findElement(By.cssSelector("#bio")).isEnabled();
		if (BiographytextareaStatus) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography is disabled");
		}
		
		boolean JobRolethreeDropdownStatus = driver.findElement(By.cssSelector("#job3")).isEnabled();
		if (JobRolethreeDropdownStatus) {
			System.out.println("Job Role 03 is enabled");
		} else {
			System.out.println("Job Role 03 is disabled");
		}
		
		boolean InternetsCheckboxStatus = driver.findElement(By.cssSelector("#check-disbaled")).isEnabled();
		if (InternetsCheckboxStatus) {
			System.out.println("Checkbox is enabled");
		} else {
			System.out.println("Checkbox is disabled");
		}
		
		boolean SlidertwoStatus = driver.findElement(By.cssSelector("#slider-2")).isEnabled();
		if (SlidertwoStatus) {
			System.out.println("Slider 02 is enabled");
		} else {
			System.out.println("Slider 02 is disabled");
		}
	}	
	
	@Test
	public void TC_03_Check_Element_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.cssSelector("#under_18")).click();
		driver.findElement(By.cssSelector("#java")).click();
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
		driver.findElement(By.cssSelector("#under_18")).click();
		driver.findElement(By.cssSelector("#java")).click();
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	}	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}