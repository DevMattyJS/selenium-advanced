package tests;

import base.TestBase;
import categories.SmokeTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForItTest extends TestBase {

    @Test
    public void waitForInputText() {
        getDriver().get(BASE_URL + "waitforit.php");

        getDriver().findElement(By.id("startWaitForText")).click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(By.id("waitForTextInput"), "value", "dary !!!"));

        System.out.println(getDriver().findElement(By.id("waitForTextInput")).getAttribute("value"));
    }

    @Category(SmokeTest.class)
    @Test
    public void waitForClass() {
        getDriver().get(BASE_URL + "waitforit.php");

        WebElement button = getDriver().findElement(By.id("startWaitForProperty"));
        button.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(By.id("waitForProperty"), "class", "error"));

        Assert.assertFalse(button.isEnabled());
    }
}
