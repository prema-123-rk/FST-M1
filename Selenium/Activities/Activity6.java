package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: same page as Activity5 (dynamic-controls) - could not be fetched
 * directly (no cached search snapshot). Locators are best-guess and marked
 * // VERIFY - confirm with Chrome DevTools before running.
 */
public class Activity6 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
    }

    @Test
    public void testCheckboxSelectedStateAfterClick() {
        System.out.println("Page title: " + driver.getTitle());

        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']")); // VERIFY

        boolean selectedBefore = checkbox.isSelected();
        System.out.println("Checkbox selected before click: " + selectedBefore);

        checkbox.click();

        boolean selectedAfter = checkbox.isSelected();
        System.out.println("Checkbox selected after click: " + selectedAfter);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
