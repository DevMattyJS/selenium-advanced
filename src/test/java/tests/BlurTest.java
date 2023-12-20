package tests;

import categories.SmokeTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlurTest {

    private WebDriver driver;
    private final String BASE_URL = "http://localhost/waitforit.php";
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @Category(SmokeTest.class)
    @Test
    public void blurTest() {
        WebElement blurField = driver.findElement(By.id("waitForBlur"));
        blurField.sendKeys("wait for it");
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur()", blurField);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeToBe(blurField, "value", "blured!"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
