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
 * snapshot was available. The button locator (assumed text "Confirm
 * Alert") is marked // VERIFY. Design note: since accepting or
 * dismissing an alert closes it, demonstrating BOTH outcomes requires
 * triggering the confirm alert twice - once per button - rather than
 * reusing a single Alert instance for both actions.
 */
public class Activity19 {

    WebDriver driver;
    By confirmButton = By.xpath("//button[contains(translate(text(),'CONFIRM','confirm'),'confirm')]"); // VERIFY

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/alerts");
    }

    @Test
    public void testConfirmAlertAcceptThenDismiss() {
        System.out.println("Page title: " + driver.getTitle());

        // First round: trigger the confirm alert and close it with OK
        driver.findElement(confirmButton).click();
        Alert alert1 = driver.switchTo().alert();
        System.out.println("Confirm alert text (before OK): " + alert1.getText());
        alert1.accept();

        // Second round: trigger it again and close it with Cancel
        driver.findElement(confirmButton).click();
        Alert alert2 = driver.switchTo().alert();
        System.out.println("Confirm alert text (before Cancel): " + alert2.getText());
        alert2.dismiss();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
