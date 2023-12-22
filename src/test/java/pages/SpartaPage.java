package pages;

import base.WebDriverSingleton;
import models.Sin;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static base.TestBase.BASE_URL;

public class SpartaPage {

    private WebDriver driver;
    private final String PAGE_URL = "sparta.php";

    public SpartaPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    public void forgiveSin(Sin sin) {
        WebElement sinElement = getMainSinElement(sin);
        sinElement.findElement(By.cssSelector("button")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("confirm")));
        driver.findElement(By.id("confirm")).click();
        sin.setForgiven(true);
    }

    private WebElement getMainSinElement(Sin sin) {
        return driver.findElement(By.xpath("//article[p[text() = '"+sin.getMessage()+"']]"));
    }
}
