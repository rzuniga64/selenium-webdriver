package webdriver.basics.driver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *  The following code is for the Gecko Driver.
 *  You also need to download the GeckoDriver executable:
 *  - https://github.com/mozilla/geckodriver/releases
 *
 *  You can easily install Geckodriver on windows using chocolatey:
 *  - https://chocolatey.org/packages?q=GeckoDriver
 *
 *  If you are on a Mac then you could install ChromeDriver using HomeBrew
 *  - http://brewformulas.org/Geckodriver
 */

public class FirefoxTest {

    @Test
    public void startWebDriver() {

        String currentDir = System.getProperty("user.dir");
        String firefoxDriverLocation = currentDir + "/src/tools/firefoxdriver.exe";
        System.setProperty("webdriver.chrome.driver", firefoxDriverLocation);

        // WebDriver is used here because we may want to inject the driver. So have the driver itself instantiated
        // somewhere else because we may want our tests to run across Firefox, Chrome, Opera, HtmlUnit, or a remote
        // Web Driver. The driver may have additional commands on it, but that's for each driver to decide.
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("http://seleniumsimplified.com");

        Assert.assertTrue("title should start differently",
                driver.getTitle().startsWith("Selenium Simplified"));

        //driver.close();
        driver.quit();
    }
}
