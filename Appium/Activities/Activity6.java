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

/**
 * NOTE: confirmed via direct fetch that https://training-support.net/webelements/popups
 * has a single "Click the button!" trigger and an emoji reveal on click,
 * but the popup's login-form markup is JS-rendered and wasn't visible in a
 * static fetch. // VERIFY locators against Chrome DevTools once the popup
 * is open on a real device.
 */
public class Activity6 {

    AndroidDriver driver;
    private static final String URL_POPUPS = "https://training-support.net/webelements/popups";

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.get(URL_POPUPS);
    }

    @Test
    public void testPopupLoginWithCorrectCredentials() {
        driver.findElement(By.xpath("//button")).click();          // VERIFY: "Click the button!" trigger

        driver.findElement(By.xpath("//input[@id='username' or @name='username']"))  // VERIFY
                .sendKeys("admin");
        driver.findElement(By.xpath("//input[@id='password' or @name='password']"))  // VERIFY
                .sendKeys("password");
        driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();       // VERIFY

        WebElement message = driver.findElement(By.xpath(
                "//*[contains(text(),'Success') or contains(text(),'success')]"));    // VERIFY: exact success text
        Assert.assertTrue(message.isDisplayed(), "Expected success message to be displayed in the popup");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
