package junit.suites;

import com.seleniumsimplified.webdriver.basics.interrogate.findby.*;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExampleSelectDropDownFiveTest;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExercisesSubmitFileTest;
import com.seleniumsimplified.webdriver.basics.manipulate.SendKeysExamplesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowManageExerciseTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.WaitingExercisesTest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests which run, on HTMLUnit

 mvn test -Dtest=FailOnHTMLUnitSuite -Dselenium2basics.webdriver=HTMLUNIT

 * 20160728 Status - WebDriver 2.53.1 && HtmlUnit v 2.21
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate FindBy
        AFirstFindByExampleTest.class,
        FindByCssSelectorExampleTest.class,
        FindByIDOrNameExampleTest.class,
        FindByXpathExampleTest.class,
        FindElementsExercisesTest.class,
        FirstFindByExercisesTest.class,


        // Interrogate

        // manipulate alerts
        AlertHandlingExercisesTest.class,

        // Manipulate Frames

        // Manipulate select Support

        // manipulate windows
         WindowManageExerciseTest.class,

        // Manipulate
         ManipulateExercisesSubmitFileTest.class,
         ManipulateExampleSelectDropDownFiveTest.class,
         SendKeysExamplesTest.class,

        // Navigation

        // cookies

        // data driven tests

        // drivermanger in DriverSanityCoursePack

        // Javascript


        // mobile in its own suite

        // Page Objects

        // remote in its own suite

        // screenshots in DriverSanityCoursePack

        //Synchronisation  conditions
        WaitingExercisesTest.class,

        //Synchronisation  fluently

        //Synchronisation  fluentWiat

        //Synchronisation  implicitWait

        //Synchronisation  refactored

        //Synchronisation  webDriverWaitBasics

        //userinteractions
         UserInteractionsExercisesTest.class,
})
public class FailOnHTMLUnitSuite {
}
