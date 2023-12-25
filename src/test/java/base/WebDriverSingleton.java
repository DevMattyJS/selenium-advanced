package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

    private static WebDriver driver;
    static void initialize() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//        driver = new ChromeDriver();
        initializeSeleniumServer();
    }

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            initialize();
        }

        return driver;
    }

    public static void initializeSeleniumServer() {
        URL url = null;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(url, options);
    }

}
