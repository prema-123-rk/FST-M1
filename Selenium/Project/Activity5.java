package selenium_project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity5 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://training-support.net/");
    }

    @Test
    public void testNavColor() {
        driver.get("https://crm.alchemy.hguy.co");

        WebElement username = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));

        username.sendKeys("admin");
        password.sendKeys("5Nx#I6BK%r3$8vz0ch");

        driver.findElement(By.id("login-button")).click();

        WebElement navbar = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.tagName("nav")));

        Color navColor = Color.fromString(navbar.getCssValue("color"));

        System.out.println("Color of Navbar in hex is: " + navColor.asHex());
        System.out.println("Color of Navbar in RGB is: " + navColor.asRgb());
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
