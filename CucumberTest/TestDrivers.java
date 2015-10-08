package CucumberTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class TestDrivers {
	private static WebDriver driver = null;

	public static void MouseHooverOverElement(WebDriver wdriver, WebElement we, String text){
		 Actions act = new Actions(driver);
		 act.moveToElement(we).build().perform();
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
		//wdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//wdriver.manage().window().maximize();
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