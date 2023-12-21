package base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class TestBase {

    public static final String BASE_URL = "http://localhost/";

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
//        WebDriverSingleton.getWebDriverInstance().quit();
    }

    public WebDriver getDriver() {
        return WebDriverSingleton.getWebDriverInstance();
    }
}
