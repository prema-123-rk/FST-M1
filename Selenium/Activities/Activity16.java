package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/selects - no cached search
 * snapshot was available. The locator for the single-select dropdown
 * (assumed to be the first/only <select> on the page) is marked
 * // VERIFY - confirm the real id via Chrome DevTools before running,
 * especially since the slide implies a second "multi select" dropdown
 * exists too (Selects #1 vs #2), which could make a plain "first
 * select on the page" locator ambiguous.
 */
public class Activity16 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/selects");
    }

    @Test
    public void testSingleSelectDropdown() {
        System.out.println("Page title: " + driver.getTitle());

        WebElement selectElement = driver.findElement(By.tagName("select")); // VERIFY
        Select singleSelect = new Select(selectElement);

        singleSelect.selectByVisibleText(singleSelect.getOptions().get(1).getText()); // 2nd option, by visible text
        System.out.println("Selected (by visible text): " + singleSelect.getFirstSelectedOption().getText());

        singleSelect.selectByIndex(2); // 3rd option, 0-indexed
        System.out.println("Selected (by index): " + singleSelect.getFirstSelectedOption().getText());

        String fourthOptionValue = singleSelect.getOptions().get(3).getAttribute("value");
        singleSelect.selectByValue(fourthOptionValue); // 4th option, by value attribute
        System.out.println("Selected (by value): " + singleSelect.getFirstSelectedOption().getText());

        List<WebElement> allOptions = singleSelect.getOptions();
        System.out.println("All options in the dropdown:");
        for (WebElement option : allOptions) {
            System.out.println(" - " + option.getText());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
