package pages;

import base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void openSinDetail(Sin sin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'"+sin.getTitle()+"')]")).click();
    }

    public void markTags(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='"+tag.getXpathValue()+"']")).click();
        }
    }
}
