package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/keyboard-events - no cached
 * search snapshot was available. Locators below are best-guesses marked
 * // VERIFY - confirm the real input field and message container via
 * Chrome DevTools before running.
 */
public class Activity11 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/keyboard-events");
    }

    @Test
    public void testTypeStringAndPrintMessage() {
        System.out.println("Page title: " + driver.getTitle());

        String inputText = "Hello from Selenium!";

        WebElement inputField = driver.findElement(By.cssSelector("input[type='text']")); // VERIFY
        inputField.sendKeys(inputText);

        // VERIFY: the element the page uses to echo back what was typed - adjust
        // once the real container is confirmed via DevTools. If the input field
        // itself just holds the value (no separate display element), read
        // inputField.getAttribute("value") instead.
        WebElement message = driver.findElement(By.id("message"));
        System.out.println("Message displayed on page: " + message.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
