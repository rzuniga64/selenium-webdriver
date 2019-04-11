package webdriver.drivers;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import webdriver.drivermanager.Driver;
import manager.ProxyPort;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *  FirefoxDriverTest class.
 *  If we don't tell webdriver where to find the chrome driver it will look on the path.
 */
public class FirefoxDriverTest {

    private static WebDriver firefox;

    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        firefox = new FirefoxDriver();
    }

    /**
     *  basicFirefoxDriver method.
     */
    @Test
    public void basicFirefoxDriver(){

        firefox.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(firefox.getTitle(), is("HTML Form Elements"));
    }

    /**
     *  firefoxDriverWithProfile method.
     */
    @Test
    public void firefoxDriverWithProfile(){

        FirefoxProfile profile = new FirefoxProfile();

        // now need to use options to set the profile (3.12.0)
        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        firefox = new FirefoxDriver(options);

        firefox.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(firefox.getTitle(), is("HTML Form Elements"));
    }

    /**
     *  firefoxDriverWithCapabilitiesForProxy method.
     *  Slightly newer.
     */
    @Test
    public void firefoxDriverWithCapabilitiesForProxy(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
            proxy.setHttpProxy(Driver.PROXY)
                 .setFtpProxy(Driver.PROXY)
                 .setSslProxy(Driver.PROXY);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, proxy);

            firefox = new FirefoxDriver(capabilities);
            firefox.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
            assertThat(firefox.getTitle(), is("HTML Form Elements"));
        } else {
            System.out.println(
                    "No Proxy seemed to be running on " +
                    Driver.PROXY +
                    " so didn't run test firefoxDriverWithCapabilitiesForProxy");
        }
    }

    /**
     *  firefoxUseExtensions method.
     *  @throws IOException throws IOException
     */
    @Test
    public void firefoxUseExtensions() throws IOException {

        // **************************************************************************
        // profile is good for setting preferences and fiddling with browser settings
        // **************************************************************************

        // Download the extension to a local folder
        String s = File.separator;
        String extensionPath = System.getProperty("user.dir") +
                                String.format("%ssrc%stest%sresources%s%s",s,s,s,s,"firebug-1.10.5-fx.xpi");

        System.out.println(extensionPath);
        FirefoxProfile profile = new FirefoxProfile();

        // Stop firebug showing the first run screen by setting the last version to the current one.
        profile.setPreference("extensions.firebug.currentVersion", "1.10.5");

        // add the extension to firefox
        profile.addExtension(new File(extensionPath));

        // prior to 3.12.0 we could set the profile
        //WebDriver firefox = new FirefoxDriver(profile);

        // in 3.12.0 we use the option
        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        WebDriver firefox = new FirefoxDriver(options);

        firefox.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(firefox.getTitle(), is("HTML Form Elements"));
    }

    @After
    public void closeBrowser() {
        firefox.close();
    }

    @AfterClass
    public static void quitBrowser() {
        //firefox.quit();
    }
}
