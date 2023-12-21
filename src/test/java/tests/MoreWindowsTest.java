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
        getDriver().get(BASE_URL + "inception.php");

        WebElement deeperButton = getDriver().findElement(By.id("letsGoDeeper"));
        String parentWindowHandle = getDriver().getWindowHandle();

        deeperButton.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindowHandles = getDriver().getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            getDriver().switchTo().window(windowHandle);
        }

        getDriver().findElement(By.xpath("//input[1]")).sendKeys("Hello second window");
        Thread.sleep(3000);         // just to be able to see the result
        getDriver().close();
        getDriver().switchTo().window(parentWindowHandle);
        deeperButton.click();
    }
}
