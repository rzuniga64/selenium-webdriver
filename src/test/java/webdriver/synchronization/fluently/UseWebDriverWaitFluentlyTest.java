package webdriver.synchronization.fluently;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *  UseWebDriverWaitFluentlyTest class.
 *  WebDriverWait is built on top of a class called FluentWait. That gives us the ability to construct a WebDriverWait that is more readable.
 *  Additional methods:
 *  .pollingEvery
 *  .ignoring
 *  .withTimeout
 *  .withMessage
 *  .ignoreAll
 */
public class UseWebDriverWaitFluentlyTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://compendiumdev.co.uk");
    }

    /**
     *  wait5Seconds method.
     *  Uses additional FluentWait methods.  The apply function of the ExpectedCondition throws a IllegalStateException
     *  every 100 ms so it never returns a Boolean true. Therefore it never comes out of its loop except when it times
     *  out after 5 seconds.
     */
    @Test
    public void wait5Seconds(){

        long currTime = System.currentTimeMillis();

        try{
            /* this will ignore the thrown exception in the apply */
            new WebDriverWait(driver, 1).
                    pollingEvery(100, TimeUnit.MILLISECONDS).       // override polling time
                    ignoring(IllegalStateException.class).                   // ignore exceptions
                    withTimeout(5, TimeUnit.SECONDS).               // override timeout
                    withMessage("See I told you a Timeout Happened").until(  // include text in Timeout Message
                        // anonymous class throws an illegal state exception
                        new ExpectedCondition<Boolean>() {
                            @Override
                            public Boolean apply(WebDriver webDriver) {
                                throw new IllegalStateException();
                            }
                        }
                    );

            fail("A time out exception should have been thrown");

        } catch (TimeoutException e){
            assertTrue(e.getMessage().contains("See I told you a Timeout Happened"));
        }

        long nowTime = System.currentTimeMillis();
        System.out.println("Timeout calculated as " + (nowTime - currTime));

        assertTrue((nowTime - currTime) >= 5000);
    }

    @AfterClass
    public static void closeBrowser() {
        //driver.quit();
    }
}
