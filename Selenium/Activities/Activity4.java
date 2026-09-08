package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * NOTE: I could not retrieve the actual page content for
 * https://training-support.net/webelements/target-practice - the search
 * engine returned no cached snapshot, and web_fetch requires a URL that
 * already appeared in a search result. Every locator below is a
 * best-guess based on the slide's wording and is marked // VERIFY -
 * confirm with Chrome DevTools (Inspect) before running.
 */
public class Activity4 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/target-practice");
    }

    @Test
    public void testTargetPracticeElements() {
        System.out.println("Page title: " + driver.getTitle());

        // "3rd header on the page" - assumes headers means any h1-h6 tag, in document order.
        // VERIFY: adjust the tag range if the page only uses specific header levels.
        List<WebElement> headers = driver.findElements(By.xpath("//h1 | //h2 | //h3 | //h4 | //h5 | //h6"));
        WebElement thirdHeader = headers.get(2); // 0-indexed: 3rd header
        System.out.println("3rd header text: " + thirdHeader.getText());

        WebElement fifthHeader = headers.get(4); // 0-indexed: 5th header
        String color = fifthHeader.getCssValue("color");
        System.out.println("5th header color: " + color);

        // VERIFY: "purple button" and "slate button" are almost certainly named
        // after Tailwind-style utility classes (e.g. "bg-purple-500", "bg-slate-500")
        // rather than button text - confirm the real class names via DevTools.
        WebElement purpleButton = driver.findElement(By.cssSelector("button[class*='purple']"));
        String classes = purpleButton.getAttribute("class");
        System.out.println("Purple button classes: " + classes);

        WebElement slateButton = driver.findElement(By.cssSelector("button[class*='slate']"));
        System.out.println("Slate button text: " + slateButton.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
