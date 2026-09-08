package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NOTE: could not retrieve the actual page content for
 * https://training-support.net/webelements/drag-drop - no cached search
 * snapshot was available. Locators below are best-guesses marked
 * // VERIFY - confirm the real ball/dropzone ids via Chrome DevTools
 * before running.
 *
 * DESIGN NOTE: Actions.dragAndDrop(source, target) is a convenience
 * method that fires native HTML5 drag events - many real-world DnD
 * widgets (this one likely included) are implemented with JS
 * mousedown/mousemove/mouseup handlers instead, which dragAndDrop()
 * does not reliably trigger. The clickAndHold -> moveToElement ->
 * release sequence below simulates the actual mouse movements and is
 * the more dependable approach for this kind of widget.
 */
public class Activity12 {

    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.get("https://training-support.net/webelements/drag-drop");
    }

    private void dragBallInto(WebElement ball, WebElement dropzone) {
        actions.clickAndHold(ball)
                .moveToElement(dropzone)
                .release()
                .perform();
    }

    private boolean dropzoneContainsBall(WebElement dropzone, By ballLocator) {
        return !dropzone.findElements(ballLocator).isEmpty();
    }

    @Test
    public void testDragBallIntoDropzone1ThenDropzone2() {
        System.out.println("Page title: " + driver.getTitle());

        By ballLocator = By.id("ball");             // VERIFY
        By dropzone1Locator = By.id("dropzone1");    // VERIFY
        By dropzone2Locator = By.id("dropzone2");    // VERIFY

        WebElement ball = driver.findElement(ballLocator);
        WebElement dropzone1 = driver.findElement(dropzone1Locator);
        WebElement dropzone2 = driver.findElement(dropzone2Locator);

        dragBallInto(ball, dropzone1);
        Assert.assertTrue(dropzoneContainsBall(dropzone1, ballLocator),
                "Expected ball to be inside Dropzone 1 after first drag");

        // Re-find the ball in case moving it re-parented/re-rendered the element
        ball = driver.findElement(ballLocator);
        dragBallInto(ball, dropzone2);
        Assert.assertTrue(dropzoneContainsBall(dropzone2, ballLocator),
                "Expected ball to be inside Dropzone 2 after second drag");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
