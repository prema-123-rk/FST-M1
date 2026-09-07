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
import java.util.List;

/**
 * NOTE ON LOCATORS: every findElement below is marked with // VERIFY.
 * Confirm the real resource-id / accessibility-id / text via Appium
 * Inspector once ts-todo-list-v1.apk is installed - not available to
 * inspect while writing this file.
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

    private void addTask(String title, String priority) {
        driver.findElement(AppiumBy.accessibilityId("Add"))                 // VERIFY: add/FAB button
                .click();

        WebElement titleField = driver.findElement(AppiumBy.className("android.widget.EditText")); // VERIFY
        titleField.sendKeys(title);

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)" + priority + "\")")).click();  // VERIFY: priority selector

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Save\")")).click();
    }

    @Test
    public void testAddThreeTasksWithPriority() {
        addTask("Complete Activity 1", "High");
        addTask("Complete Activity 2", "Medium");
        addTask("Complete Activity 3", "Low");

        List<WebElement> taskTitles = driver.findElements(                  // VERIFY
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
