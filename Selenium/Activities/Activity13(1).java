package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * ASSUMPTIONS (page content itself wasn't retrievable, but standard HTML
 * table structure is a safe bet regardless of the actual data):
 *  - There's exactly one <table> on the page. If there's more than one,
 *    the XPaths below need a more specific ancestor (e.g. by id/class).
 *  - "Rows" counts every <tr>, including a header row if one exists
 *    (e.g. a <thead><tr><th>...</th></tr></thead>). If the header row
 *    shouldn't count, restrict to tbody/tr instead: //table/tbody/tr.
 *  - "Third row" / "second row second column" are 1-indexed, matching
 *    how the slide phrases them and how XPath indexes (tr[3], td[2]).
 */
public class Activity13 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/tables");
    }

    @Test
    public void testTableRowsColumnsAndCellValues() {
        System.out.println("Page title: " + driver.getTitle());

        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        System.out.println("Number of rows: " + rows.size());

        // Column count taken from the first row - assumes a rectangular table
        // (every row has the same number of cells).
        List<WebElement> firstRowCells = driver.findElements(By.xpath("//table//tr[1]/*[self::td or self::th]"));
        System.out.println("Number of columns: " + firstRowCells.size());

        // Third row - all cell values
        List<WebElement> thirdRowCells = driver.findElements(By.xpath("//table//tr[3]/*[self::td or self::th]"));
        System.out.print("Third row values: ");
        for (WebElement cell : thirdRowCells) {
            System.out.print(cell.getText() + " | ");
        }
        System.out.println();

        // Second row, second column - single cell value
        WebElement targetCell = driver.findElement(
                By.xpath("//table//tr[2]/*[self::td or self::th][2]"));
        System.out.println("Second row, second column value: " + targetCell.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
