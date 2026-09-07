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
        caps.setCapability("appium:noReset", true);                        // keep the 3 tasks from Activity1

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void testMarkTasksCompleteAndToggleView() {
        // Mark the first two tasks complete
        List<WebElement> checkboxes = driver.findElements(
                AppiumBy.className("android.widget.CheckBox"));            // VERIFY: checkbox per task row
        Assert.assertTrue(checkboxes.size() >= 2, "Expected at least 2 checkboxes");
        checkboxes.get(0).click();
        checkboxes.get(1).click();

        // Toggle the "show completed" icon
        driver.findElement(AppiumBy.accessibilityId("Toggle completed"))    // VERIFY: exact icon content-desc
                .click();

        List<WebElement> visibleTasks = driver.findElements(
                AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*task_title.*\")"));

        // With completed tasks hidden, only the 1 remaining incomplete task should show
        Assert.assertEquals(visibleTasks.size(), 1,
                "Expected only the non-completed task to be visible after toggling");
        Assert.assertTrue(visibleTasks.get(0).getText().contains("Complete Activity 3"),
                "Expected the remaining visible task to be 'Complete Activity 3'");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
