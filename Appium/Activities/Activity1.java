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
import java.util.List;

/**
 * NOTE ON LOCATORS: every findElement below is marked with // VERIFY.
 * Confirm the real resource-id / accessibility-id / text via Appium
 * Inspector once the ToDo app is installed - this app's exact ids
 * were not available to inspect while writing this file.
 */
public class Activity1 {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");          // VERIFY: adb devices
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", "com.example.todo");        // VERIFY: real package
        caps.setCapability("appium:appActivity", ".MainActivity");          // VERIFY: real launch activity
        caps.setCapability("appium:noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    private LocalDate nextWeekday(DayOfWeek day) {
        return LocalDate.now().with(TemporalAdjusters.next(day));
    }

    private void addTask(String title, int priority, LocalDate dueDate) {
        driver.findElement(AppiumBy.accessibilityId("New")).click();        // VERIFY: "New" button

        WebElement titleField = driver.findElement(AppiumBy.className("android.widget.EditText")); // VERIFY
        titleField.sendKeys(title);

        // VERIFY: priority selector - adjust if it's a spinner/dropdown rather than a numbered list
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Priority.*" + priority + "\")")).click();

        // VERIFY: due-date field opens a date picker
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Due date\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().description(\"" + dueDate.getDayOfMonth() + "\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)OK|Set\")")).click();

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)OK\")")).click();       // Save the task
    }

    @Test
    public void testAddThreeTasksWithPriorityAndDueDate() {
        LocalDate wednesday = nextWeekday(DayOfWeek.WEDNESDAY);
        LocalDate thursday = nextWeekday(DayOfWeek.THURSDAY);

        addTask("Complete Activity 1", 1, wednesday);
        addTask("Complete Activity 2", 2, wednesday);
        addTask("Complete Activity 3", 3, thursday);

        // VERIFY: adjust the resource-id to match the task title TextView
        List<WebElement> taskTitles = driver.findElements(
                AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*task_title.*\")"));

        Assert.assertEquals(taskTitles.size(), 3, "Expected exactly 3 tasks in the list");

        boolean[] found = new boolean[3];
        String[] expected = {"Complete Activity 1", "Complete Activity 2", "Complete Activity 3"};
        for (WebElement el : taskTitles) {
            for (int i = 0; i < expected.length; i++) {
                if (el.getText().contains(expected[i])) found[i] = true;
            }
        }
        for (int i = 0; i < expected.length; i++) {
            Assert.assertTrue(found[i], expected[i] + " was not found in the task list");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
