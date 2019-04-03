package junit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitTest {

    @Test
    public void startWebDriver(){

        // WebDriver is used here because we may want to inject the driver. So have the driver itself instantiated
        // somewhere else because we may want our tests to run across Firefox, Chrome, Opera, HtmlUnit, or a remote
        // Web Driver. The driver may have additional commands on it, but that's for each driver to decide.
        final WebDriver driver = new HtmlUnitDriver();
        driver.navigate().to("http://seleniumsimplified.com");

        Assert.assertTrue("title should start differently",
                driver.getTitle().startsWith("Selenium Simplified"));

        driver.quit();
    }
}
