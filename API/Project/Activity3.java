package appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * NOTE ON LOCATORS: every findElement below is marked with // VERIFY.
 * Relies on Activity1's 3 tasks already existing (appium:noReset keeps them).
 */
public class Activity3 {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", "com.example.todo");        // VERIFY
        caps.setCapability("appium:appActivity", ".MainActivity");          // VERIFY
        caps.setCapability("appium:noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void testMarkTasksCompleteAndVerifyCompletedList() {
        // Mark the first two tasks complete
        List<WebElement> checkboxes = driver.findElements(
                AppiumBy.className("android.widget.CheckBox"));            // VERIFY: checkbox per task row
        Assert.assertTrue(checkboxes.size() >= 2, "Expected at least 2 checkboxes");
        checkboxes.get(0).click();
        checkboxes.get(1).click();

        // Long press the third task to edit it and set progress to 50%
        WebElement thirdTask = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().resourceIdMatches(\".*task_item.*\").instance(2)"));  // VERIFY

        driver.executeScript("mobile: longClickGesture", Map.of(
                "elementId", thirdTask.getId(), "duration", 1000));

        WebElement progressBar = driver.findElement(AppiumBy.className("android.widget.SeekBar")); // VERIFY
        Rectangle rect = progressBar.getRect();
        int endX = rect.getX() + rect.getWidth() / 2;
        int midY = rect.getY() + rect.getHeight() / 2;

        driver.executeScript("mobile: dragGesture", Map.of(
                "elementId", progressBar.getId(), "endX", endX, "endY", midY));

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Save\")")).click();

        // Open the options/overflow menu and select "Completed tasks"
        driver.findElement(AppiumBy.accessibilityId("More options")).click();  // VERIFY
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Completed tasks\")")).click();

        List<WebElement> completedItems = driver.findElements(
                AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*task_item.*\")")); // VERIFY

        // The 50%-progress task should NOT count as completed - only the first two do
        Assert.assertEquals(completedItems.size(), 2,
                "Expected exactly 2 completed tasks, got " + completedItems.size());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
