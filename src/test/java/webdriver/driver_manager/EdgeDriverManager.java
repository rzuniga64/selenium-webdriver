package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        System.setProperty("webdriver.edge.driver", RESOURCE_DIR + "MicrosoftWebDriver.exe");
        return new EdgeDriver();
    }

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }
}
