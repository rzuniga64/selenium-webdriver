package webdriver.driver_manager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static webdriver.driver_manager.EnvironmentPropertyReader.getPropertyOrEnv;

/**
 *  Factory class which returns a Appium driver.
 */
public class AppiumDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        WebDriver driver = null;

        // Quick hack code to get Appium running.
        // Only one env variable, your APPIUM_DEVICE_NAME so amend code for your local version.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // The device can be seen when you do 'adb devices'.
        capabilities.setCapability("deviceName", getPropertyOrEnv("APPIUM_DEVICE_NAME", ""));
        capabilities.setCapability("platformName", Platform.ANDROID);
        capabilities.setCapability("APPIUM_BROWSER", "browser");

        try {
            // add url to environment variables to avoid releasing with source
            String appiumURL = "http://127.0.0.1:4723/wd/hub";
            driver = new RemoteWebDriver(new URL(appiumURL), capabilities);
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
