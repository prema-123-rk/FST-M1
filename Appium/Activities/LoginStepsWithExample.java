package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepsWithExample extends BaseClass {

    @Given("the user is present on the login page")
    public void openPage() {
        driver.get("https://training-support.net/webelements/login-form");
        Assertions.assertEquals("Selenium: Login Form", driver.getTitle());
    }

    @When("the user enters the {string} and {string}")
    public void enterCredentials(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.clear();
        passwordField.clear();

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @And("clicks the login button")
    public void clickSubmit() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("get the confirmation text message and verify it as {string}")
    public void getMessage(String expectedMessage) {
        String message = "";

        if (expectedMessage.contains("Invalid")) {
            message = driver.findElement(By.id("subheading")).getText();
        } else {
            wait.until(ExpectedConditions.titleContains("Success!"));
            message = driver.findElement(By.cssSelector("h2.mt-5")).getText();
        }

        Assertions.assertEquals(message, expectedMessage);
    }
}
