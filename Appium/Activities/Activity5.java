package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * NOTE: the login-form page could not be fetched directly (server returned
 * a 405 on a plain GET), so the exact input ids are unconfirmed. The
 * // VERIFY locators below use common patterns (id="username"/"password")
 * as a starting point - confirm with Chrome DevTools before running,
 * and adjust the success/failure message text to match what actually
 * renders (the slide's screenshots weren't included as text).
 */
public class Activity5 {

    AndroidDriver driver;
    private static final String URL_LOGIN = "https://training-support.net/webelements/login-form";

    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.get(URL_LOGIN);
    }

    private void login(String username, String password) {
        driver.findElement(By.xpath("//input[@id='username' or @name='username']"))  // VERIFY
                .sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password' or @name='password']"))  // VERIFY
                .sendKeys(password);
        driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();       // VERIFY
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        login("admin", "password");

        WebElement message = driver.findElement(By.xpath(
                "//*[contains(text(),'Success') or contains(text(),'success')]"));   // VERIFY: exact success text
        Assert.assertTrue(message.isDisplayed(), "Expected success message to be displayed");
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        login("admin", "wrongpassword");

        WebElement message = driver.findElement(By.xpath(
                "//*[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'Error')]")); // VERIFY
        Assert.assertTrue(message.isDisplayed(), "Expected error message to be displayed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
