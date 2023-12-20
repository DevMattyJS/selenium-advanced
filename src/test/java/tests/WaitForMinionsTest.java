package tests;

import base.TestBase;
import categories.ReleaseTest;
import categories.SmokeTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitForMinionsTest extends TestBase {

    // We can assign one or more categories to our tests by @Category annotation
    // We need to create class for each test category
    @Category({ReleaseTest.class, SmokeTest.class})
    @Test
    public void waitForMinions() {
        driver.get(BASE_URL + "minions.php");

        int minionCount = 5;
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys(String.valueOf(minionCount));
        driver.findElement(By.xpath("//button[contains(@class, 'btn-warning')]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Timeout waiting for number of elements to be " + minionCount)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='minions']//img"), minionCount));
        List<WebElement> allMinions = driver.findElements(By.xpath("//div[@class='minions']//img"));
        Assert.assertEquals(minionCount, allMinions.size());

    }
}
