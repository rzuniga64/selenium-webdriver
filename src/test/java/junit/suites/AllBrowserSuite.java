package junit.suites;

import junit.JUnitBeforeAndAfterTest;
import junit.JUnitExercisesTest;
import junit.MyClassTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.basics.interrogation.DriverInterrogateRefactoredTest;
import webdriver.basics.interrogation.DriverInterrogateTest;
import webdriver.basics.interrogation.GetTitleReplacementExerciseTest;
import webdriver.basics.interrogation.WebElementInterrogationTest;
import webdriver.basics.interrogation.examples.*;
import webdriver.basics.interrogation.exercises.*;
import webdriver.basics.manipulation.alerts.AlertHandlingExampleTest;
import webdriver.basics.manipulation.alerts.AlertHandlingExercisesTest;
import webdriver.basics.manipulation.examples.ManipulateExampleTest;
import webdriver.basics.manipulation.examples.ManipulateExampleTest2;
import webdriver.basics.manipulation.exercises.*;
import webdriver.basics.manipulation.frames.FrameFaqTest;
import webdriver.basics.manipulation.frames.FramesExampleTest;
import webdriver.basics.manipulation.frames.FramesExercisesRefactoredTest;
import webdriver.basics.manipulation.frames.FramesExercisesTest;
import webdriver.basics.manipulation.selectSupport.SelectSupportTest;
import webdriver.basics.manipulation.windows.WindowManageExerciseTest;
import webdriver.basics.manipulation.windows.WindowsExampleTest;
import webdriver.basics.manipulation.windows.WindowsExercisesTest;
import webdriver.basics.manipulation.windows.WindowsManageExampleTest;
import webdriver.basics.navigation.NavigationBasicsTest;
import webdriver.basics.navigation.NavigationExampleTest;
import webdriver.cookies.CookiesExampleTest;
import webdriver.cookies.CookiesExercisesTest;
import webdriver.cookies.CookiesExercisesTestWorkWithExtraSync;
import webdriver.datadriven.CsvDataDrivenTest;
import webdriver.datadriven.ObjectArrayDataDrivenTest;
import webdriver.javascript.JavaScriptExecutorTest;
import webdriver.javascript.JavascriptAsyncExecutorTest;
import webdriver.javascript.JavascriptExecuteAsyncExerciseTest;
import webdriver.javascript.JavascriptExecutorExampleTest;
import webdriver.screenshots.PersistScreenshotsTest;
import webdriver.screenshots.ScreenshotsExampleTest;
import webdriver.screenshots.ScreenshotsExerciseTest;
import webdriver.synchronization.conditions.*;
import webdriver.synchronization.fluentWait.FluentWaitExampleTest;
import webdriver.synchronization.fluentWait.FluentWaitExercisesTest;
import webdriver.synchronization.fluentWait.FluentWaitForWebElementExampleTest;
import webdriver.synchronization.fluently.UseWebDriverWaitFluentlyTest;
import webdriver.synchronization.implicitWait.ImplicitWaitTest;
import webdriver.synchronization.refactored.MakeYourWaitsReadableTest;
import webdriver.synchronization.webDriverWaitBasics.SynchronisationWithWebDriverWaitTest;
import webdriver.synchronization.webDriverWaitBasics.WebDriverWaitExampleTest;
import webdriver.userinteractions.UserInteractionsExercisesTest;

