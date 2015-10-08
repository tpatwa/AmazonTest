package CucumberTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumTest {
	private static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException {
		// Create a new instance of the Firefox driver

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Nisum User\\Desktop\\Selenium\\Software\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();

		// Put a Implicit wait, this means that any search for elements on the
		// page could take the time the implicit wait is set for before throwing
		// exception

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Launch the Online Store Website

		driver.get("http://www.amazon.com");

		// Find the element that's ID attribute is 'account'(My Account)

		driver.findElement(By.linkText("Sign in")).click();

		// Find the element that's ID attribute is 'log' (Username)

		// Enter Username on the element found by above desc.

		driver.findElement(By.id("ap_email")).sendKeys(
				"adelaide.talent@gmail.com");

		// Find the element that's ID attribute is 'pwd' (Password)

		// Enter Password on the element found by the above desc.

		driver.findElement(By.id("ap_password")).sendKeys("pass22");

		// Now submit the form. WebDriver will find the form for us from the
		// element

		driver.findElement(By.id("signInSubmit-input")).click();

		// Print a Log In message to the screen

		System.out.println("Login Successfully");

		Thread.sleep(5000);

		// Mouse Movement

		// Actions act = new Actions(driver);
		// WebElement mainMenu =
		// driver.findElement(By.linkText("Your Account"));
		// act.moveToElement(mainMenu).build().perform();

		// Actions act = new Actions(driver);
		clickElementByTagNameAndAttributeAndIndex(driver, "SPAN",
				"textContent", "Your Account", 0);
		// act.moveToElement(mainMenu).build().perform();

		Thread.sleep(5000);

		ClickByTagNameAndAttribute(driver, "SPAN", "textContent",
				"Not Adelaide? Sign Out", true);

		// driver.findElement(By.linkText("Sign Out")).click();

		// Find the element that's ID attribute is 'account_logout' (Log Out)

		// driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();

		// Print a Log In message to the screen

		System.out.println("LogOut Successfully");

		// Close the driver

		driver.quit();

	}
	
	public static void MouseHooverOverElementByLinkText(WebDriver wdriver, String text){
		 Actions act = new Actions(driver);
		 WebElement mainMenu =
		 wdriver.findElement(By.linkText(text));
		 act.moveToElement(mainMenu).build().perform();
	}

	public static WebDriver DriverInit(String browser) {
		if (String.valueOf(browser).equals("firefox")) {
			return driver = new FirefoxDriver();
		} else if (String.valueOf(browser).equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Nisum User\\Desktop\\Selenium\\Software\\chrome\\chromedriver.exe");
			return driver = new ChromeDriver();
		}
		return null;
	}
	
	public static WebElement FindLinkElement (WebDriver driver, String linkText){
		DriverSleep (3000);
		driver.findElement(By.linkText(linkText)).click();
		return driver.findElement(By.linkText(linkText));

	}
	
	public static WebElement FindElementById (WebDriver driver, String eid){
		DriverSleep (3000);
		return driver.findElement(By.id(eid));

	}
	
	public static void GoToPage (WebDriver wdriver, String pageUrl){
		wdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wdriver.manage().window().maximize();
		wdriver.get(pageUrl);
		DriverSleep (10000);
	}

	public static void DriverReport(String textmessage) {
		System.out.println(textmessage);
	}

	public static void DriverSleep(int mlsec) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebElement clickElementByTagNameAndAttributeAndIndex(
			WebDriver wdriver, String tagName, String attributeName,
			String attributeValue, int index) {
		int counter = 0;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> elements = wdriver.findElements(By.tagName(tagName));
		for (WebElement element : elements) {
			String value = element.getAttribute(attributeName);
			if (value.equals(attributeValue)) {
				if (element.isDisplayed()) {
					if (index == counter) {
						element.click();
						return element;
					}
					counter++;
				}

			}
		}
		return null;
	}

	public static void verifyElementById(WebDriver wdriver, String id) {
		wdriver.findElement(By.id(id));
	}

	public static void verifyElementByTagNameAndAttributeAndIndex(
			WebDriver wdriver, String tagName, String attributeName,
			String attributeValue, int index) {
		int counter = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> elements = wdriver.findElements(By.tagName(tagName));

		for (WebElement element : elements) {
			String value = element.getAttribute(attributeName);
			if (value.equals(attributeValue)) {

				if (element.isDisplayed()) {
					if (index == counter) {
					}
					counter++;
				}
				return;
			}

		}

		wdriver.findElement(By.id("looking for: " + attributeValue));

	}

	public static WebElement ClickByTagNameAndAttribute(WebDriver wdriver,
			String tag, String attribute, String value, Boolean click) {

		try {

			List<WebElement> elements = wdriver.findElements(By.tagName(tag));
			for (WebElement element : elements) {
				if (element.getAttribute(attribute).equals(value)) {
					try {
						if (click) {
							DriverReport("Clicking on: " + value);
							element.click();
						}
					} catch (Exception e) {
						DriverReport("Error: " + e.getMessage());
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					return element;
				}
			}

			return null;

		} catch (Exception e) {
			DriverReport("Error: " + e.getMessage());
			return null;

		}

	}

}