package tests;

import models.Sin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SinCityTest {

    private WebDriver driver;
    private WebElement titleField;
    private WebElement authorField;
    private WebElement messageField;
    private WebElement submitButton;

    private final String BASE_URL = "http://localhost/sincity.php";
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        titleField = driver.findElement(By.name("title"));
        authorField = driver.findElement(By.name("author"));
        messageField = driver.findElement(By.name("message"));
        submitButton = driver.findElement(By.xpath("//form/button"));

    }

    @Test
    public void testNewSin() {
        Sin spiderSin = new Sin("I killed a spider", "Matty", "I stomp on him");
        fillDataAndSubmit(spiderSin);

        spiderSin.setTags(List.of("robbery", "murder", "car accident", "hijack", "blackmail"));
        markTags(spiderSin.getTags());
    }

    @After
    public void tearDown() {
//        driver.quit();
    }

    public void fillDataAndSubmit(Sin sin) {
        titleField.sendKeys(sin.getTitle());
        authorField.sendKeys(sin.getAuthor());
        messageField.sendKeys(sin.getMessage());
//        submitButton.click();
    }

    public void markTags(List<String> tags) {
        for (String tag : tags) {
            driver.findElement(By.xpath("//input[@value='"+tag+"']")).click();
        }
    }
}
