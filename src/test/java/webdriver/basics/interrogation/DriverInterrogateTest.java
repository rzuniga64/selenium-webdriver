package webdriver.basics.interrogation;

import org.junit.BeforeClass;
import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Create a test which visits www.compendium.co.uk/selenium/basic_web_page.html.
 *  Assert title equals 'Basic Web Page Title'
 *  Assert current URL is what you expect
 *  Check page source contains 'A paragraph of text'
 */
public class DriverInterrogateTest {

    private final String PROTOCOL = "https";
    private final String DOMAIN = "www.compendiumdev.co.uk";
    private final String ROOT_URL = PROTOCOL + "://" + DOMAIN;
    private final String PAGE = "/selenium/basic_web_page.html";

    private final String theTestPageURL = ROOT_URL + PAGE;

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        driver = Driver.get("webdriver.htmlunit.driver","HTMLUNIT" );
    }

    @Test
    public void driverLevelPageInterrogateMethods(){

        driver.navigate().to(theTestPageURL);

        assertEquals( driver.getTitle(), "Basic Web Page Title");
        assertEquals( driver.getCurrentUrl(), theTestPageURL);
        assertTrue( driver.getPageSource().contains("A paragraph of text"));

        System.out.println(driver.getPageSource());
    }
}
