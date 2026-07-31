package stepDefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlertSteps extends BaseClass {

    private Alert alert;

    @Given("User is on the page")
    public void getPage() {
        driver.get("https://training-support.net/webelements/alerts");
    }

    @When("User clicks the Simple Alert button")
    public void clickSimpleAlert() {
        driver.findElement(By.id("simple")).click();
    }

    @When("User clicks the Confirm Alert button")
    public void clickConfirmAlert() {
        driver.findElement(By.id("confirmation")).click();
    }

    @When("User clicks the Prompt Alert button")
    public void clickPromptAlert() {
        driver.findElement(By.id("prompt")).click();
    }

    @Then("Alert opens")
    public void openAlert() {
        alert = driver.switchTo().alert();
    }

    @And("Read the text from it and print it")
    public void readAlertText() {
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);
    }

    @And("Write a custom message in it")
    public void writeAlertMsg() {
        alert.sendKeys("Message");
    }

    @And("Close the alert")
    public void closeAlert() {
        alert.accept();
    }

    @And("Close the alert with Cancel")
    public void cancelAlert() {
        alert.dismiss();
    }

    @And("Read the result text")
    public void readResultText() {
        String resultText = driver.findElement(By.id("result")).getText();
        System.out.println("Result text is: " + resultText);
    }
}
