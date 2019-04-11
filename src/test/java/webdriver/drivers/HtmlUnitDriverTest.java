package webdriver.drivers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.After;
import org.junit.AfterClass;
import webdriver.drivermanager.Driver;
import manager.ProxyPort;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *  HtmlUnitDriverTest class.
 *  Make sure you have HtmlUnitDriver in your pom.xml https://github.com/SeleniumHQ/htmlunit-driver
 */
public class HtmlUnitDriverTest {

    private static WebDriver htmlunit;

    /**
     *  basicHTMLUnitDriverBrowserVersion method.
     *  Start an HtmlUnitDriver to use headers that tell the application that I am coming from Firefox 60.
     *  There are different browser than I can use.
     */
    @Test
    public void basicHTMLUnitDriverBrowserVersion(){

        htmlunit = new HtmlUnitDriver(BrowserVersion.FIREFOX_60);
        htmlunit.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(htmlunit.getTitle(), is("HTML Form Elements"));
    }

    /**
     *  basicHTMLUnitDriverJavascriptOn method.
     *  Enable JavaScript.
     */
    @Test
    public void basicHTMLUnitDriverJavascriptOn(){

        htmlunit = new HtmlUnitDriver(true);
        htmlunit.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(htmlunit.getTitle(), is("HTML Form Elements"));
    }

    /**
     *  basicHTMLUnitDriverCapabilities method.
     *  Use capabilities.
     */
    @Test
         public void basicHTMLUnitDriverCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // setting this to false does not impact firefox
        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName("htmlunit");

        htmlunit = new HtmlUnitDriver(capabilities);
        htmlunit.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(htmlunit.getTitle(), is("HTML Form Elements"));
    }

    @Test
    public void basicHTMLUnitDriverProxyCapabilities(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            // setting this to false does not impact firefox
            capabilities.setJavascriptEnabled(true);

            org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
            proxy.setHttpProxy(Driver.PROXY)
                    .setFtpProxy(Driver.PROXY)
                    .setSslProxy(Driver.PROXY);
            capabilities.setCapability(CapabilityType.PROXY, proxy);

            htmlunit = new HtmlUnitDriver(capabilities);
            htmlunit.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
            assertThat(htmlunit.getTitle(), is("HTML Form Elements"));
        } else {
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test basicHTMLUnitDriverProxyCapabilities");
        }
    }

    @After
    public void closeBrowser() {
        htmlunit.close();
    }

    @AfterClass
    public static void quitBrowser() {
        htmlunit.quit();
    }
}
