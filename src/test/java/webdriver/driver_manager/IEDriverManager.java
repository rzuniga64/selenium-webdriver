package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 *  Factory class which returns a Internet Explorer 11 driver.
 */
public class IEDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        String RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.ie.driver", RESOURCE_DIR + "IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }
}
