package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *  Factory class which returns a HTML Unit driver.
 */
public class HtmlUnitDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        // HtmlUnitDriver added as a maven dependency - no paths required
        return new HtmlUnitDriver();  // enable javascript
    }

    @Override
    protected void startService() { }

    @Override
    protected void stopService() { }
}
