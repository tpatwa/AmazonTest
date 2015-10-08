package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import CucumberTest.SeleniumTest;
import CucumberTest.TestDrivers;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {

	public static WebDriver driver;

	@Given("^User is on Home Page \"([^\"]*)\" and using \"([^\"]*)\" browser$")
	public void user_is_on_Home_Page(String pageUrl, String browserName)
			throws Throwable {

		driver = TestDrivers.DriverInit(browserName);
		TestDrivers.GoToPage(driver, pageUrl);

	}

	@When("^User Navigate to LogIn Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		TestDrivers.DriverSleep(30000);
		String newValue = "Hello. Sign inYour AccountSign inYour Account";
		if (TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
				"Hello. Sign inYour AccountSign inYour Account", false) == null) {
			newValue = "Your Amazon.com";
			TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
					newValue, true);

		} else {
			TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
					newValue, true);
		}

		TestDrivers.clickElementByTagNameAndAttributeAndIndex(driver, "A",
				"text", "Hello. Sign inYour AccountSign inYour Account", 0);
		// SeleniumTest.clickElementByTagNameAndAttributeAndIndex(driver,
		// "SPAN",
		// "textContent", "Hello. Sign in", 0);

		TestDrivers.clickElementByTagNameAndAttributeAndIndex(driver, "SPAN",
				"textContent", "Sign in", 0);

	}

	@When("^User enters UserName \"([^\"]*)\" and Password \"([^\"]*)\"$")
	public void user_enters_UserName_and_Password(String userName,
			String password) throws Throwable {
		Thread.sleep(5000);
		TestDrivers.FindElementById(driver, "ap_email").sendKeys(userName);
		TestDrivers.FindElementById(driver, "ap_password").sendKeys(password);
		TestDrivers.FindElementById(driver, "signInSubmit-input").click();
	}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {

		System.out.println("Login Successfully");
	}

	@When("^User Navigate Amazon Video Page$")
	public void user_Navigate_VideoPage() {
		// Actions vact=new Actions(driver);
		// vact.moveToElement(driver.findElement(By.id("nav-link-shopall"))).build().perform();

		TestDrivers.DriverSleep(20000);
		String newValue = "Shop byDepartment";
		// if (TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
		// "Shop byDepartment", false) == null) {
		// newValue = "Explore Amazon";
		// TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
		// newValue, true);
		//
		// } else {
		// TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
		// newValue, true);
		// }
		WebElement we = null;
		we = TestDrivers.ClickByTagNameAndAttribute(driver, "A", "text",
				newValue, false);

		if (we == null) {
			newValue = "Explore Amazon";
			TestDrivers.MouseHooverOverElement(driver, we, "Shop byDepartment");

		} else {
			TestDrivers.MouseHooverOverElement(driver, we, newValue);

		}

		TestDrivers.clickElementByTagNameAndAttributeAndIndex(driver, "SPAN",
				"textContent", "Amazon Video", 0);

		TestDrivers.clickElementByTagNameAndAttributeAndIndex(driver, "SPAN",
				"textContent", "Amazon Video", 2);

	}

	@Then("^Message displayed \"([^\"]*)\"$")
	public void messageDisplayed(String messageDisplayed) {
		SeleniumTest.verifyElementByTagNameAndAttributeAndIndex(driver, "H1",
				"textContent", "\n		Amazon Video\n	", 0);
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		Thread.sleep(5000);
		// if (SeleniumTest.clickElementByTagNameAndAttributeAndIndex(driver,
		// "SPAN",
		// "textContent", "Your Account", 0) == null) {
		// SeleniumTest.clickElementByTagNameAndAttributeAndIndex(driver,
		// "SPAN",
		// "textContent", "Account & Lists", 0);
		//
		// }

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("nav-link-yourAccount")))
				.build().perform();
		TestDrivers.ClickByTagNameAndAttribute(driver, "SPAN", "textContent",
				"Not Adelaide? Sign Out", true);

	}

	@Then("^Logout Successfully$")
	public void message_displayed_Logout_Successfully()
			throws InterruptedException {
		Thread.sleep(5000);
		TestDrivers.verifyElementByTagNameAndAttributeAndIndex(driver, "INPUT",
				"id", "ap_email", 0);
		System.out.println("LogOut Successfully");
		Thread.sleep(5000);
		driver.quit();
	}

}