package selenium_project;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class Activity9 {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        action = new Actions(driver);
        driver.get("https://training-support.net/");
    }

    @Test
    public void testSelectLeadsOption() {
        driver.get("https://crm.alchemy.hguy.co");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        username.sendKeys("admin");
        password.sendKeys("5Nx#I6BK%r3$8vz0ch");
        driver.findElement(By.id("login-button")).click();

        WebElement leads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Leads']")));
        action.moveToElement(leads).perform();
        WebElement viewLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='View Leads']")));
        viewLeads.click();
    }

    @Test(dependsOnMethods = "testSelectLeadsOption")
    public void testLeadsPage() {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//table[contains(@class,'cdk-table')]//tbody//tr[contains(@class,'cdk-row')]")));

        System.out.println("The names and user types are:");
        int limit = Math.min(10, rows.size());

        for (int i = 0; i < limit; i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String userType = cells.get(7).getText();
            System.out.println(name + " - " + userType);
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}
