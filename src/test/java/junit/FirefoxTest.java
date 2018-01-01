package junit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxTest {

    @Test
    public void startWebDriver(){


        /**  NOTE:
         * This test will work on
         *     - WebDriver 2.53.1
         *     - Firefox v < 48
         * And on
         *     - WebDriver 3
         *     - Firefox 48+
         *     - with geckodriver.exe on the path
         */
        String currentDir = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.manager", currentDir + "\\src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("http://seleniumsimplified.com");

        Assert.assertTrue("title should start differently",
                            driver.getTitle().startsWith("Selenium Simplified"));

        // For GeckoDriver 0.19 and Firefox 55.0.3, if I close then I get an error, so I decided to just quit instead
        //manager.close();
        driver.quit();
    }
}
