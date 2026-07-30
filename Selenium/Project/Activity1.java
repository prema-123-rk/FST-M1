package selenium_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {

    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/");
    }

    @Test
    public void verifyTitle() {
        driver.get("https://crm.alchemy.hguy.co");
        Assert.assertEquals(driver.getTitle(), "SuiteCRM");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
