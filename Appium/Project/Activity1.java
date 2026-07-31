package project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity1 {

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

    @DataProvider(name = "taskData")
    public Object[][] task() {
        return new Object[][]{
            {"Activity 1","1","Wednesday"},
            {"Activity 2","2","Wednesday"},
            {"Activity 3","3","Thursday"}
        };
    }

    @Test(dataProvider = "taskData")
    public void testAddTask(String taskDesc, String priority, String day) {
        driver.findElement(AppiumBy.id("ListButtonNew")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("DetailEditTextDescription"))).sendKeys(taskDesc);

        WebElement setPriority = driver.findElement(AppiumBy.id("DetailEditTextPriority"));
        setPriority.clear();
        setPriority.sendKeys(priority);

        driver.findElement(AppiumBy.id("DetailButtonDueDate")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and contains(@text,'" + day + "')]"))).click();

        driver.findElement(AppiumBy.id("DetailButtonOK")).click();

        String activityText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='com.xmission.trevin.android.todo:id/ToDoEditDescription' and contains(@text,'" + taskDesc + "')]"))).getText();

        Assert.assertEquals(taskDesc, activityText);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
