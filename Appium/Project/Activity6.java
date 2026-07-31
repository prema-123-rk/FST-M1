package project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import activities.ActionBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 {

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
        options.setCapability("appium:chromedriverAutodownload", true);
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
        driver.setSetting("enableMultiWindows", true);
        action = new ActionBase();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
            {"admin", "password", "Login Success!"},
            {"admin", "password1", "Invalid Credentials!"}
        };
    }

    @Test
    public void popupTest() throws InterruptedException {
        driver.get("https://training-support.net/webelements/");

        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));
        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));
        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("Popups Work with popups!"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.Button[@resource-id='launcher']"))).click();

        Thread.sleep(2000);

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")));
        username.click();
        username.clear();
        username.sendKeys("admin");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")));
        password.click();
        password.clear();
        password.sendKeys("password");

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.Button[@text='Submit']"))).click();

        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Login')]"))).getText();

        Assert.assertEquals(message, "Login Success!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
