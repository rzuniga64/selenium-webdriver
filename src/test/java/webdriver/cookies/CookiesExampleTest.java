package webdriver.cookies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  CookiesExampleTest class.
 *  Inspect
 *  - Driver.manage
 *    - .getCookies()
 *    - .getCookieNamed(“name’)
 *  Interact
 *  - Driver.manage
 *    - .addCookie(Cookie)
 *    - .deleteAllCookies
 *    - .deleteCookie(Cookie)
 *    - .deleteCookieNamed(“name”)
 */
public class CookiesExampleTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/search.php");
    }

    /**
     *  visitSearchPageAndCheckNoLastSearchCookie method.
     *
     *  Inspect
     *  - Driver.manage
     *    - .getCookies()
     *    - .getCookieNamed(“name’)
     *  Interact
     *  - Driver.manage
     *    - .deleteAllCookies
     */
    @Test
    public void visitSearchPageAndCheckNoLastSearchCookie(){

        // delete all cookies for current domain.
        driver.manage().deleteAllCookies();
        // refresh page to get an initial st of cookies.
        driver.navigate().refresh();

        // Find a named cookie. Return null if not found.
        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchLastVisit");
        assertEquals("Should be last search cookie", "seleniumSimplifiedSearchLastVisit", aCookie.getName());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
