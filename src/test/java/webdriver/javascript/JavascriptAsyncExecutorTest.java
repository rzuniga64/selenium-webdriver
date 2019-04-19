package webdriver.javascript;

import webdriver.drivermanager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 *  JavascriptAsyncExecutorTest class.
 *
 *  When executeAsyncScript is called an addition final arguments added into the script by WebDriver as a final
 *  argument, a callback function to signal that async execution has finished.
 *  - “var callback = arguments[arguments.length = 1];”
 *  Any argument you pass to the callback function will be returned to WebDriver
 *  - HTML Element == WebElement, number == Long etc.
 *  Call it expecting an Object and cast appropriately
 *  SetScriptTimeout
 *  - driver.manage().timeouts.setScriptTimeout(10, TimeUnit.SECONDS);
 */
public class JavascriptAsyncExecutorTest {

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
     *  syncOnAjaxGifRemovalViaAsync method.
     *  for hints see http://stackoverflow.com/questions/2857900/onhide-type-event-in-
     *
     *  add a function to the global space. When executeAsyncScript is called an addition final arguments added into
     *  the script by WebDriver as a final argument, a callback function to signal that async execution has finished.
     *  Get the JQuery hide function into a variable. Then make this the  new JQuery hide function which calls the
     *  old JQuery hide function. Once it is hidden my WebDriver callback function will be called.
     */
    @Test
    public void syncOnAjaxGifRemovalViaAsync(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        // Set the execute timeout. We have to do this before we execute the async script. Otherwise it will time out
        // right away.
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        js.executeScript("window.webdrivercallback = function(){};" +
                //extend the jQuery hide method to call our callback when it hides the gif
                "var _oldhide = $.fn.hide;" +
                "$.fn.hide = function(speed, callback) {" +
                "    var retThis = _oldhide.apply(this,arguments);" +
                "    window.webdrivercallback.apply();" +
                "    return retThis;" +
                "};"
        );

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // need to wait in here, can do it with async javascript
        js.executeAsyncScript("window.webdrivercallback = arguments[arguments.length - 1];");

        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = new WebDriverWait(driver,10).until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }
}