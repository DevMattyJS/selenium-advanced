package pages;

import base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static base.TestBase.BASE_URL;

public class SinCityPage {

    private WebDriver driver;
    private final String PAGE_URL = "sincity.php";

    // Define page elements, using @FindBy annotation
    @FindBy(name = "title")
    private WebElement titleInput;

    @FindBy(name = "author")
    private WebElement authorInput;

    @FindBy(name = "message")
    private WebElement messageInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement confessButton;

    @FindBy(css = "div.sinsListArea")
    private WebElement sinListSection;

    @FindBy(xpath = "//div[contains(@class, 'detail')]/article")
    private WebElement sinDetail;

    public SinCityPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
        // Initialize all elements defined at this page
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    public void fillSinDataAndConfess(Sin sin, List<SinType> tags) {
        titleInput.sendKeys(sin.getTitle());
        authorInput.sendKeys(sin.getAuthor());
        messageInput.sendKeys(sin.getMessage());
        markTags(tags);
        confessButton.click();
    }

    public void verifySinDetail(Sin sin) {

        openSinDetail(sin);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement
                        (driver.findElement(By.xpath("//div[contains(@class, 'detail')]/article/h4[1]")), sin.getAuthor() + " : " + sin.getTitle()));

        verifySinAuthorAndTitle(sin);
        verifySinMessage(sin);
        verifySinTags(sin);
    }

    public void openSinDetail(Sin sin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'"+sin.getTitle()+"')]")).click();
    }

    public void verifySinAuthorAndTitle(Sin sin) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String expectedText= sin.getAuthor() + " : " + sin.getTitle();

        WebElement authorAndTitleElement = sinDetail.findElement(By.xpath("./h4[1]"));
        String authorAndTitleText = (String) js.executeScript("return arguments[0].firstChild.textContent", authorAndTitleElement);
        Assert.assertEquals(expectedText, authorAndTitleText);
    }

    public void verifySinMessage(Sin sin) {
        String expectedMessage = sin.getMessage();
        String message = sinDetail.findElement(By.xpath("./p")).getText();
        Assert.assertEquals(expectedMessage, message);
    }

    public void verifySinTags(Sin sin) {
        List<WebElement> sinTagsEle = sinDetail.findElements(By.xpath("//div[@class='tags']/ul/li"));
        List <String> sinTags = new ArrayList<>();
        List<String> expectedSinTags = new ArrayList<>();

        for (WebElement sinTag : sinTagsEle) {
            sinTags.add(sinTag.getText());
        }

        for (SinType sinTag : sin.getTags()) {
            expectedSinTags.add(sinTag.getXpathValue());
        }

        Assert.assertEquals(expectedSinTags, sinTags);
    }

    public void verifySinStatus(Sin sin, String expectedStatus) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        WebElement sinElement = listOfSins.findElement(By.xpath("./li[contains(text(), '"+sin.getTitle()+"')]"));
        String descriptionText = sinElement.findElement(By.xpath("./div[@class='description']/p")).getText();
        Assert.assertEquals(expectedStatus, descriptionText);
    }

    public void markTags(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='"+tag.getXpathValue()+"']")).click();
        }
    }
}
