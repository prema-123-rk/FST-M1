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
 * NOTE: locators marked // VERIFY need confirming with Appium Inspector
 * against the real ToDo app - not available to inspect while writing this.
 */
public class Activity2 {

    AndroidDriver driver;
    private static final String NEW_CATEGORY = "Work";       // TODO: rename if a specific category name is required
    private static final String SECOND_TASK_TITLE = "Complete Activity 2";

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
    public void testAddCategoryAndAssignToTask() {
        // Open the dropdown / overflow menu and select "Edit categories"
        driver.findElement(AppiumBy.accessibilityId("More options")).click();   // VERIFY: dropdown/menu icon
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Edit categories\")")).click();

        // Add the new category
        driver.findElement(AppiumBy.accessibilityId("New")).click();            // VERIFY: "New" button
        driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(NEW_CATEGORY);
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)OK\")")).click();

        // Long press the second task to edit it
        WebElement secondTask = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + SECOND_TASK_TITLE + "\")"));  // VERIFY
        driver.executeScript("mobile: longClickGesture", java.util.Map.of(
                "elementId", secondTask.getId(), "duration", 1000));

        // Assign the new category to this task
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Category\")")).click();     // VERIFY: category field/dropdown
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + NEW_CATEGORY + "\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)OK\")")).click();

        // Verify via the filter dropdown
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textMatches(\"(?i)Filter|Categor(y|ies)\")")).click(); // VERIFY: filter dropdown
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + NEW_CATEGORY + "\")")).click();

        List<WebElement> filteredTasks = driver.findElements(
                AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*task_title.*\")"));

        Assert.assertEquals(filteredTasks.size(), 1, "Expected exactly 1 task under the new category");
        Assert.assertTrue(filteredTasks.get(0).getText().contains(SECOND_TASK_TITLE),
                "Expected the filtered task to be '" + SECOND_TASK_TITLE + "'");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
