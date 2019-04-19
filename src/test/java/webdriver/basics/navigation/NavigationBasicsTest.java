package webdriver.basics.navigation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 *  Use the following URLs:
 *      - navigate().to()
 *      - forward()
 *      - back()
 *      - refresh()
 *  URL: http://compendium.co.uk
 *  Assert on titles to check navigation worked.
 *
 *  File Path                       Title
 *  /selenium                       "Selenium Simplified"
 *  /selenium/search.php            "Selenium Simplified Search Engine"
 *  /selenium/basic_html_form.html  "HTML Form elements"
 *  /selenium/basic_web_page.html   "Basic Web Page Title"
 *  /selenium/refresh.php           "Refreshed Page on ([0-9]{10})"
 */
public class NavigationBasicsTest {

    static WebDriver driver;
    final private String PROTOCOL = "http";
    final private String DOMAIN = "www.compendiumdev.co.uk";
    final private String ROOT_URL = PROTOCOL + "://" + DOMAIN;

    @BeforeClass
    public static void createDriver() throws IOException {
        driver = Driver.get();
    }

    @Test
    public void navigateWithGet(){

        driver.get(ROOT_URL + "/selenium");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
    }

    @Test
    public void navigateWithNavigateTo(){

        driver.navigate().to(ROOT_URL + "/selenium/search.php");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateWithNavigateToURL() throws MalformedURLException {

        URL searchPage = new URL(PROTOCOL, DOMAIN,"/selenium/search.php");
        driver.navigate().to(searchPage);
        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateWithNavigateBackAndForward(){

        driver.navigate().to(ROOT_URL + "/selenium/basic_html_form.html");
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().to(ROOT_URL + "/selenium/basic_web_page.html");
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));

        driver.navigate().back();
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().forward();
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));
    }

    @Test
    public void navigateWithRefresh(){

        driver.navigate().to(ROOT_URL + "/selenium/refresh.php");

        final String refreshTitleConstant = "Refreshed Page on ";
        String pageTitle = driver.getTitle();

        assertTrue(pageTitle.startsWith(refreshTitleConstant));

        long startTime = Long.parseLong(pageTitle.replaceFirst(refreshTitleConstant, ""));

        // Synchronise using sleep to guarantee time moves on.
        // The only time we sleep is waiting for time.
        // Never sleep waiting for page objects.
        // Allow for time to advance so the next time the page is refreshed we get a different title.
        try{Thread.sleep(2000);}
        catch(InterruptedException e){/*ignore interrupt */};

        driver.navigate().refresh();

        // in opera the refresh did not block or wait for the page to change so needed a synchronisation check,
        // not an assert
        // assertTrue(driver.getTitle().startsWith(refreshTitleConstant));

        new WebDriverWait(driver,10).until(titleIsNoLonger(pageTitle));
        long endTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant, ""));

        assertTrue("expected " + endTime + " > " + startTime, endTime > startTime);
        //assertThat(endTime, greaterThan(startTime));
    }

    private ExpectedCondition<Boolean> titleIsNoLonger(final String pageTitle) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !(pageTitle.equals(input.getTitle()));
            }
        };
    }

    @AfterClass
    public static void quitDriver(){
        driver.quit();
    }
}