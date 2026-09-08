package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/tabs - no cached search
 * snapshot was available. The button and "message" locators are
 * marked // VERIFY. The window-handle logic itself
 * (ExpectedConditions.numberOfWindowsToBe, switchTo().window(handle))
 * is standard Selenium API and doesn't depend on page-specific
 * details.
 */
public class Activity21 {

    WebDriver driver;
    WebDriverWait wait;

    private static final By NEW_TAB_BUTTON = By.xpath("//button[contains(translate(text(),'OPEN','open'),'open')]"); // VERIFY
    private static final By MESSAGE = By.id("message"); // VERIFY

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/webelements/tabs");
    }

    /**
     * Clicks the "open new tab" button on the current page, waits for a new
     * window handle to appear, switches focus to it, and returns its handle.
     */
    private String openAndSwitchToNewTab(String currentHandle) {
        Set<String> handlesBefore = driver.getWindowHandles();
        int expectedCount = handlesBefore.size() + 1;

        driver.findElement(NEW_TAB_BUTTON).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(expectedCount));

        Set<String> handlesAfter = driver.getWindowHandles();
        System.out.println("All window handles: " + handlesAfter);

        String newHandle = handlesAfter.stream()
                .filter(h -> !h.equals(currentHandle))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find a new window handle"));

        driver.switchTo().window(newHandle);
        return newHandle;
    }

    @Test
    public void testOpenSwitchAndRepeatAcrossTabs() {
        System.out.println("Page title: " + driver.getTitle());

        String originalHandle = driver.getWindowHandle();

        // First new tab
        String secondHandle = openAndSwitchToNewTab(originalHandle);
        System.out.println("New tab title: " + driver.getTitle());
        System.out.println("New tab message: " + driver.findElement(MESSAGE).getText());

        // Repeat: click the button again, this time from within the new tab
        String thirdHandle = openAndSwitchToNewTab(secondHandle);
        System.out.println("Third tab title: " + driver.getTitle());
        System.out.println("Third tab message: " + driver.findElement(MESSAGE).getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit(); // quit() rather than close() - closes ALL open tabs from this session
    }
}
