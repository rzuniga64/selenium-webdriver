package webdriver.synchronization.fluentWait;

import com.google.common.base.Function;
import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 *  FluentWaitExercisesTest class.
 *  Using javascript_countdown.hmtl
 *  1. Create a fluent wait that returns a String with the time when the last two chars are “04”. Assert the returned
 *     time is 01:01:04
 *  2. Do the same with a WebDriverWait
 */
public class FluentWaitExercisesTest {

    private static WebDriver driver;
    WebElement countdown;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/javascript_countdown.html");
    }

    @Before
    public void setupTest(){

        driver.navigate().refresh();
        countdown = driver.findElement(By.id("javascript_countdown_time"));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(countdown));
    }

    /**
     *  waitForWebElementFluently method.
     *  Create a fluent wait that returns a String with the time when the last two chars are “04”. Assert the returned
     *  time is 01:01:04.
     */
    @Test
    public void waitForWebElementFluently(){

        String theTime = new FluentWait<WebElement>(countdown).
                withTimeout(20, TimeUnit.SECONDS).
                pollingEvery(10,TimeUnit.MILLISECONDS).
                // Works on a WebElement and returns a String.
                until(new Function<WebElement, String>() {
                    @Override
                    public String apply(WebElement element) {
                        // amended from
                        // return element.getText().endsWith("04") ? element.getText() : null;
                        // because on grid, the time between the two getText calls was enough to allow the time to have
                        // advanced further than necessary so store the getText and use that in the condition and return
                        String elementText = element.getText();
                        return elementText.endsWith("04") ? elementText : null;
                    }
                }
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }

    /**
     *  waitForTimeWithWebDriverWaitFunction method.
     *  Create a WebDriverWait that returns a String with the time when the last two chars are “04”. Assert the returned
     *  time is 01:01:04
     */
    @Test
    public void waitForTimeWithWebDriverWaitFunction(){

        String theTime = new WebDriverWait(driver,20,100).
                // works on a WebDriver and returns a String.
                until(new Function<WebDriver, String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        WebElement countDown = driver.findElement(By.id("javascript_countdown_time"));

                        // amended from
                        // return countDown.getText().endsWith("04") ? countDown.getText() : null;
                        // because on grid, the time between the two getText calls was enough
                        // to allow the time to have advanced further than necessary
                        // so store the getText and use that in the condition and return
                        String elementText = countDown.getText();
                        return elementText.endsWith("04") ? elementText : null;
                    }
                }
                );
        assertEquals("Expected a different time", "01:01:04", theTime);
    }

    /**
     *  waitForTimeWithWebDriverWaitExpectedCondition method.
     *  Use an ExpectedCondition instead of a function.
     *  Create a WebDriverWait that returns a String with the time when the last two chars are “04”. Assert the returned
     *  time is 01:01:04
     */
    @Test
    public void waitForTimeWithWebDriverWaitExpectedCondition(){

        String theTime = new WebDriverWait(driver,20,100).
                // returns a String
                until(new ExpectedCondition<String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        WebElement countDown = driver.findElement(By.id("javascript_countdown_time"));

                        // amended from
                        // return countDown.getText().endsWith("04") ? countDown.getText() : null;
                        // because on grid, the time between the two getText calls was enough
                        // to allow the time to have advanced further than necessary
                        // so store the getText and use that in the condition and return
                        String elementText = countDown.getText();
                        return elementText.endsWith("04") ? elementText : null;
                    }
                }
                );
        assertEquals("Expected a different time", "01:01:04", theTime);
    }
}
