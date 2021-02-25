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


public class Topic_05_Element_Method_III {
	WebDriver driver;
	By emailTextbox = By.cssSelector("#mail");
	By Ageunder18radio = By.cssSelector("#under_18");
	By EducationTextArea = By.cssSelector("#edu");
	By Job1 = By.cssSelector("#job1");
	By Job2 = By.cssSelector("#job2");
	By Job3 = By.cssSelector("#job3");
	By DevelopmentCheckbox = By.cssSelector("#development");
	By Slider1 = By.cssSelector("#slider-1");
	By Slider2 = By.cssSelector("#slider-2");
	By Password = By.cssSelector("#password");
	By Ageradiobutton = By.cssSelector("#radio-disabled");
	By Biographytextarea = By.cssSelector("#bio");
	By InternetsCheckbox = By.cssSelector("#check-disbaled");
	By Java = By.cssSelector("#java");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Check_Element_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (isElementDisplayed(emailTextbox)) {
			senkeyToElement(emailTextbox, "Automation Testing");
		}
		
		if (isElementDisplayed(Ageunder18radio)) {
			clickToElement(Ageunder18radio);
		}
		
		if (isElementDisplayed(EducationTextArea)) {
			senkeyToElement(EducationTextArea, "Automation Testing");
		}
	}	
		
	@Test
	public void TC_02_Check_Element_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(Ageunder18radio));
		Assert.assertTrue(isElementEnabled(EducationTextArea));
		Assert.assertTrue(isElementEnabled(Job1));
		Assert.assertTrue(isElementEnabled(Job2));
		Assert.assertTrue(isElementEnabled(DevelopmentCheckbox));
		Assert.assertTrue(isElementEnabled(Slider1));
		Assert.assertFalse(isElementEnabled(Password));
		Assert.assertFalse(isElementEnabled(Ageradiobutton));
		Assert.assertFalse(isElementEnabled(Biographytextarea));
		Assert.assertFalse(isElementEnabled(Job3));
		Assert.assertFalse(isElementEnabled(InternetsCheckbox));
		Assert.assertFalse(isElementEnabled(Slider2));
		}	
		
	@Test
	public void TC_03_Check_Element_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickToElement(Ageunder18radio);
		clickToElement(Java);
		
		Assert.assertTrue(isElementSelected(Ageunder18radio));
		Assert.assertTrue(isElementSelected(Java));
		
		clickToElement(Ageunder18radio);
		clickToElement(Java);
		
		Assert.assertTrue(isElementSelected(Ageunder18radio));
		Assert.assertFalse(isElementSelected(Java));
	}	
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}
	
	public void senkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);		
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}