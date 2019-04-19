package junit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.basics.navigation.NavigationBasicsTest;
import webdriver.basics.navigation.NavigationExampleTest;

/**
 * A simple suite that contains just the Navigation tests
 * as an example of how to collate specific Test Classes into
 * a Suite
 *
 * Can run the suite with
 *
 * mvn clean -Dtest=InterrogateOnlySuite test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        // Navigation
        NavigationBasicsTest.class,
        NavigationExampleTest.class

})
public class NavigateOnlySuite {
}
