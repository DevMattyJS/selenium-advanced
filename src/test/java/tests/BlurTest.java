package tests;

import base.TestBase;
import categories.SmokeTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlurTest extends TestBase {

    @Category(SmokeTest.class)
    @Test
    public void blurTest() {
        driver.get(BASE_URL + "waitforit.php");
        WebElement blurField = driver.findElement(By.id("waitForBlur"));
        blurField.sendKeys("wait for it");
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur()", blurField);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeToBe(blurField, "value", "blured!"));

    }
}
