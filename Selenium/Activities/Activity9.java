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

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/dynamic-content - no cached
 * search snapshot was available. This mirrors the classic "Dynamic
 * Loading" Selenium exercise (click a button, wait for content to load
 * asynchronously, then read the result). The container locator and the
 * exact word "release" are best-guesses marked // VERIFY - confirm the
 * real element and wording via Chrome DevTools before running.
 */
public class Activity9 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/webelements/dynamic-content");
    }

    @Test
    public void testWaitForDynamicContentToLoad() {
        System.out.println("Page title: " + driver.getTitle());

        driver.findElement(By.xpath("//button[normalize-space()='Click me!']")).click(); // VERIFY

        // VERIFY: the container that eventually holds the loaded text - adjust the
        // locator once the real element is confirmed via DevTools.
        By resultLocator = By.id("result");

        wait.until(ExpectedConditions.textMatches(resultLocator,
                java.util.regex.Pattern.compile("(?i)release")));

        WebElement resultElement = driver.findElement(resultLocator);
        System.out.println("Loaded text: " + resultElement.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
