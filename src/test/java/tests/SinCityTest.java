package tests;

import base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SinCityTest extends TestBase {

    private WebElement titleField;
    private WebElement authorField;
    private WebElement messageField;
    private WebElement submitButton;

    @Test
    public void testNewSin() {
        driver.get(BASE_URL + "sincity.php");
        titleField = driver.findElement(By.name("title"));
        authorField = driver.findElement(By.name("author"));
        messageField = driver.findElement(By.name("message"));
        submitButton = driver.findElement(By.xpath("//form/button"));

        Sin spiderSin = new Sin("I killed a spider", "Matty", "I stomp on him");
        fillDataAndSubmit(spiderSin);

        spiderSin.setTags(List.of(SinType.MURDER, SinType.BLACKMAIL, SinType.ROBBERY));
        markTags(spiderSin.getTags());
    }

    public void fillDataAndSubmit(Sin sin) {
        titleField.sendKeys(sin.getTitle());
        authorField.sendKeys(sin.getAuthor());
        messageField.sendKeys(sin.getMessage());
//        submitButton.click();
    }

    public void markTags(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='"+tag.getXpathValue()+"']")).click();
        }
    }
}
