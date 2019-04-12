package webdriver.remote;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  GridTest class.
 *  1. On the command line navigate to  C:\Users\Owner\git\selenium-webdriver\src\test\resources\gridconfig\win.
 *  2. Open starthub.bat and startnode and set the correct version of selenium-standalone jar.
 *  3. On the command line type: starthub. This will start the server we will be connecting to with our tests.
 *  4. On another command line type: startnode. This will register a node with the hub. The node starts on a port and
 *     starts polling.
 *  5. In a browser type: http://localhost:4444/grid/console. The grid console is for the hub. You can see that that
 *     there is a node that is available for me to test. I can test against IE, Firefox, and Chrome.
 *
 *  Create your test and run it. You can run GridTest as an example. It runs on Windows/Firefox by default.
 *  If you want to run on Chrome we have to tell the node about the Chrome driver. You can set it up in your tests.
 *  You can either set up the properties on the machine that the node is running on or we can pass it in through the
 *  command line. So you have to configure the node and tell it where the chrome driver is.
 */
public class GridTest {

    public static WebDriver driver = null;

    @BeforeClass
    public static void connectToGrid(){

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("platform", Platform.WINDOWS);

        try {
            // add url to environment variables to avoid releasing with source
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleInteraction(){
       driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        assertFalse("Starts not selected", checkBox1.isSelected());
        checkBox1.click();
        assertTrue("Click selects", checkBox1.isSelected());
    }

    @AfterClass
    public static void stopGrid(){
        driver.quit();
    }
}