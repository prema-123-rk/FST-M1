package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * NOTE: this slide is the vaguest of the set - "find the input fields
 * and type in the required data" doesn't say how many fields, what they
 * are, or what a submit action looks like. "Dynamic Attributes" is also
 * the name of a well-known unrelated Selenium exercise elsewhere (a
 * button whose id/class changes on every page load, to teach you to
 * locate it by a stable attribute instead) - so this page may not even
 * be a form. What's below is a generic, defensive best-effort: it finds
 * every <input> on the page and fills each based on its "type"
 * attribute, then looks for a submit button and a success message.
 * Strongly recommend opening the real page before trusting this file -
 * it's more a starting skeleton than a verified test.
 */
public class Activity15 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/webelements/dynamic-attributes");
    }

    private void fillField(WebElement input) {
        String type = input.getAttribute("type");
        if (type == null) type = "text";

        switch (type.toLowerCase()) {
            case "email":
                input.sendKeys("test@example.com");
                break;
            case "password":
                input.sendKeys("Password123!");
                break;
            case "tel":
                input.sendKeys("1234567890");
                break;
            case "number":
                input.sendKeys("123");
                break;
            case "checkbox":
            case "radio":
                if (!input.isSelected()) input.click();
                break;
            case "text":
            default:
                input.sendKeys("Test Value");
                break;
        }
    }

    @Test
    public void testFillDynamicFieldsAndWaitForSuccess() {
        System.out.println("Page title: " + driver.getTitle());

        List<WebElement> inputs = driver.findElements(By.tagName("input")); // VERIFY: form structure unknown
        System.out.println("Found " + inputs.size() + " input field(s)");

        for (WebElement input : inputs) {
            fillField(input);
        }

        // VERIFY: assumes a submit button exists - adjust text/locator once confirmed
        List<WebElement> submitButtons = driver.findElements(
                By.xpath("//button[@type='submit'] | //input[@type='submit'] | //button[contains(translate(text(),'SUBMIT','submit'),'submit')]"));
        if (!submitButtons.isEmpty()) {
            submitButtons.get(0).click();
        }

        // VERIFY: container for the success message - unknown without the real page
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//*[contains(translate(text(),'SUCCESS','success'),'success')]")));
        System.out.println("Success message: " + successMessage.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
