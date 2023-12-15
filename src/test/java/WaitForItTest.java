import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForItTest {

    private WebDriver driver;
    private String BASE_URL = "http://localhost/waitforit.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.firefox.driver", "src/test/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void waitForInputText() {
        driver.findElement(By.id("startWaitForText")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(By.id("waitForTextInput"), "value", "dary !!!"));

        System.out.println(driver.findElement(By.id("waitForTextInput")).getAttribute("value"));
    }

    @Test
    public void waitForClass() {
        WebElement button = driver.findElement(By.id("startWaitForProperty"));
        button.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(By.id("waitForProperty"), "class", "error"));

        Assert.assertFalse(button.isEnabled());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
