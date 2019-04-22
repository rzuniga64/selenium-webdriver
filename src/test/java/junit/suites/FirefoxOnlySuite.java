package junit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.basics.manipulation.examples.ManipulateExampleTest;

/**
 * A simple suite that contains all the passing Firefox tests
 *
 * Can run the suite with
 *
 * mvn clean test -Dtest=FirefoxOnlySuite -Dselenium2basics.webdriver=FIREFOX
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        ManipulateExampleTest.class,
})
public class FirefoxOnlySuite {
}
