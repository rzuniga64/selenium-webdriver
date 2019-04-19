package webdriver.basics.manipulation.examples;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 *  WebDriverWaitExampleTest.class.
 *  WebDriver has a helper class WebDriverWait which can help us synchronize our tests.
 *  Used in conjunction with another helper class ExpectedConditions we can write simple synchronization statements.
 */
public class WebDriverWaitExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() throws IOException {

        driver = Driver.get();
        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void exampleUsingExpectedConditions() {

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }

    @Test
    public void exampleWithSleepTime() {

        new WebDriverWait(driver, 10, 50).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }
}
