import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity11 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/"); // TODO: replace with the actual page from the slide
    }

    @Test
    public void testPageContent() {
        String actualText = driver.findElement(By.id("someElement")).getText(); // TODO: adjust locator to the real element
        Assert.assertEquals(actualText, "Expected text"); // TODO: replace with the expected value
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
