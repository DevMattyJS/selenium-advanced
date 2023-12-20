package tests;

import base.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class MoreWindowsTest extends TestBase {

    @Test
    public void testGoDeeper() throws InterruptedException {
        driver.get(BASE_URL + "inception.php");

        WebElement deeperButton = driver.findElement(By.id("letsGoDeeper"));
        String parentWindowHandle = driver.getWindowHandle();

        deeperButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            driver.switchTo().window(windowHandle);
        }

        driver.findElement(By.xpath("//input[1]")).sendKeys("Hello second window");
        Thread.sleep(3000);         // just to be able to see the result
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        deeperButton.click();
    }
}
