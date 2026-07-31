package project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity3 {

    AppiumDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
        File testApp = new File("src/test/resources/ToDo.apk");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setApp(testApp.getAbsolutePath());

        URL serverURL = new URI("http://localhost:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(dependsOnMethods = "testAddTask", priority = 3)
    public void testCompletedTask() {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("ListSpinnerCategory"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"All\"]")))
                .click();

        for (int i = 1; i <= 2; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("(//android.widget.CheckBox[@resource-id='com.xmission.trevin.android.todo:id/ToDoItemChecked'])[" + i + "]")))
                    .click();
        }

        driver.findElement(AppiumBy.id("menuShowCompleted")).click();

        String activityText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.xmission.trevin.android.todo:id/ToDoEditDescription\" and @text=\"Activity 3\"]")))
                .getText();

        System.out.println(activityText);

        Assert.assertEquals("Activity 3", activityText);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
