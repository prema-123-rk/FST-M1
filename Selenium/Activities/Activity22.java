package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * NOTE: confirmed earlier in this session (via a direct page fetch) that
 * https://training-support.net/webelements/popups has a single "Click the
 * button!" trigger with no visible login form until it's launched - the
 * popup's markup is rendered by JS, so the field/button locators below
 * are still best-guesses marked // VERIFY. Confirm via Chrome DevTools
 * (Inspect) once the popup is open.
 */
public class Activity22 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/webelements/popups");
    }

    @Test
    public void testPopupLoginWithCredentials() {
        System.out.println("Page title: " + driver.getTitle());

        driver.findElement(By.xpath("//button")).click(); // VERIFY: launches the popup

        // Wait for the popup's username field to actually appear before interacting with it
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='username' or @name='username']"))); // VERIFY
        usernameField.sendKeys("admin");

        WebElement passwordField = driver.findElement(
                By.xpath("//input[@id='password' or @name='password']")); // VERIFY
        passwordField.sendKeys("password");

        driver.findElement(By.xpath("//button[contains(translate(text(),'SUBMIT','submit'),'submit')]")) // VERIFY
                .click();

        // VERIFY: container for the post-login message - unknown without the real page
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(translate(text(),'SUCCESS','success'),'success')]")));
        System.out.println("Message after login: " + message.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
