package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class FirefoxPortableDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        String RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("seleniumsimplified.firefoxportable", RESOURCE_DIR + "FirefoxPortable.exe");
        String property = System.getProperty("seleniumsimplified.firefoxportable");

        DesiredCapabilities portableCapabilities = DesiredCapabilities.firefox();
        portableCapabilities.setCapability("marionette", false);
        portableCapabilities.setCapability("firefox_binary", new File(RESOURCE_DIR + "FirefoxPortable.exe"));
        return new RemoteWebDriver(portableCapabilities);
    }

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }
}
