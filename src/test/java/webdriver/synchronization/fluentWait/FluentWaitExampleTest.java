package webdriver.synchronization.fluentWait;

import com.google.common.base.Function;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 *  FluentWaitExampleTest class.
 *  FluentWait is the class that underpins the WebDriverWait. It is there to give you the additional flexibility when you need it.
 *
 *  WebDriverWait extends FluentWait so everything you have seen is really FluentWait
 *  Main difference between FluentWait and WebDriverWait:
 *  - FluentWait until can apply to anything
 *    - e.g. WebElement, String, Time, Files, etc.
 *    - And can return anything
 *  - WebDriverWait until applies to WebDriver
 *    - And can return anything
 */
public class FluentWaitExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  exampleUsingExpectedConditions method.
     *  Uses a FluentWait that uses a WebDriver. So, this is going to be equivalent to a WebDriverWait.
     *
     *  FluentWait gives more granular control and can adjust params on the fly so reuse the wait but next time with a
     *  different timeout or a different polling interval, or add additional exceptions to ignore.
     */
    @Test
    public void exampleUsingExpectedConditions(){

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                                withTimeout(10, TimeUnit.SECONDS).
                                pollingEvery(100, TimeUnit.MILLISECONDS).
                                ignoring(NotFoundException.class);

        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }

    /**
     *  exampleUsingExpectedConditionsMix method.
     *  exampleUsingExpectedConditionsMix method.
     *  This is equivalent to the above but uses a WebDriverWait.
     */
    @Test
    public void exampleUsingExpectedConditionsMix(){

        WebDriver driver;

        driver = Driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_html_form.html");

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).
                                 pollingEvery(100, TimeUnit.MILLISECONDS);

        // the following line is not needed because a WebDriverWait adds the NotFoundException to the ignored list
        // wait.ignoring(NotFoundException.class);

        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }

    /**
     *  fluentWaitDoesNotNeedWebDriver method.
     *  Uses a FluentWait that uses a Long.
     *  Fluent wait does not need webdriver, this test waits for a minimum of 4 seconds using FluentWait
     */
    @Test
    public void fluentWaitDoesNotNeedWebDriver(){

        Long startTime = System.currentTimeMillis();

        FluentWait<Long> wait = new FluentWait<Long>(startTime).
                                    withTimeout(7, TimeUnit.SECONDS).
                                    pollingEvery(50, TimeUnit.MILLISECONDS);

        Long endTime = wait.until(new Function<Long,Long>() {
                            @Override
                            public Long apply(Long startTime) {
                                Long currTime = System.currentTimeMillis();

                                if(currTime > (startTime + 4000))
                                    return currTime;

                                return null;
                            }
                        });

        System.out.println("Actual Time difference = " + (endTime- startTime) + " milliseconds");
    }

    @AfterClass
    public static void closeBrowser() {
        //driver.quit();
    }
}
