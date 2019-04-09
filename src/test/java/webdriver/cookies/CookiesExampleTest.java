package webdriver.cookies;

import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import static org.junit.Assert.assertEquals;

public class CookiesExampleTest {

    @Test
    public void visitSearchPageAndCheckNoLastSearchCookie(){

        WebDriver driver = Driver.get("http://compendiumdev.co.uk/selenium/search.php");

        driver.manage().deleteAllCookies();

        driver.navigate().refresh();

        Cookie aCookie = driver.manage().getCookieNamed("SeleniumSimplifiedLastSearch");

        assertEquals("Should be no last search cookie", null, aCookie);
    }
}
