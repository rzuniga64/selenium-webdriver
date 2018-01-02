package webdriver.interrogation;

import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DriverInterrogateTest {

    @Test
    public void driverLevelPageInterrogateMethods(){

        final String PROTOCOL = "http";
        final String DOMAIN = "www.compendiumdev.co.uk";
        final String ROOT_URL = PROTOCOL + "://" + DOMAIN;
        final String PAGE = "/selenium/basic_web_page.html";

        WebDriver driver;

        driver = Driver.get("selenium2basics.webdriver","CHROME" );
        driver.navigate().to(ROOT_URL + PAGE);

        // in the videos you might see these the wrong way round
        // always use expected, actual
        assertEquals("Basic Web Page Title", driver.getTitle());

        // Changed this from
        // assertEquals(theTestPageURL, driver.getCurrentUrl());
        // to cope with the pages being served by either http or https
        assertTrue( driver.getCurrentUrl().endsWith(PAGE));

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("A paragraph of text"));

        System.out.println(pageSource);

    }

}
