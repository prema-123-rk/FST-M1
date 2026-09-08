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
 * NOTE: same page as Activity5/6/7 (dynamic-controls) - could not be
 * fetched directly (no cached search snapshot). Locators are best-guess
 * and marked // VERIFY - confirm with Chrome DevTools before running.
 * Design note: the checkbox is actually removed/re-added to the DOM by
 * the "Toggle Checkbox" button (not just hidden), so
 * invisibilityOfElementLocated is used rather than a plain
 * isDisplayed() check - it correctly treats "not present at all" as
 * satisfying the wait, whereas isDisplayed() would throw a
 * NoSuchElementException once the element is gone.
 */
public class Activity8 {

    WebDriver driver;
    WebDriverWait wait;

    private static final By CHECKBOX = By.cssSelector("input[type='checkbox']"); // VERIFY
    private static final By TOGGLE_BUTTON = By.xpath("//button[normalize-space()='Toggle Checkbox']"); // VERIFY

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/webelements/dynamic-controls");
    }

    @Test
    public void testWaitForCheckboxToggle() {
        System.out.println("Page title: " + driver.getTitle());

        // Confirm the checkbox is present before we start
        wait.until(ExpectedConditions.presenceOfElementLocated(CHECKBOX));

        // Click "Toggle Checkbox" to remove it
        driver.findElement(TOGGLE_BUTTON).click();

        // Wait for the checkbox to disappear (removed from DOM or hidden - either satisfies this)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX));
        System.out.println("Checkbox disappeared after first toggle");

        // Toggle again to bring it back
        driver.findElement(TOGGLE_BUTTON).click();

        // Wait for it to reappear, then select it
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKBOX));
        System.out.println("Checkbox reappeared after second toggle");

        checkbox.click();
        System.out.println("Checkbox selected: " + checkbox.isSelected());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
