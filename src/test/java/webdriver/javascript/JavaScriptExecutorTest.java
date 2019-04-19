package webdriver.javascript;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *  JavaScriptExecutorTest class.
 *
 *  Call the draw function with arguments from the .executeScript method & assert it drew e.g.
 *  - .executeScript(“document.title=arguments[0]”, “bob”);
 *  Execute a script which adds two arguments & assert the result
 *  - “return 10;” will return 10
 *  Pass a WebElement as an argument, and use JQuery to ‘hide’ it, then assert that it is hidden
 *  - e.g. to hide body use JQuery: $(‘body’).hide();
 *  Create a test which adds JavaScript to the page, demonstrate that it persists beyond the anonymous function execution.
 */
public class JavaScriptExecutorTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
    }

    @Before
    public void resetBeforeTest(){

        driver.navigate().refresh();
    }

    /**
     *  passArgsToTheJavaScript method.
     *  Call the draw function with arguments from the .executeScript method & assert it drew.
     */
    @Test
    public void passArgsToTheJavaScript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(2, actionsCount);

        for(int testLoop=0; testLoop < 10; testLoop++){

            js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);",
                    testLoop*20, testLoop*20, "#" + testLoop + testLoop + "0000");
        }

        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(12, actionsCount);
    }

    /**
     *  returnValuesFromJavaScript method.
     *  Execute a script which adds two arguments & assert the result
     *  - “return (arguments[0]+arguments[1]);, 20, 20” will return 40
     */
    @Test
    public void returnValuesFromJavaScript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertEquals("Javascript should calc correctly", 40L, js.executeScript(
                        "return (arguments[0]+arguments[1]);",
                        20, 20));
    }

    /**
     *  returnHardCodedValueFromJavaScript method.
     *  Execute a script which returns 10.
     *  - “return 10;” will return 10
     */
    @Test
    public void returnHardCodedValueFromJavaScript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertEquals("return 10", 10L, js.executeScript("return 10;"));
    }

    /**
     *  changeTitleUsingJavascript method.
     *  Execute a script which set the Title to whatever we are passing in.
     */
    @Test
    public void changeTitleUsingJavascript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertEquals("Javascript Canvas Example", driver.getTitle());

        js.executeScript("document.title=arguments[0]", "bob");
        assertEquals("bob", driver.getTitle());
    }

    /**
     *  useJQueryToHideBodyWithNoParams method.
     *  Pass a WebElement as an argument, and use JQuery to ‘hide’ it, then assert that it is hidden
     *  - e.g. to hide body use JQuery: $(‘body’).hide();
     */
    @Test
    public void useJQueryToHideBodyWithNoParams(){

        JavascriptExecutor js =(JavascriptExecutor)driver;

        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());
        js.executeScript("$('body').hide();");
        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    /**
     *  hideWebElementAsParam method.
     *  Pass a WebElement as an argument, and use JQuery to ‘hide’ it, then assert that it is hidden
     *  - e.g. to hide body use JQuery: $(‘body’).hide();
     *  Replace the body with our passed in WebElement which is the 'commands' list.
     *  The only thing that will disappear is the 'commands' list.
     */
    @Test
    public void hideWebElementAsParam(){

        JavascriptExecutor js =(JavascriptExecutor)driver;

        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());
        js.executeScript("$(arguments[0]).hide();", driver.findElement(By.cssSelector("#commands")));
        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    /**
     *  javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehind method.
     *
     *  Create a test which adds JavaScript to the page, demonstrate that it persists beyond the anonymous function
     *  execution.
     *  - Only firefox legacy handles alerts generated by the test script
     *  - IE Blocks
     *  - Chrome throws an UnhandledAlertException
     *  - Marionette GeckoDriver blocks
     */
    //@Ignore("This test only works on Firefox Legacy - see other example below")
    @Test
    public void javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehind(){

        JavascriptExecutor js =(JavascriptExecutor)driver;

        // this code run as an anonymous function with no trace left
        js.executeScript("alert('alert triggered by webdriver');");

        assertThat(driver.switchTo().alert().getText(), is("alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        // this code creates a function that will persist as we have added it to the global window
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');};"+
                    "window.webdriveralert.call();");

        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        // this can only work if we managed to leave javascript lying around
        js.executeScript("window.webdriveralert.call();");

        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();
    }

    /**
     *  javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehindOtherBrowsers method.
     *  Create a test which adds JavaScript to the page, demonstrate that it persists beyond the anonymous function
     *  execution.
     */
    @Test
    public void javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehindOtherBrowsers(){

        JavascriptExecutor js =(JavascriptExecutor)driver;

        // In this example I'm not using alerts, I'm using title
        // this code run as an anonymous function with no trace left
        js.executeScript("document.title='title changed by webdriver';");
        assertThat(driver.getTitle(), is("title changed by webdriver"));

        // this code creates a function that will persist as we have added it to the global window
        js.executeScript("window.webdrivertitlechange = function(){document.title='stored title changed by webdriver';};"+
                         "window.webdrivertitlechange.call();");
        assertThat(driver.getTitle(), is("stored title changed by webdriver"));

        // this can only work if we managed to leave javascript lying around
        js.executeScript("document.title='title changed by webdriver';");
        js.executeScript("window.webdrivertitlechange.call();");
        assertThat(driver.getTitle(), is("stored title changed by webdriver"));
    }
}
