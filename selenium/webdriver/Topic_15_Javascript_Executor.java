package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Javascript_Executor {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		
		String homepageDomain = (String) executeForBrowser(driver, "return document.domain;");
		AssertJUnit.assertEquals(homepageDomain, "live.demoguru99.com");
		
		String homepageUrl = (String) executeForBrowser(driver, "return document.URL;");
		AssertJUnit.assertEquals(homepageUrl, "http://live.demoguru99.com/");
		
		highlightElement(driver, "//a[text()='Mobile']");
		clickToElementByJS(driver, "//a[text()='Mobile']");
		
		highlightElement(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		highlightElement(driver, "//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));
		
		highlightElement(driver, "//a[text()='Customer Service']");
		clickToElementByJS(driver, "//a[text()='Customer Service']");
		
		highlightElement(driver, "//input[@id='newsletter']");
		scrollToElement(driver, "//input[@id='newsletter']");
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", getEmailRandom());
		
		highlightElement(driver, "//button[@title='Subscribe']");
		clickToElementByJS(driver, "//button[@title='Subscribe']");
		
		highlightElement(driver, "//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));
		
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		
		String demoGuruDomain = (String) executeForBrowser(driver, "return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}
	
	@Test
	public void TC_02() {
		navigateToUrlByJS(driver, "https://automationfc.github.io/html5/index.html");
		
		clickToElementByJS(driver, "//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@name='fname']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//input[@name='fname']", "Dam Dao");
		clickToElementByJS(driver, "//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@name='pass']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//input[@name='pass']", "123456");
		clickToElementByJS(driver, "//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@name='em']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//input[@name='em']", "123@$!");
		clickToElementByJS(driver, "//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@name='em']"), "A part following '@' should not contain the symbol '$'.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//input[@name='em']", "123@456");
		clickToElementByJS(driver, "//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@name='em']"), "Please match the requested format.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//input[@name='em']", "daominhdam@gmail.com");
		clickToElementByJS(driver, "//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage(driver, "//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select"), "Please select an item in the list.");
		sleepInSecond(2);
	}
	
	@Test
	public void TC_03() {
		navigateToUrlByJS(driver, "https://login.ubuntu.com/");
		
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getElementValidationMessage(driver, "//div[@class='login-form']//input[@id='id_email']"), "Please fill out this field.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//div[@class='login-form']//input[@id='id_email']", "aa");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getElementValidationMessage(driver, "//div[@class='login-form']//input[@id='id_email']"), "Please include an '@' in the email address. 'aa' is missing an '@'.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//div[@class='login-form']//input[@id='id_email']", "123@");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getElementValidationMessage(driver, "//div[@class='login-form']//input[@id='id_email']"), "Please enter a part following '@'. '123@' is incomplete.");
		sleepInSecond(2);
		
		sendkeyToElementByJS(driver, "//div[@class='login-form']//input[@id='id_email']", "dam@gmail.com");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getElementValidationMessage(driver, "//div[@class='login-form']//input[@id='id_password']"), "Please fill out this field.");
		sleepInSecond(2);		
	}
	
	@Test
	public void TC_04() {
		String loginPageUrl, userID, password, customerID;
		String name, dob, address, city, state, pin, mobile, email;
		
		By nameTextboxBy = By.name("name");
		By genderRadioBy = By.name("gender");
		By dobTextboxBy = By.name("dob");
		By addressTextAreaBy = By.name("addr");
		By cityTextboxBy = By.name("city");
		By stateTextboxBy = By.name("state");
		By pinTextboxBy = By.name("pinno");
		By mobileTextboxBy = By.name("telephoneno");
		By emailTextboxBy = By.name("emailid");
		By passwordTextboxBy = By.name("password");
		
		name = "John Kennedy";
		dob = "1960-01-01";
		address = "564 Suitable Address";
		city = "New York";
		state = "Califonia";
		pin = "999666";
		mobile = "0985692999";
		email = getEmailRandom();
		
		driver.get("http://demo.guru99.com/v4");
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		// Get thông tin lưu vào biến
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		
		// Set giá trị từ biến vào form đăng nhập
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// Input
		driver.findElement(nameTextboxBy).sendKeys(name);
		
		//Remove attribute (type=date)
		removeAttributeInDOM(driver, "//input[@name='dob']", "type");
		sleepInSecond(5);
		
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextAreaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(mobileTextboxBy).sendKeys(mobile);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		
		// Server process + Response (Output)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
				
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
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
	
	// document.title/ document.domain/ ...
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String getEmailRandom() {
		Random rand = new Random();
		return "hang" + rand.nextInt() + "@gmail.com";
	}
}