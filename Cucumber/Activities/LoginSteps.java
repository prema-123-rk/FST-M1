package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseClass {

    @Given("the user is on the login page")
    public void loginPage() {
        driver.get("https://training-support.net/webelements/login-form");
        Assertions.assertEquals("Selenium: Login Form", driver.getTitle());
    }

    @When("the user enters username and password")
    public void loginForm() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("admin");
        password.sendKeys("password");

        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @And("clicks the submit button")
    public void formSubmitBtn() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("get the confirmation message and verify it")
    public void getConfirmation() {
        wait.until(ExpectedConditions.titleContains("Success!"));

        String message = driver.findElement(By.cssSelector("h2.mt-5")).getText();

        Assertions.assertEquals("Welcome Back, Admin!", message);
    }
}
