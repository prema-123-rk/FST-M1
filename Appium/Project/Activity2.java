package appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

/**
 * NOTE ON LOCATORS: every findElement below is marked with // VERIFY.
 * "next Saturday" is computed relative to the machine running the test,
 * not hardcoded, so the assertion stays correct on any run date.
 */
public class Activity2 {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", "com.example.todo");        // VERIFY
        caps.setCapability("appium:appActivity", ".MainActivity");          // VERIFY
        caps.setCapability("appium:noReset", true);                        // keep the 3 tasks from Activity1

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void testEditFirstTaskSetDeadline() {
        LocalDate targetDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        WebElement firstTask = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().resourceIdMatches(\".*task_item.*\").instance(0)"));  // VERIFY

        driver.executeScript("mobile: longClickGesture", Map.of(
                "elementId", firstTask.getId(), "duration", 1000));

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Deadline|Due date\")")).click();     // VERIFY

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().description(\"" + targetDate.format(
                        java.time.format.DateTimeFormatter.ofPattern("EEEE, MMMM d")) + "\")")).click(); // VERIFY

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)OK|Set\")")).click();

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Save\")")).click();

        String expectedDateStr = targetDate.format(
                java.time.format.DateTimeFormatter.ofPattern("MMM d"));    // e.g. "Sep 12" - match app's format

        boolean deadlineShown = !driver.findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + expectedDateStr + "\")")).isEmpty();

        Assert.assertTrue(deadlineShown,
                "Expected deadline '" + expectedDateStr + "' to be visible on the task after saving");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
