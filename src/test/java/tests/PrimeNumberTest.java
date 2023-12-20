package tests;

import base.TestBase;
import helpers.ExcelReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class PrimeNumberTest extends TestBase {

    private final String TEST_DATA_PATH = "src/test/resources/data/data.xlsx";
    private final String SHEET_NAME = "prime";

    @Test
    public void primeTest() throws IOException {
        driver.get(BASE_URL + "primenumber.php");
        WebElement numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement primeButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-danger')]"));
        ExcelReader excelReader = new ExcelReader(TEST_DATA_PATH);
        Sheet primeSheet = excelReader.getSheetByName(SHEET_NAME);

        for (Row row : primeSheet) {
            // skip the header of the table
            if (row.getRowNum() == 0) {
                continue;
            }

            int number = (int) row.getCell(0).getNumericCellValue();
            boolean expectedValue = row.getCell(1).getBooleanCellValue();
            numberInput.clear();
            numberInput.sendKeys(String.valueOf(number));
            primeButton.click();

            checkResult(expectedValue);
        }
    }

    public void checkResult(boolean expectedValue) {
        if (expectedValue) {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result text-center success']")));
        } else {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result text-center error']")));
        }
    }
}
