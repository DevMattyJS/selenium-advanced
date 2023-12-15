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
import java.util.List;

public class WaitForMinionsTest {

    private WebDriver driver;
    private final String BASE_URL = "http://localhost/minions.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.firefox.driver", "src/test/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void waitForMinions() {
        int minionCount = 5;
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys(String.valueOf(minionCount));
        driver.findElement(By.xpath("//button[contains(@class, 'btn-warning')]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Timeout waiting for number of elements to be " + minionCount)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='minions']//img"), minionCount));
        List<WebElement> allMinions = driver.findElements(By.xpath("//div[@class='minions']//img"));
        Assert.assertEquals(minionCount, allMinions.size());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
