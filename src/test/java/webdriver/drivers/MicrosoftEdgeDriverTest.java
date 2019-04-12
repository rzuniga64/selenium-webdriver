package webdriver.drivers;

import manager.ProxyPort;
import org.junit.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 *  MicrosoftEdgeDriverTest class.
 *  Microsoft Edge Driver:
 *  Where to get it: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
 *  What it currently supports:
 *  https://developer.microsoft.com/en-us/microsoft-edge/platform/documentation/webdriver-commands/
 *
 *  Webdriver comes with a default wrapper for MS Edge.
 *  This will either use property "webdriver.edge.driver" to find the .exe of the service or will look for
 *  MicrosoftWebDriver.exe on the System Path.
 *  Any custom options for EdgeDriver can be found in the EdgeOptions class or use capabilities
 */
public class MicrosoftEdgeDriverTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){

        String RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.ie.driver", RESOURCE_DIR + "MicrosoftWebDriver.exe");
    }

    @Test
    public void useWebDriverDefaultWrapper(){

        driver = new EdgeDriver();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/testpages/");

        // found that Edge didn't always synchronise on page load properly so I added a wait for title
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Selenium"));
        assertEquals("Selenium Test Pages", driver.getTitle());
    }

    @Ignore("Edge does not seem to support proxy yet https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/5468949/")
    @Test
    public void basicEdgeProxy(){

        //run this only if proxy is running on port 8888 e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            Proxy proxy = new Proxy().setHttpProxy(Driver.PROXY); // e.g. "localhost:8888"
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, proxy);

            WebDriver driver = new EdgeDriver(capabilities);

            driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

            assertThat(driver.getTitle(), is("HTML Form Elements"));
        } else {
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test proxy on Edge");
        }
    }

    @After
    public void closeBrowser() {
        //driver.close();
    }

    @AfterClass
    public static void quitBrowser() {
        //driver.quit();
    }
}
