package webdriver.basics.interrogation;

import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.core.Is.is;


public class DriverInterrogateRefactoredTest {

    private final String PROTOCOL = "https";
    private final String DOMAIN = "www.compendiumdev.co.uk";
    private final String ROOT_URL = PROTOCOL + "://" + DOMAIN;
    private final String PAGE = "/selenium/basic_web_page.html";

    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){

        driver = Driver.get("webdriver.htmlunit.driver","HTMLUNIT" );
    }

    @Test
    public void driverLevelPageInterrogateMethods(){

        final String theTestPageURL = ROOT_URL + PAGE;

        driver.navigate().to(theTestPageURL);

        assertThat(driver.getTitle(), is("Basic Web Page Title"));
        assertThat(driver.getCurrentUrl(), endsWith(theTestPageURL));
        assertThat(driver.getPageSource(), containsString("A paragraph of text"));
    }

    private void assertThat(String title, Matcher<String> basic_web_page_title) {}

    @AfterClass
    public static void stopDriver(){
        //driver.quit();
    }

}