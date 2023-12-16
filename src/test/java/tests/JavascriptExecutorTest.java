package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class JavascriptExecutorTest{

    private  WebDriver driver;
    private JavascriptExecutor js;
    private final String BASE_URL = "http://localhost/tabulka.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get(BASE_URL);
    }

    @Test
    public void testHighlight() {
        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr"));
        for (WebElement tableRow : tableRows) {
            if (tableRow.getText().contains("Enrigue")) {
                highlight(tableRow);
            }
        }
    }

    @Test
    public void testScrollToEnd() {
        WebElement lastRow = driver.findElement(By.xpath("//table/tbody/tr[last()]"));
        js.executeScript("arguments[0].scrollIntoView()", lastRow);
    }

    @Test
    public void testScrollToEndAgain() {
        long bodyHeight = (long) js.executeScript("return document.body.scrollHeight");
        js.executeScript("window.scrollBy(0, "+bodyHeight+")");
    }

    @Test
    public void testScrollToEndStepByStep() {
        long bodyHeight = (long) js.executeScript("return document.body.scrollHeight");
        for (int i = 0; i < bodyHeight; i += 100) {
            js.executeScript("window.scrollBy(0,"+i+")");
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void highlight(WebElement row) {
        // We can use JavascriptExecutor interface to execute JS scripts in Selenium
        js.executeScript("arguments[0].style.border='3px solid red'", row);
    }

}
