package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net");
    }

    @Test
    public void testClickAboutUsLink() {
        String homeTitle = driver.getTitle();
        System.out.println("Home page title: " + homeTitle);
        Assert.assertEquals(homeTitle, "Training Support", "Home page title did not match");

        driver.findElement(By.linkText("About Us")).click();

        String aboutTitle = driver.getTitle();
        System.out.println("About Us page title: " + aboutTitle);
        Assert.assertEquals(aboutTitle, "About Training Support", "About Us page title did not match");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
