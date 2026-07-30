package selenium_project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity6 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/");
    }

    @Test
    public void testAccountsMenu() {
        driver.get("https://crm.alchemy.hguy.co");

        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='Username']")));

        WebElement password = driver.findElement(
                By.xpath("//input[@placeholder='Password']"));

        username.sendKeys("admin");
        password.sendKeys("5Nx#I6BK%r3$8vz0ch");

        driver.findElement(By.id("login-button")).click();

        WebElement accounts = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='Accounts']")));

        Assert.assertTrue(accounts.isDisplayed(), "Accounts menu not displayed!");

        System.out.println("Accounts menu is displayed.");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
