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
 * snapshot was available. The button locator (assumed text "Prompt
 * Alert") is marked // VERIFY. alert.sendKeys(...) is the standard
 * Selenium API for typing into a prompt dialog's input field before
 * accepting it.
 */
public class Activity20 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/alerts");
    }

    @Test
    public void testPromptAlertTypeAndAccept() {
        System.out.println("Page title: " + driver.getTitle());

        driver.findElement(By.xpath("//button[contains(translate(text(),'PROMPT','prompt'),'prompt')]")) // VERIFY
                .click();

        Alert alert = driver.switchTo().alert();
        System.out.println("Prompt alert text: " + alert.getText());

        alert.sendKeys("Awesome!");
        alert.accept();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
