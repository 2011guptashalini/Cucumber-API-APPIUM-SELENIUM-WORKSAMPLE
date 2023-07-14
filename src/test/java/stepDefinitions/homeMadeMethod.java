package stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Utils;

public class homeMadeMethod extends Utils {
	
	@SuppressWarnings("deprecation")
	@Given("User has launched the app and signin page displayed")
	public void user_has_launched_the_app_and_signin_page_displayed() throws IOException {
		driver = getMobileDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(AppiumBy.accessibilityId(getLocator("turn_on_notification"))).click();
		driver.findElement(By.id(getLocator("allow"))).click();
		driver.findElement(AppiumBy.accessibilityId(getLocator("me"))).click();
		driver.findElement(AppiumBy.accessibilityId(getLocator("login_link"))).click();
		
	}
	@SuppressWarnings("deprecation")
	@When("user provide correct {string} and {string}")
	public void user_provide_correct_and(String email, String password) throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElements(AppiumBy.className(getLocator("email_password"))).get(0).clear();
		driver.findElements(AppiumBy.className(getLocator("email_password"))).get(0).sendKeys("s");
		//driver.findElements(By.className(getLocator("email_password"))).get(0).sendKeys("s");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElements(By.className(getLocator("email_password"))).get(1).sendKeys("s");
		driver.findElements(AppiumBy.className(getLocator("email_password"))).get(1).sendKeys(email);
	    
	}
	@Then("click on signin button")
	public void click_on_signin_button() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(AppiumBy.accessibilityId(getLocator("login"))).click();
	}
	@Then("plans page opens")
	public void plans_page_opens() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(AppiumBy.accessibilityId(getLocator("plans"))).isDisplayed();
	}

}
