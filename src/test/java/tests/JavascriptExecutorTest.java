package tests;

import base.TestBase;
import categories.ReleaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JavascriptExecutorTest extends TestBase {

    private JavascriptExecutor js;

    @Test
    public void testHighlight() {
        initializeExecutorAndOpenBaseUrl();
        List<WebElement> tableRows = getDriver().findElements(By.xpath("//table/tbody/tr"));
        for (WebElement tableRow : tableRows) {
            if (tableRow.getText().contains("Enrigue")) {
                highlight(tableRow);
            }
        }
    }

    @Test
    public void testScrollToEnd() {
        initializeExecutorAndOpenBaseUrl();
        WebElement lastRow = getDriver().findElement(By.xpath("//table/tbody/tr[last()]"));
        js.executeScript("arguments[0].scrollIntoView()", lastRow);
    }

    @Test
    public void testScrollToEndAgain() {
        initializeExecutorAndOpenBaseUrl();
        long bodyHeight = (long) js.executeScript("return document.body.scrollHeight");
        js.executeScript("window.scrollBy(0, "+bodyHeight+")");
    }

    @Category(ReleaseTest.class)
    @Test
    public void testScrollToEndStepByStep() {
        initializeExecutorAndOpenBaseUrl();
        long bodyHeight = (long) js.executeScript("return document.body.scrollHeight");
        for (int i = 0; i < bodyHeight; i += 100) {
            js.executeScript("window.scrollBy(0,"+i+")");
        }
    }

    private void highlight(WebElement row) {
        // We can use JavascriptExecutor interface to execute JS scripts in Selenium
        js.executeScript("arguments[0].style.border='3px solid red'", row);
    }

    private void initializeExecutorAndOpenBaseUrl() {
        js = (JavascriptExecutor) getDriver();
        getDriver().get(BASE_URL + "tabulka.php");
    }

}
