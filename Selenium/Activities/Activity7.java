package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: same page as Activity5/Activity6 (dynamic-controls) - could not be
 * fetched directly (no cached search snapshot). Locators are best-guess and
 * marked // VERIFY - confirm with Chrome DevTools before running.
 */
public class Activity7 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
    }

    @Test
    public void testTextFieldEnabledStateAfterClick() {
        System.out.println("Page title: " + driver.getTitle());

        WebElement textField = driver.findElement(By.cssSelector("input[type='text']")); // VERIFY

        boolean enabledBefore = textField.isEnabled();
        System.out.println("Text field enabled before click: " + enabledBefore);

        WebElement enableButton = driver.findElement(By.xpath("//button[normalize-space()='Enable Input']")); // VERIFY
        enableButton.click();

        boolean enabledAfter = textField.isEnabled();
        System.out.println("Text field enabled after click: " + enabledAfter);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
