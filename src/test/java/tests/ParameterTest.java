package tests;

import base.TestBase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterTest extends TestBase {

    private int number;
    private boolean expectedValue;

    @Parameterized.Parameters
    public static List<Object[]> getData() {
        return Arrays.asList(new Object[][]{{1, true}, {2, true}, {3, true}, {4, false}});
    }

    public ParameterTest(int number, boolean expectedValue) {
       this.number = number;
       this.expectedValue = expectedValue;
    }

    @Test
    public void primeTestWithParameters() {
        driver.get(BASE_URL + "primenumber.php");

        WebElement numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement primeButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-danger')]"));

        numberInput.clear();
        numberInput.sendKeys(String.valueOf(number));
        primeButton.click();

        checkResult(expectedValue);
    }

    @After
    public void tearDown() {
        driver.quit();
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
