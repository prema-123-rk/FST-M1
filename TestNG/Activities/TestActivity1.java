import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity1 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net");
    }

    @Test
    public void testOpenPage() {
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
