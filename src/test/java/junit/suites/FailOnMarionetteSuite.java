package junit.suites;

import com.seleniumsimplified.webdriver.basics.manipulate.SendKeysExamplesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.selectSupport.SelectSupportTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsExampleTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsExercisesTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
Tests which should run, and pass, across any of the non-headless browsers without amendment

Marionette is still a work in progress

mvn test -Dtest=FailOnMarionetteSuite -Dselenium2basics.webdriver=FIREFOXMARIONETTE


* 20161014 Status
    * Marionette v 0.11.1 && Firefox 49.0.1 the tests in this suite fail

* 20160728 Status
  * WebDriver 2.53.1 && Marionette v 0.9.0 (64 bit) && Firefox 47.0.1 - 66 tests failed using AllBrowserSuite
  * The tests in this suite fail
*/
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite


        // Interrogate

        // manipulate alerts
        // the basicPromptTestsFail with no such element exception
        AlertHandlingExercisesTest.class,

        // Manipulate Frames

        // Manipulate select Support
        SelectSupportTest.class,


        // Manipulate
        SendKeysExamplesTest.class,

        // Navigation

        // cookies
        CookiesExercisesTest.class,


        // data driven tests

        // drivermanger in DriverSanityCoursePack

        // Javascript



        // mobile in its own suite

        // remote in its own suite

        // screenshots in DriverSanityCoursePack

        //Synchronisation  conditions

        //Synchronisation  fluently

        //Synchronisation  fluentWiat

        //Synchronisation  implicitWait

        //Synchronisation  refactored

        //Synchronisation  webDriverWaitBasics

        //userinteractions
        UserInteractionsExercisesTest.class,

        // moved to end of suite because this causes GeckoDriver to lose connection
        // manipulate windows
        WindowsExampleTest.class,
        WindowsExercisesTest.class,
})
public class FailOnMarionetteSuite {
}
