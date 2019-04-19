package junit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.basics.driver.ChromeTest;
import webdriver.basics.driver.FirefoxTest;
import webdriver.basics.driver.FundamentalWhatHappensIfTest;
import webdriver.basics.driver.HtmlUnitTest;
import webdriver.drivermanager.DriverTest;
import webdriver.drivers.*;
import webdriver.screenshots.PersistScreenshotsTest;
import webdriver.screenshots.ScreenshotsExampleTest;
import webdriver.screenshots.ScreenshotsExerciseTest;

/**
 * This is for tests that are browser specific and control their browser setup to run
 * i.e. they don't use the Driver class so aren't cross browser
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        // basics.driver
        ChromeTest.class,
        FirefoxTest.class,
        HtmlUnitTest.class,
        FundamentalWhatHappensIfTest.class,

        // drivermanager
        DriverTest.class,

        // because these have hardcoded paths for drivers etc. I only run this suite in the IDE, not CI
        // drivers
        ChromeDriverTest.class,
        FirefoxDriverTest.class,
        HtmlUnitDriverTest.class,
        MicrosoftEdgeDriverTest.class,
        IEDriverTest.class,
        PortableFirefoxDriverTest.class,

        // screenshots
        PersistScreenshotsTest.class,
        ScreenshotsExampleTest.class,
        ScreenshotsExerciseTest.class
})
public class DriverSanityCoursePackSuite {

}
