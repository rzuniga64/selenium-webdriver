package webdriver.basics.driver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *  The following code is for the Chrome Driver.
 *  You also need to download the ChromeDriver executable:
 *  - https://sites.google.com/a/chromium.org/chromedriver/
 *
 *  You can easily install Chromedriver on windows using chocolatey:
 *  - https://chocolatey.org/packages?q=ChromeDriver
 *
 *  If you are on a Mac then you could install ChromeDriver using HomeBrew
 *  - http://brewformulas.org/Chromedriver
 */

public class ChromeTest {

    @Test
    public void startWebDriver() {

        String currentDir = System.getProperty("user.dir");
        String chromeDriverLocation = currentDir + "/src/tools/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        // WebDriver is used here because we may want to inject the driver. So have the driver itself instantiated
        // somewhere else because we may want our tests to run across Firefox, Chrome, Opera, HtmlUnit, or a remote
        // Web Driver. The driver may have additional commands on it, but that's for each driver to decide.
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://seleniumsimplified.com");

        Assert.assertTrue("title should start differently",
                driver.getTitle().startsWith("Selenium Simplified"));

        driver.close();
        driver.quit();
    }
}
