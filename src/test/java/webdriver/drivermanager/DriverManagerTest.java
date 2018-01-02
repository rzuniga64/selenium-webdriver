package webdriver.drivermanager;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DriverManagerTest {

    WebDriver driver;

    @Test
    public void createAnHtmlUnitDriver(){
        driver = Driver.get("selenium2basics.webdriver","HTMLUNIT" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAFirefoxDriver(){
        driver = Driver.get("webdriver.gecko.driver","FIREFOX" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAChromeDriver(){
        driver = Driver.get("selenium2basics.webdriver","CHROME" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAEdgeDriver(){
        driver = Driver.get("webdriver.ie.driver","CHROME" );
        assertBrowserTestRuns();
    }

    @Test
    public void createADefaultDriver(){
        assertBrowserTestRuns();
    }

    public void assertBrowserTestRuns(){
        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(), is("Basic Web Page Title"));
    }
}
