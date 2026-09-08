package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TSHomepageSteps extends BaseClass{
	
	@Given("user is on the TS homepage")
	public void getTitle() {
	 driver.get("https://training-support.net");	
		
	 Assertions.assertEquals("Training Support" , driver.getTitle());
	}
	
	@When("the user clicks on the About Us link")
	public void clickAboutUs() {
		driver.findElement(By.linkText("About Us")).click();
	}
	
	
	@Then("they are redirected to another page")
	public void verifyPage() {
		wait.until(ExpectedConditions.titleContains("About"));
		
		String pageHeading = driver.findElement(By.cssSelector("h1.text-center")).getText();
		System.out.println("New page title is: " + pageHeading);
		
		Assertions.assertEquals("About Training Support" , driver.getTitle());
	}
}
