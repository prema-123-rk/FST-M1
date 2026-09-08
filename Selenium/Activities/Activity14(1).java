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
 * ASSUMPTIONS (page content itself wasn't retrievable, but the slide
 * confirms column names "Book Name" and "Price" exist, and that the
 * Price header is clickable to sort):
 *  - Header row is row 1 (//table//tr[1]), using <th> cells.
 *  - "5th row" refers to the 5th <tr> overall, i.e. the 4th data row
 *    after the header row. If the header isn't a real <tr> (e.g. styled
 *    div instead), adjust the row index accordingly.
 *  - Clicking the header text itself triggers the sort (common pattern
 *    for JS-driven sortable tables) rather than a separate sort icon.
 */
public class Activity14 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://training-support.net/webelements/tables");
    }

    // Finds the 1-based column index of a header whose text matches exactly
    private int findColumnIndex(String headerText) {
        List<WebElement> headers = driver.findElements(By.xpath("//table//tr[1]/th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(headerText)) {
                return i + 1; // XPath is 1-indexed
            }
        }
        throw new IllegalStateException("Could not find a column header matching: " + headerText);
    }

    private String getBookNameInRow(int rowIndex, int bookNameColumnIndex) {
        WebElement cell = driver.findElement(
                By.xpath("//table//tr[" + rowIndex + "]/td[" + bookNameColumnIndex + "]"));
        return cell.getText();
    }

    @Test
    public void testSortTableByPriceAndCompareBookName() {
        System.out.println("Page title: " + driver.getTitle());

        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        System.out.println("Number of rows: " + rows.size());

        List<WebElement> firstRowCells = driver.findElements(By.xpath("//table//tr[1]/th"));
        System.out.println("Number of columns: " + firstRowCells.size());

        int bookNameCol = findColumnIndex("Book Name");
        int priceCol = findColumnIndex("Price");

        String bookNameBeforeSort = getBookNameInRow(5, bookNameCol);
        System.out.println("Book Name in row 5 (before sort): " + bookNameBeforeSort);

        // Click the Price column header to sort ascending
        WebElement priceHeader = driver.findElement(
                By.xpath("//table//tr[1]/th[" + priceCol + "]"));
        priceHeader.click();

        String bookNameAfterSort = getBookNameInRow(5, bookNameCol);
        System.out.println("Book Name in row 5 (after sorting by Price): " + bookNameAfterSort);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
