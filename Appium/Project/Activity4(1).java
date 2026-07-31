package project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import activities.ActionBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {

    UiAutomator2Options options;
    AndroidDriver driver;
    ActionBase action;
    WebDriverWait wait;

    @BeforeClass
    public void setUP() throws MalformedURLException, URISyntaxException {
        options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
        action = new ActionBase();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAddTask() {
        String[] tasks = {
                "Add tasks to list",
                "Get number of tasks",
                "Clear the list"
        };

        driver.get("https://training-support.net/webelements/");

        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));
        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));
        action.scroll(driver, 950, new Point(581, 2295), new Point(581, 408));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("To-Do List Elements get added at runtime!"))).click();

        for (String task : tasks) {
            driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]")).sendKeys(task);
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();
        }

        for (int i = 3; i <= 5; i++) {
            driver.findElement(AppiumBy.xpath("//android.widget.ListView/android.view.View[" + i + "]/android.view.View/android.widget.CheckBox")).click();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
