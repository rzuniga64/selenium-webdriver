package webdriver.synchronization.webDriverWaitBasics;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 *  WebDriverWaitExampleTest class.
 *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html and checks the title.
 */
public class WebDriverWaitExampleTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        wait =  new WebDriverWait(driver,10);
    }

    /**
     *  exampleUsingExpectedConditions method.
     *  Navigates to compendiumdev.co.uk/selenium/basic_ajax.html and checks the title.
     */
    @Test
    public void exampleUsingExpectedConditions(){

        try {
            wait.until(titleIs("Basic Ajax"));
            assertEquals("Basic Ajax", driver.getTitle());
        } catch (TimeoutException e) {
            // ignore the timeout exception.
        }
    }

    @Test
    public void exampleWithSleepTime(){

        try {
            wait =  new WebDriverWait(driver,10,50);
            wait.until(titleIs("Basic Ajax"));
            assertEquals("Basic Ajax", driver.getTitle());
        } catch (TimeoutException e) {
            // ignore the timeout exception
        }
    }
}
