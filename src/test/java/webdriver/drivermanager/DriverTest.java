package webdriver.drivermanager;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DriverTest {

    WebDriver driver;


    @Test
    public void createADriver() throws IOException {

        driver = Driver.get();
        assertBrowserTestRuns();
    }

    public void assertBrowserTestRuns(){

        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(), is("Basic Web Page Title"));
    }
}
