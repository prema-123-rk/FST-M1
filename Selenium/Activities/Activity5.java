package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: same limitation as Activity 4 - I could not retrieve the actual
 * page content for https://training-support.net/webelements/dynamic-controls
 * (no cached search snapshot, and web_fetch requires a previously-seen URL).
 * This mirrors the well-known "Dynamic Controls" Selenium practice exercise
 * (toggling a checkbox's visibility in the DOM), so the structure below is a
 * reasonable best guess, but every locator is marked // VERIFY - confirm
 * with Chrome DevTools before running.
 */
public class Activity5 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
    }

    @Test
    public void testCheckboxVisibilityAfterToggle() {
        System.out.println("Page title: " + driver.getTitle());

        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']")); // VERIFY
        boolean visibleBefore = checkbox.isDisplayed();
        System.out.println("Checkbox visible before toggle: " + visibleBefore);

        WebElement toggleButton = driver.findElement(By.xpath("//button[normalize-space()='Toggle Checkbox']")); // VERIFY
        toggleButton.click();

        // Re-locate after the toggle, since the element may have been removed/re-added to the DOM
        // rather than just hidden - a stale reference to the old element would throw here.
        boolean visibleAfter;
        try {
            WebElement checkboxAfter = driver.findElement(By.cssSelector("input[type='checkbox']")); // VERIFY
            visibleAfter = checkboxAfter.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            visibleAfter = false; // removed from the DOM entirely counts as "not visible"
        }
        System.out.println("Checkbox visible after toggle: " + visibleAfter);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
