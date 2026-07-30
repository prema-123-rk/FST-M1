package selenium_project;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class Activity7 {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().minimize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        action = new Actions(driver);
        driver.get("https://crm.alchemy.hguy.co");
    }

    @Test
    public void testSelectLeadsOption() {
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
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            cells.get(1).click();
            WebElement popover = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.popover-body")));
            System.out.println("Row " + (i + 1) + ":");
            System.out.println(popover.getText());
            System.out.println("--------------------------------");
            action.sendKeys(Keys.ESCAPE).perform();
            wait.until(ExpectedConditions.invisibilityOf(popover));
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}
