package webdriver.examples;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *  The following code is for the FirefoxDriver.
 *  You also need to download the GeckoDriver executable:
 *  -https://github.com/mozilla/geckodriver/releases
 *
 *  You can easily install GeckoDriver on windows using chocolatey:
 *  - https://chocolatey.org/packages/selenium-gecko-driver
 *
 *  If you are on a Mac then you could install ChromeDriver using HomeBrew
 *  - http://brewformulas.org/geckodriver
 */
public class FirefoxTest {

    @Test
    public void startWebDriver(){

        String currentDir = System.getProperty("user.dir");
        String geckoDriverLocation = currentDir + "/src/tools/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", geckoDriverLocation);

        // WebDriver is used here because we may want to inject the driver. So have the driver itself instantiated
        // somewhere else because we may want our tests to run across Firefox, Chrome, Opera, HtmlUnit, or a remote
        // Web Driver. The driver may have additional commands on it, but that's for each driver to decide.
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("http://seleniumsimplified.com");

        Assert.assertTrue("title should start differently",
                driver.getTitle().startsWith("Selenium Simplified"));

        // FirefoxDriver seems to prefer either quit or close, but sometimes throws an error if you use both
        //driver.close();
        driver.quit();
    }
}
