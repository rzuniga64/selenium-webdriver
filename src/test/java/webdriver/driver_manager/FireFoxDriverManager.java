package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.gecko.driver", RESOURCE_DIR + "geckodriver.exe");

        return new FirefoxDriver();
    }

    @Override
    protected void startService() { }

    @Override
    protected void stopService() { }
}
