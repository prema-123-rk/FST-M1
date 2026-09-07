package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

/**
 * Confirmed via direct page fetch: https://training-support.net/webelements/todo-list
 * ships with 2 pre-existing tasks ("Buy Milk", "Buy Cat"). Exact ids/classes
 * for the JS-rendered widget were not visible from a static fetch - confirm
 * the // VERIFY locators with Chrome DevTools (Inspect) before running.
 */
public class Activity4 {

    AndroidDriver driver;
    private static final String URL_TODO = "https://training-support.net/webelements/todo-list";
    private static final String[] NEW_TASKS = {
            "Add tasks to list", "Get number of tasks", "Clear the list"
    };
    private static final int PRE_EXISTING_TASKS = 2;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.get(URL_TODO);
    }

    @Test
    public void testAddAndStrikeOutTasks() {
        // VERIFY: input placeholder text may differ slightly
        WebElement input = driver.findElement(By.xpath(
                "//input[contains(@placeholder,'to do') or contains(@placeholder,'task')]"));

        for (String task : NEW_TASKS) {
            input.clear();
            input.sendKeys(task);
            List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(.,'Add')]"));
            if (!addButtons.isEmpty()) {
                addButtons.get(0).click();
            } else {
                input.sendKeys(org.openqa.selenium.Keys.ENTER);
            }
        }

        List<WebElement> allTasks = driver.findElements(By.xpath("//li"));   // VERIFY: list item locator
        int expectedTotal = PRE_EXISTING_TASKS + NEW_TASKS.length;
        Assert.assertEquals(allTasks.size(), expectedTotal,
                "Expected " + expectedTotal + " tasks total");

        // Click each new task to strike it out
        for (String task : NEW_TASKS) {
            driver.findElement(By.xpath("//li[contains(.,'" + task + "')]")).click();
        }

        for (String task : NEW_TASKS) {
            WebElement item = driver.findElement(By.xpath("//li[contains(.,'" + task + "')]"));
            String style = (item.getAttribute("style") == null ? "" : item.getAttribute("style"))
                    + " " + (item.getAttribute("class") == null ? "" : item.getAttribute("class"));
            Assert.assertTrue(
                    style.contains("line-through") || style.toLowerCase().contains("complete")
                            || style.toLowerCase().contains("done"),
                    "Expected '" + task + "' to appear struck-through, got style/class: " + style);
        }

        List<WebElement> allTasksAfter = driver.findElements(By.xpath("//li"));
        Assert.assertEquals(allTasksAfter.size(), expectedTotal,
                "Task count changed after striking items out");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
