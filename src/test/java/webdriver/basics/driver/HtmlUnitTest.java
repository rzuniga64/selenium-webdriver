package webdriver.basics.driver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * We need to create a driver before we can do anything.
 * HtmlUnitDriver is a headless browser implemented using HtmlUnit
 * http://htmlunit.sourceforge.net/
 *
 * Advantages:
 * - Fast
 * - good for simple testing
 *
 * Disadvantages:
 * - does not handle JavaScript as well as a full browser - but it is getting better
 * - you need to add HtmlUnitDriver to your pom.xml
 *   https://github.com/SeleniumHQ/htmlunit-driver
 *
 <dependency>
 <groupId>org.seleniumhq.selenium</groupId>
 <artifactId>htmlunit-driver</artifactId>
 <version>${htmlunitdriver.version}</version>
 </dependency>
 *
 */
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

        // don't need to close an HtmlUnitDriver, garbage collection will take care of it.
    }
}