/**
 * Tests which should run, and pass, across any of the non-headless browsers without amendment
 * ]
 * mvn test -Dtest=AllBrowserSuite -Dselenium2basics.webdriver=<insertbrowserhere>
 *
 * 20160728 Status - WebDriver 2.53.1
 * - FirefoxDriver && Firefox 47.0.1 - all tests passed
 * - ChromeDriver v2.22.397933 && Chrome Version 52.0.2743.82 m - all tests passed
 * - IEDriverServer (32 bit) v2.53.1 - 1 test failed (frames related)
 * - HTMLUnitDriver v 2.21 - 27 tests failed
 *    - see HTMLUnitSuite and FailOnHTMLUnitSuite for more details
 * Marionette and Edge are still works in progress so not all of the tests work on those yet
 * - Marionette v 0.9.0 (64 bit) && Firefox 47.0.1 - 66 tests failed using AllBrowserSuite
 *    - see MarionetteSuite and FailOnMarionetteSuite to see Marionette/Gecko status
 * - Edge - Alert tests halt the execution of suite so not run against Edge
 *    - see EdgeSuite and FailOnEdgeSuite to see edge status
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit
        JUnitBeforeAndAfterTest.class,
        MyClassTest.class,
        JUnitExercisesTest.class,

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate Examples
        FindByExampleTest.class,
        FindByChainingExampleTest.class,
        FindByCssSelectorExampleTest.class,
        FindByCSSSelectorPathsExampleTest.class,
        FindByXpathExampleTest.class,
        FindByIDOrNameExampleTest.class,
        FindElementsExampleTest.class,

        // Interrogate Exercises
        FindByCSSSelectorBasicExercisesTest.class,
        FindByCSSSelectorBasicExercises2Test.class,
        FindByXPathSelectorBasicExerciseTest.class,
        FindElementsExercisesTest.class,
        FirstFindByExercisesTest.class,

        // Interrogate
        DriverInterrogateRefactoredTest.class,
        DriverInterrogateTest.class,
        GetTitleReplacementExerciseTest.class,
        WebElementInterrogationTest.class,

        // manipulate alerts
        AlertHandlingExampleTest.class,
        AlertHandlingExercisesTest.class,

        // Manipulate Alerts
        AlertHandlingExampleTest.class,
        AlertHandlingExercisesTest.class,

        // Manipulate Examples
        ManipulateExampleTest.class,
        ManipulateExampleTest2.class,
        webdriver.basics.manipulation.examples.WebDriverWaitExampleTest.class,

        // Manipulate Frames
        FrameFaqTest.class,
        FramesExampleTest.class,
        FramesExercisesRefactoredTest.class,
        FramesExercisesTest.class,

        // Manipulate select Support
        SelectSupportTest.class,

        // manipulate windows
        WindowManageExerciseTest.class,
        WindowsExampleTest.class,
        WindowsExercisesTest.class,
        WindowsManageExampleTest.class,

        // Manipulate Exercises
        ManipulateClearTest.class,
        ManipulateExerciseDropDownTest.class,
        ManipulateExercisesCheckboxTest.class,
        ManipulateExercisesCommentsTest.class,
        ManipulateExercisesSelectRadioTest.class,
        ManipulateExercisesSubmitFileTest.class,
        ManipulateExercisesSubmitFormTest.class,
        ManipulateSelectSupportTest.class,
        ManipulatetExerciseMultiSelectTest.class,
        SendKeysExamplesTest.class,

        // Navigation
        NavigationBasicsTest.class,
        NavigationExampleTest.class,

        // cookies
        CookiesExampleTest.class,
        CookiesExercisesTest.class,
        CookiesExercisesTestWorkWithExtraSync.class,

        // data driven tests
        ObjectArrayDataDrivenTest.class,
        CsvDataDrivenTest.class,

        // drivermanger in DriverSanityCoursePack
        // Javascript

        JavascriptAsyncExecutorTest.class,
        JavascriptExecuteAsyncExerciseTest.class,
        JavascriptExecutorExampleTest.class,
        JavaScriptExecutorTest.class,

        // mobile in its own suite
        // Page Objects
        webdriver.pageobjects.loadablecomponent.BasicTestsRefactored.class,
        webdriver.pageobjects.refactorExampleOne.BasicTestsRefactored.class,
        webdriver.pageobjects.refactorExampleOneExercise.BasicTestsRefactored.class,
        webdriver.pageobjects.slowloadablecomponent.BasicTestsRefactored.class,
        webdriver.pageobjects.slowloading.SlowLoadingPageExampleTest.class,
        webdriver.pageobjects.usingpagefactory.BasicTestsRefactored.class,
        webdriver.pageobjects.WithoutPageObjectsTest.class,

        // remote in its own suite
        // screenshots in DriverSanityCoursePack
        PersistScreenshotsTest.class,
        ScreenshotsExampleTest.class,
        ScreenshotsExerciseTest.class,

        //Synchronisation  conditions
        CustomExpectedConditionsExampleTest.class,
        InlineExpectedConditionExampleTest.class,
        WaitingExercisesTest.class,
        WaitingExercisesUsingPredicateTest.class,
        WebDriverWaitFunctionAndExpectedConditionsExampleTest.class,

        //Synchronisation  fluently
        UseWebDriverWaitFluentlyTest.class,

        //Synchronisation  fluentWiat
        FluentWaitExampleTest.class,
        FluentWaitExercisesTest.class,
        FluentWaitForWebElementExampleTest.class,

        //Synchronisation  implicitWait
        ImplicitWaitTest.class,

        //Synchronisation  refactored
        MakeYourWaitsReadableTest.class,

        //Synchronisation  webDriverWaitBasics
        SynchronisationWithWebDriverWaitTest.class,
        WebDriverWaitExampleTest.class,

        //userinteractions
        UserInteractionsExercisesTest.class,
})
public class AllBrowserSuite {
}
