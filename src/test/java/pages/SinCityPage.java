package pages;

import base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;
import org.junit.Assert;
import org.openqa.selenium.By;
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
        this.driver = WebDriverSingleton.getInstance().getDriver();
        // Initialize all elements defined at this page
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    public void fillSinDataAndConfess(Sin sin) {
        titleInput.sendKeys(sin.getTitle());
        authorInput.sendKeys(sin.getAuthor());
        messageInput.sendKeys(sin.getMessage());
        markTags(sin.getTags());
        confessButton.click();
    }

    public void verifySinDetail(Sin sin) {

        openSinDetail(sin);
        // This wait will also verify a correct message is displayed in the element
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement
                        (sinDetail.findElement(By.cssSelector("p")), sin.getMessage()));

        verifySinAuthorAndTitle(sin);
        verifySinTags(sin);
    }

    public void openSinDetail(Sin sin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'"+sin.getTitle()+"')]")).click();
    }

    public void verifySinAuthorAndTitle(Sin sin) {
        String actualSinTitle = sinDetail.findElement(By.cssSelector("h4")).getText();
        Assert.assertTrue(actualSinTitle.contains(sin.getTitle()));
        Assert.assertTrue(actualSinTitle.contains(sin.getAuthor()));
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

    public void verifySinStatus(Sin sin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        WebElement sinElement = listOfSins.findElement(By.xpath("./li[contains(text(), '"+sin.getTitle()+"')]"));

        String expectedStatus = sin.isForgiven() ? "forgiven" : "pending";
        String actualStatus = sinElement.findElement(By.xpath("./div[@class='description']/p")).getText();

        Assert.assertEquals(expectedStatus, actualStatus);
    }

    public void markTags(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='"+tag.getXpathValue()+"']")).click();
        }
    }

}
