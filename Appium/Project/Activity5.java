package project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Point;
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

public class Activity5 {

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

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"admin", "password", "Login Success!"},
                {"admin", "password1", "Invalid Credentials!"}
        };
    }

    @Test
    public void loginSuccessTest() {
        driver.get("https://training-support.net/webelements/");

        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));
        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("Login Form Please sign in!"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]"))).sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).sendKeys("password");

        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();

        String displayMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@text=\"Login Success!\"]"))).getText();

        Assert.assertEquals(displayMessage, "Login Success!");
    }

    @Test
    public void InvalidTest() {
        driver.get("https://training-support.net/webelements/");

        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));
        action.scroll(driver, 950, new Point(581, 2304), new Point(581, 408));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("Login Form Please sign in!"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]"))).sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).sendKeys("password1");

        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();

        String displayMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@resource-id=\"subheading\"]"))).getText();

        Assert.assertEquals(displayMessage, "Invalid credentials");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
