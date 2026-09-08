package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/selects - no cached search
 * snapshot was available. select[multiple] is a reliable CSS attribute
 * selector for the multi-select dropdown specifically (as opposed to
 * Activity16's single-select, which should use
 * select:not([multiple]) to avoid ambiguity - see the note left there).
 */
public class Activity17 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/selects");
    }

    @Test
    public void testMultiSelectDropdown() {
        System.out.println("Page title: " + driver.getTitle());

        WebElement selectElement = driver.findElement(By.cssSelector("select[multiple]")); // VERIFY
        Select multiSelect = new Select(selectElement);
        Assert.assertTrue(multiSelect.isMultiple(), "Expected this <select> to support multiple selection");

        multiSelect.selectByVisibleText("HTML");

        // 4th, 5th, 6th options - 0-indexed as 3, 4, 5
        multiSelect.selectByIndex(3);
        multiSelect.selectByIndex(4);
        multiSelect.selectByIndex(5);

        // "Node" option, by value attribute - looked up dynamically rather than
        // hardcoding the value string, since the actual value attribute is unknown
        List<WebElement> options = multiSelect.getOptions();
        String nodeValue = null;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase("Node")) {
                nodeValue = option.getAttribute("value");
                break;
            }
        }
        if (nodeValue == null) {
            throw new IllegalStateException("Could not find an option with visible text 'Node'");
        }
        multiSelect.selectByValue(nodeValue);

        System.out.println("Selected options after all selections: ");
        for (WebElement selected : multiSelect.getAllSelectedOptions()) {
            System.out.println(" - " + selected.getText());
        }

        // Deselect the 5th option (index 4)
        multiSelect.deselectByIndex(4);

        System.out.println("Selected options after deselecting the 5th: ");
        for (WebElement selected : multiSelect.getAllSelectedOptions()) {
            System.out.println(" - " + selected.getText());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
