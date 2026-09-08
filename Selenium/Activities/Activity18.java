package selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/alerts - no cached search
 * snapshot was available. The button locator (assumed text "Simple
 * Alert") is marked // VERIFY - confirm via Chrome DevTools before
 * running. The alert-handling logic itself (switchTo().alert(),
 * getText(), accept()) is standard Selenium API and doesn't depend on
 * page-specific details.
 */
public class Activity18 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/alerts");
    }

    @Test
    public void testSimpleAlertTextAndAccept() {
        System.out.println("Page title: " + driver.getTitle());

        driver.findElement(By.xpath("//button[contains(translate(text(),'SIMPLE','simple'),'simple')]")) // VERIFY
                .click();

        // Switch focus from the main window to the alert box
        Alert alert = driver.switchTo().alert();

        System.out.println("Alert text: " + alert.getText());

        // Close the alert with OK
        alert.accept();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
