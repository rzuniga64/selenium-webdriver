package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 *  Factory class which returns a Opera driver.
 */
public class OperaDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.edge.driver", RESOURCE_DIR + "operadriver.exe");
        return new OperaDriver();
    }

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }
}
