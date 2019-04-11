package webdriver.drivers;

import manager.ProxyPort;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webdriver.drivermanager.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * cover nuances with the Chrome Driver
 */
public class ChromeDriverTest {

    private static WebDriver chrome;

    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        // tell webdriver where to find the chrome driver
        String RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.chrome.driver", RESOURCE_DIR + "chromedriver.exe");
    }

    /**
     *  basicChromeUsage method.
     */
    @Test
    public void basicChromeUsage(){

        chrome = new ChromeDriver();
        chrome.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(chrome.getTitle(), is("HTML Form Elements"));
    }

    /**
     *  basicChromeDriverOptions method.
     *  Chrome is supported by WebDriver, on linux it may not find the location of your browser.
     *  The 'which' command for chromium-browser may find it.
     *
     *  http://peter.sh/experiments/chromium-command-line-switches/
     */
    @Test
    public void basicChromeDriverOptions(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-plugins");
        options.addArguments("disable-extensions");

        chrome = new ChromeDriver(options);
        chrome.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(chrome.getTitle(), is("HTML Form Elements"));
    }

    /**
     *  basicChromeDriverProxy method.
     *  Set a Chrome driver proxy.
     *  http://peter.sh/experiments/chromium-command-line-switches/
     */
    @Test
    public void basicChromeDriverProxy(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("disable-plugins");
            options.addArguments("disable-extensions");
            options.addArguments("proxy-server=" + Driver.PROXY);

            chrome = new ChromeDriver(options);
            chrome.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
            assertThat(chrome.getTitle(), is("HTML Form Elements"));

        } else {
            System.out.println("No Proxy seemed to be running on " + Driver.PROXY +
                            " so didn't run test basicChromeDriverProxy");
        }
    }

    @After
    public void closeBrowser() {
        chrome.close();
    }

    @AfterClass
    public static void quitBrowser() {
        chrome.quit();
    }
}
