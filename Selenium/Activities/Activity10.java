package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/mouse-events - no cached
 * search snapshot was available, so the button locators, the "confirmation
 * text" container, and the right-click context menu's "open" option are
 * all best-guesses marked // VERIFY. Confirm the real structure via
 * Chrome DevTools before running - this file demonstrates the correct
 * Actions-class sequences (click -> hover -> click, doubleClick,
 * contextClick -> menu item click) more than it guarantees working
 * locators.
 */
public class Activity10 {

    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.get("https://training-support.net/webelements/mouse-events");
    }

    @Test
    public void testClickHoverClickSequence() {
        System.out.println("Page title: " + driver.getTitle());

        WebElement cargoLock = driver.findElement(By.xpath("//button[normalize-space()='Cargo.lock']")); // VERIFY
        WebElement cargoToml = driver.findElement(By.xpath("//button[normalize-space()='Cargo.toml']")); // VERIFY

        actions.click(cargoLock)
                .moveToElement(cargoToml)
                .click(cargoToml)
                .perform();

        // VERIFY: locator for wherever the page prints its confirmation text
        WebElement confirmation = driver.findElement(By.id("confirmation"));
        System.out.println("Confirmation text (click -> hover -> click): " + confirmation.getText());
    }

    @Test(dependsOnMethods = "testClickHoverClickSequence")
    public void testDoubleClickAndRightClickSequence() {
        WebElement src = driver.findElement(By.xpath("//button[normalize-space()='src']"));       // VERIFY
        WebElement target = driver.findElement(By.xpath("//button[normalize-space()='target']")); // VERIFY

        actions.doubleClick(src).perform();
        actions.contextClick(target).perform();

        // VERIFY: the "open" option in whatever context menu appears after the right-click
        WebElement openMenuItem = driver.findElement(By.xpath("//*[normalize-space()='Open' or normalize-space()='open']"));
        openMenuItem.click();

        // VERIFY: same confirmation container as the first test - adjust if this
        // sequence writes to a different element
        WebElement confirmation = driver.findElement(By.id("confirmation"));
        System.out.println("Confirmation text (double-click -> right-click -> open): " + confirmation.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
