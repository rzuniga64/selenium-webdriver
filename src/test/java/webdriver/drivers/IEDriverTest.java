package webdriver.drivers;

import manager.ProxyPort;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IEDriverTest {

    private static WebDriver iedriver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setupTheIEDriverSystemProperty(){

        String RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.ie.driver", RESOURCE_DIR + "IEDriverServer.exe");
    }

    @Test
    public void basicIEDriverTest(){

        iedriver = new InternetExplorerDriver();
        iedriver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        new WebDriverWait(iedriver, 10).until(ExpectedConditions.titleContains("HTML Form Elements"));
        assertThat(iedriver.getTitle(), is("HTML Form Elements"));
    }

    @Test
    public void ieDriverProxyTest(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            Proxy proxy = new Proxy();
            proxy.setHttpProxy(Driver.PROXY)
                    .setFtpProxy(Driver.PROXY)
                    .setSslProxy(Driver.PROXY);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, proxy);

            iedriver = new InternetExplorerDriver(capabilities);
            iedriver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
            assertThat(iedriver.getTitle(), is("HTML Form Elements"));
        } else {
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test ieDriverProxyTest");
        }
    }

    @After
    public void closeBrowser() {
        //iedriver.close();
    }

    @AfterClass
    public static void quitBrowser() {
        //iedriver.quit();
    }
}
