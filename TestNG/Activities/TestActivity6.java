import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestActivity6 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/"); // TODO: replace with the actual page from the slide
    }

    @Test
    @Parameters({"locatorValue"})
    public void testFindElement(String locatorValue) {
        WebElement element = driver.findElement(By.id(locatorValue)); // TODO: adjust locator strategy as needed
        System.out.println("Found element: " + element.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
