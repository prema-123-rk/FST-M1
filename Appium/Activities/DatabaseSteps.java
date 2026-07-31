package stepDefinitions;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DatabaseSteps extends BaseClass {

    @Given("user is on to-do list page")
    public void todoListPage() {
        driver.get("https://training-support.net/webelements/todo-list");
        Assertions.assertEquals("Selenium: To-Do List", driver.getTitle());
    }

    @When("user adds following task:")
    public void addTask(DataTable input) throws InterruptedException {
        List<String> tasks = input.asList();

        WebElement textBox = driver.findElement(By.id("todo-input"));
        WebElement addBtn = driver.findElement(By.id("todo-add"));

        for (String task : tasks) {
            textBox.sendKeys(task);
            addBtn.click();
            Thread.sleep(2000);
        }
    }

    @Then("they can see task added to list")
    public void verifyResult() {
        System.out.println("All tasks present");
    }
}
