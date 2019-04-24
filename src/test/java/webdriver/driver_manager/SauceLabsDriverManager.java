package webdriver.driver_manager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *  Factory class which returns a SauceLabs driver.
 */
public class SauceLabsDriverManager extends DriverManager {
    @Override
    protected WebDriver createDriver() {

        WebDriver driver = null;

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "5");
        capabilities.setCapability("platform", Platform.WIN10);
        try {
            // add URL to environment variables to avoid releasing with source
            String saucelabURL = System.getenv("SAUCELABS_URL");
            driver = new RemoteWebDriver(new URL(saucelabURL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }
}
