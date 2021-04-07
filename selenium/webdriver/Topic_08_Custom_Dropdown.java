package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	String moth = "May";
	String[] Firstmonths = {"January", "April", "July"};
	String[] Secondmonths = {"January", "April", "July", "May"};

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "12");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "12");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "1");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "1");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "10");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
	}
	
	@Test
	public void TC_02_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		
		selectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "25");
		selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']/option", "May");
		selectItemInCustomDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']/option", "1994");
	}
	
	@Test
	public void TC_03_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemInCustomDropdown("//*[@id=\"games\"]/span", "//ul[@id='games_options']//li", "Football");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@placeholder='Select a game']")).getAttribute("aria-label"), "Football");	
		
		selectItemInCustomDropdown("//*[@id=\"games\"]/span", "//ul[@id='games_options']//li", "Golf");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@placeholder='Select a game']")).getAttribute("aria-label"), "Golf");		
	}
	
	@Test
	public void TC_04_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Jenny Hess");
		
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Stevie Feliciano");
		
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Matt");
	}
	
	@Test
	public void TC_05_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown("//div[@class='btn-group']", "//ul[@class='dropdown-menu']/li", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		
		selectItemInCustomDropdown("//div[@class='btn-group']", "//ul[@class='dropdown-menu']/li", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		
		selectItemInCustomDropdown("//div[@class='btn-group']", "//ul[@class='dropdown-menu']/li", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
	}
	
	@Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Afghanistan");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Afghanistan");
		
		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Australia");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Australia");
		
		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Benin");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Benin");	
	}
	
	@Test
	public void TC_07_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemEditableDropdown("//input[@class='search']", "Afghanistan");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Afghanistan");
		
		selectItemEditableDropdown("//input[@class='search']", "Australia");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Australia");
		
		selectItemEditableDropdown("//input[@class='search']", "Benin");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Benin");	
	}
	
	@Test
	public void TC_08_Multiple_Select() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", Firstmonths);
		Assert.assertTrue(areItemSelected(Firstmonths));
		
		driver.navigate().refresh();
		
		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", Secondmonths);
		Assert.assertTrue(areItemSelected(Secondmonths));
	}
	
	public void selectItemInCustomDropdown(String parenXpath, String allItemXpath, String expectedText) {
		// Click vào dropdown
		driver.findElement(By.xpath(parenXpath)).click();
		
		// Chờ cho các item được hiển thị ra trước khi chọn
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		// Lấy hết tất cả các item con đưa vào 1 List để duyệt qua
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));
		
		// Dùng vòng lặp để duyệt qua các item
		for (WebElement item : allItem) {
			// Duyệt qua từng cái và getText ra
			// Nếu text get ra bằng với text mong muốn thì dừng lại và click vào item đó luôn
			// Thoát khỏi vòng lặp
			if(item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}
	
	public void selectItemInEditableDropdown(String parenXpath, String allItemXpath, String expectedText) {
		driver.findElement(By.xpath(parenXpath)).clear();
		driver.findElement(By.xpath(parenXpath)).sendKeys(expectedText);
		
		List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		for (WebElement item : allItem) {
			if(item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}
	
	public void selectItemEditableDropdown(String parenXpath, String expectedText) {
		driver.findElement(By.xpath(parenXpath)).clear();
		driver.findElement(By.xpath(parenXpath)).sendKeys(expectedText);
		
		driver.findElement(By.xpath(parenXpath)).sendKeys(Keys.TAB);
	}
	
	public void selectMultipleItemInDropdown(String parenXpath, String childXpath, String[] months) {
		// Click vào dropdown
		driver.findElement(By.xpath(parenXpath)).click();
		
		// Chờ cho các item được hiển thị ra trước khi chọn
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// Lấy hết tất cả các item con đưa vào 1 List để duyệt qua
		List<WebElement> allItem = driver.findElements(By.xpath(childXpath));
		
		// Dùng vòng lặp để duyệt qua các item
		for (String month : months) {
			for(WebElement item : allItem) {
				if(item.getText().equals(month)) {
					item.click();
					break;
				}
			}
		}
	}
	
	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class = 'ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		
		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : months) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		} else if (numberItemSelected > 12) {
			return driver.findElement(By.xpath("//button[@class = 'ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class = 'ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			new RuntimeException ("No month selected");
			return false;
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}	
	
//Hành vi của 1 dropdown:
//- Click vào dropdown
//- Chờ các item được hiển thị ra
//	- Tìm cái item cần chọn -> Lấy ra text của item mà mình mong muốn -> So sánh với cái mình đang mong đợi -> Bằng nhau
//	+ Item mình cần nó nằm trong tầm nhìn thấy của User -> Click luôn
//	+ Ko nằm trong tầm nhìn thấy (Viewpost) -> Scroll xuống -> Click
//- Bấm vào - Kiểm tra xem chọn đúng chưa