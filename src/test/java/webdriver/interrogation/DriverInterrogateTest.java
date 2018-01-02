package webdriver.interrogation;

import org.junit.BeforeClass;
import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Create a test which visits www.compendium.co.uk/selenium/basic_web_page.html.
 *  Assert title equals 'Basic Web Page Title'
 *  Assert current URL is what you expect
 *  Check page source contains 'A paragraph of text'
 */
public class DriverInterrogateTest {

    final String PROTOCOL = "https";
    final String DOMAIN = "www.compendiumdev.co.uk";
    final String ROOT_URL = PROTOCOL + "://" + DOMAIN;
    final String PAGE = "/selenium/basic_web_page.html";

    static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        driver = Driver.get("selenium2basics.webdriver","HTMLUNIT" );
    }

    @Test
    public void driverLevelPageInterrogateMethods(){

        driver.navigate().to(ROOT_URL + PAGE);

        assertThat( driver.getTitle(), is("Basic Web Page Title"));
        assertThat( driver.getCurrentUrl(), is(ROOT_URL + PAGE));
        assertThat( driver.getPageSource(), containsString("A paragraph of text"));
    }
}
