package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity3 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/login-form/");
    }

    @Test
    public void testSendInputToLoginFormUsingXpaths() {
        System.out.println("Page title: " + driver.getTitle());

        // VERIFY: exact id/name of the username field - confirmed the visible
        // label reads "Username", but the underlying attribute wasn't visible
        // from a static page fetch. id/name="username" is the common default.
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='username' or @name='username']"));
        usernameField.sendKeys("admin");

        // VERIFY: same caveat as above, but for the password field
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password' or @name='password']"));
        passwordField.sendKeys("password");

        // Confirmed via the v1 site that the button text is "Log in"
        WebElement loginButton = driver.findElement(By.xpath("//button[normalize-space()='Log in']"));
        loginButton.click();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
