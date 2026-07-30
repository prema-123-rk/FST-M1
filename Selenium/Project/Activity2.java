package selenium_project;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://training-support.net/");
    }

    @Test
    public void testHeaderImg() {
        driver.get("https://crm.alchemy.hguy.co");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'form-row')]/div/scrm-logo-ui/scrm-image/img")));

        WebElement headerImg = driver.findElement(
                By.xpath("//div[contains(@class,'form-row')]/div/scrm-logo-ui/scrm-image/img"));

        System.out.println("Url of Header Image: " + headerImg.getAttribute("src"));
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
