package webdriver.javascript;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

/**
 *  JavascriptExecuteAsyncExerciseTest class.
 *
 *  Look at the JavaDoc help for executeAsyncScript
 *  - Create a test for the sleep sample code
 *  - Use the XMLHttpRequest sample to call /selenium/ajaxselect.php which is the server method which responds to the
 *    Ajax call on the app. You will need to add an id parameter to call e.g. id=2.
 */
public class JavascriptExecuteAsyncExerciseTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Before
    public void resetBeforeTest(){

        driver.navigate().refresh();
    }

    /**
     *  waitInBrowserForTimeSample method.
     *  Look at the JavaDoc help for executeAsyncScript
     *  - Create a test for the sleep sample code
     */
    @Test
    public void waitInBrowserForTimeSample(){

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        assertTrue("Elapsed time should be greater than 500 milli",
                (System.currentTimeMillis() - start) > 500);
    }

    /**
     *  useXMLHttpRequest method.
     *
     *  Look at the JavaDoc help for executeAsyncScript
     *  - Use the XMLHttpRequest sample to call /selenium/ajaxselect.php which is the server method which responds to the
     *    Ajax call on the app. You will need to add an id parameter to call e.g. id=2.
     */
    @Test
    public void useXMLHttpRequest(){

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        Object response = ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  }" +
                        "};" +
                        "xhr.send();");

        System.out.println((String)response);
        assertThat((String) response, containsString("{optionValue:10, optionDisplay: 'C++'}"));
    }
}


