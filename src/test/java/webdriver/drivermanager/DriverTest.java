package webdriver.drivermanager;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DriverTest {

    WebDriver driver;

    @Test
    public void createAnHtmlUnitDriver(){

        driver = Driver.get(Driver.BROWSER_PROPERTY_NAME,"HTMLUNIT" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAFirefoxDriver(){

        driver = Driver.get(Driver.BROWSER_PROPERTY_NAME,"FIREFOX" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAChromeDriver(){

        driver = Driver.get(Driver.BROWSER_PROPERTY_NAME,"CHROME" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAEdgeDriver(){

        driver = Driver.get(Driver.BROWSER_PROPERTY_NAME,"EDGE" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAIEDriver(){

        driver = Driver.get(Driver.BROWSER_PROPERTY_NAME,"IE" );
        assertBrowserTestRuns();
    }

    @Test
    public void createAOperaDriver(){

        driver = Driver.get(Driver.BROWSER_PROPERTY_NAME,"OPERA" );
        assertBrowserTestRuns();
    }

    public void assertBrowserTestRuns(){

        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(), is("Basic Web Page Title"));
    }
}
