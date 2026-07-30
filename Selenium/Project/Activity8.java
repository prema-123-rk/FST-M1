package selenium_project;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Activity8 {

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
    public void testSelectAccountsOption() {
        driver.get("https://crm.alchemy.hguy.co");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        username.sendKeys("admin");
        password.sendKeys("5Nx#I6BK%r3$8vz0ch");
        driver.findElement(By.id("login-button")).click();

        WebElement accounts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Accounts']")));
        action.moveToElement(accounts).perform();
        WebElement viewAccounts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='View Accounts']")));
        viewAccounts.click();
    }

    @Test(dependsOnMethods = "testSelectAccountsOption")
    public void testAccountsPage() {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//table[contains(@class,'cdk-table')]//tbody//tr[contains(@class,'cdk-row')]")));

        System.out.println("Names in first five odd rows are:");
        int count = 0;
        for (int i = 0; i < rows.size() && count < 5; i += 2) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            System.out.println(cells.get(2).getText());
            count++;
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
