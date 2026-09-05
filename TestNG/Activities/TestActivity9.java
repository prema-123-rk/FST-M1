import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestActivity9 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/"); // TODO: replace with the actual page from the slide
    }

    @BeforeMethod
    public void beforeEachTest() {
        Reporter.log("Starting a new test method", true);
    }

    @Test
    public void testAlertHandling() {
        driver.findElement(By.id("alertButton")).click(); // TODO: adjust locator to the real trigger element

        Alert alert = driver.switchTo().alert();
        Reporter.log("Alert text: " + alert.getText(), true);
        alert.accept();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
