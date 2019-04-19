package junit.suites;

import com.seleniumsimplified.webdriver.basics.interrogate.findby.FirstFindByExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExampleSelectDropDownFiveTest;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExercisesSubmitFormTest;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulatetExampleMultiSelectTest;
import com.seleniumsimplified.webdriver.basics.manipulate.SendKeysExamplesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.frames.FramesExampleTest;
import com.seleniumsimplified.webdriver.basics.manipulate.frames.FramesExercisesRefactoredDeleteTest;
import com.seleniumsimplified.webdriver.basics.manipulate.frames.FramesExercisesRefactoredTest;
import com.seleniumsimplified.webdriver.basics.manipulate.frames.FramesExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.selectSupport.SelectSupportTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsExampleTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsExercisesTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTest;
import com.seleniumsimplified.webdriver.datadriven.BasicDataDrivenTest;
import com.seleniumsimplified.webdriver.datadriven.CsvDataDrivenTest;
import com.seleniumsimplified.webdriver.javascript.JavaScriptExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptAsyncExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecuteAsyncExerciseTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecutorExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.CustomExpectedConditionsExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.InlineExpectedConditionExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.WaitingExercisesTest;
import com.seleniumsimplified.webdriver.synchronisation.implicitWait.ImplicitWaitTest;
import com.seleniumsimplified.webdriver.synchronisation.refactored.MakeYourWaitsReadableTest;
import com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics.SynchronisationWithWebDriverWaitTest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
Tests which should run, and pass, across any of the non-headless browsers without amendment

Marionette is still a work in progress

mvn test -Dtest=FailOnMarionetteSuite -Dselenium2basics.webdriver=FIREFOXMARIONETTE


* 20160728 Status
  * WebDriver 2.53.1 && Marionette v 0.9.0 (64 bit) && Firefox 47.0.1 - 66 tests failed using AllBrowserSuite
  * The tests in this suite fail
*/
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate FindBy
        FirstFindByExercisesTest.class,


        // Interrogate

        // manipulate alerts
        AlertHandlingExercisesTest.class,

        // Manipulate Frames

        FramesExampleTest.class,
        FramesExercisesRefactoredDeleteTest.class,
        FramesExercisesRefactoredTest.class,
        FramesExercisesTest.class,

        // Manipulate select Support
        SelectSupportTest.class,

        // manipulate windows
        WindowsExampleTest.class,
        WindowsExercisesTest.class,

        // Manipulate
        ManipulateExampleSelectDropDownFiveTest.class,
        ManipulateExercisesSubmitFormTest.class,
        ManipulatetExampleMultiSelectTest.class,
        SendKeysExamplesTest.class,

        // Navigation

        // cookies
        CookiesExercisesTest.class,


        // data driven tests
        BasicDataDrivenTest.class,
        CsvDataDrivenTest.class,

        // drivermanger in DriverSanityCoursePack

        // Javascript

        JavascriptAsyncExecutorTest.class,
        JavascriptExecutorExampleTest.class,
        JavaScriptExecutorTest.class,
        JavascriptExecuteAsyncExerciseTest.class,

        // mobile in its own suite

        // Page Objects
        com.seleniumsimplified.webdriver.pageobjects.loadablecomponent.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.refactorExampleOneExercise.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.slowloadablecomponent.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.usingpagefactory.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.WithoutPageObjectsTest.class,

        // remote in its own suite

        // screenshots in DriverSanityCoursePack

        //Synchronisation  conditions
        CustomExpectedConditionsExampleTest.class,
        InlineExpectedConditionExampleTest.class,
        WaitingExercisesTest.class,

        //Synchronisation  fluently

        //Synchronisation  fluentWiat

        //Synchronisation  implicitWait
        ImplicitWaitTest.class,

        //Synchronisation  refactored
        MakeYourWaitsReadableTest.class,

        //Synchronisation  webDriverWaitBasics
        SynchronisationWithWebDriverWaitTest.class,

        //userinteractions
        UserInteractionsExercisesTest.class,
})
public class FailOnMarionetteSuite_v0_9_0 {
}
