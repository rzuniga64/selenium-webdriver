package junit.suites;

import com.seleniumsimplified.junit.JUnitBeforeAndAfterTest;
import com.seleniumsimplified.junit.JUnitExampleTest;
import com.seleniumsimplified.junit.JUnitExercisesTest;
import com.seleniumsimplified.webdriver.basics.interrogate.DriverInterrogateRefactoredTest;
import com.seleniumsimplified.webdriver.basics.interrogate.DriverInterrogateTest;
import com.seleniumsimplified.webdriver.basics.interrogate.GetTitleReplacementExerciseTest;
import com.seleniumsimplified.webdriver.basics.interrogate.WebElementInterrogationTest;
import com.seleniumsimplified.webdriver.basics.interrogate.findby.*;
import com.seleniumsimplified.webdriver.basics.manipulate.*;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExampleTest;
import com.seleniumsimplified.webdriver.basics.manipulate.frames.*;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowManageExerciseTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsManageExampleTest;
import com.seleniumsimplified.webdriver.basics.navigation.NavigationBasicsTest;
import com.seleniumsimplified.webdriver.basics.navigation.NavigationExampleTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExampleTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTestWorkWithExtraSync;
import com.seleniumsimplified.webdriver.datadriven.BasicDataDrivenTest;
import com.seleniumsimplified.webdriver.datadriven.CsvDataDrivenTest;
import com.seleniumsimplified.webdriver.javascript.JavaScriptExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptAsyncExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecuteAsyncExerciseTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecutorExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.*;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitExercisesTest;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitForWebElementExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.fluently.UseWebDriverWaitFluentlyTest;
import com.seleniumsimplified.webdriver.synchronisation.implicitWait.ImplicitWaitTest;
import com.seleniumsimplified.webdriver.synchronisation.refactored.MakeYourWaitsReadableTest;
import com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
Tests which should run, and pass, across any of the non-headless browsers without amendment

Marionette is still a work in progress

mvn test -Dtest=MarionetteSuite -Dselenium2basics.webdriver=FIREFOXMARIONETTE

* 20161014 status
  * WebDriver 2.53.1 && Marionette v 0.11.1 (64 bit) && Firefox 49.0.1 - 27 tests failed using AllBrowserSuite
      * The Alert tests and Select test in here fail on 2.53.1 with Marionette v 0.11.1
  * WebDriver 3.0.0 && Marionette v 0.11.1 (64 bit) && Firefox 49.0.1 - 25 tests failed using AllBrowserSuite
      * All tests here pass on WebDriver 3.0.0 with Marionette v 0.11.1

* 20160728 Status
  * WebDriver 2.53.1 && Marionette v 0.9.0 (64 bit) && Firefox 47.0.1 - 66 tests failed using AllBrowserSuite
  * Commented out the tests which fail and created FailOnMarionetteSuite

*/
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit
        JUnitBeforeAndAfterTest.class,
        JUnitExampleTest.class,
        JUnitExercisesTest.class,

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate FindBy
        AFirstFindByExampleTest.class,
        ChainingFindByExampleTest.class,
        FindByCSSSelectorBasicExercisesFullAnswersTest.class,
        FindByCSSSelectorBasicExercisesTest.class,
        FindByCssSelectorExampleTest.class,
        FindByCSSSelectorPathsExampleTest.class,
        FindByIDOrNameExampleTest.class,
        FindByXpathExampleTest.class,
        FindByXPathSelectorBasicExercisesFullAnswersTest.class,
        FindElementsExampleTest.class,
        FindElementsExercisesTest.class,
        FirstFindByExercisesTest.class,


        // Interrogate
        DriverInterrogateRefactoredTest.class,
        DriverInterrogateTest.class,
        GetTitleReplacementExerciseTest.class,
        WebElementInterrogationTest.class,

        // manipulate alerts
        AlertHandlingExampleTest.class,
        //AlertHandlingExercisesTest.class,

        // Manipulate Frames
        FrameFaqTest.class,

        FramesExampleTest.class,
        FramesExercisesRefactoredDeleteTest.class,
        FramesExercisesRefactoredTest.class,
        FramesExercisesTest.class,

        // Manipulate select Support
        // Failed on Marionette
        //SelectSupportTest.class,

        // manipulate windows
        WindowManageExerciseTest.class,
        //WindowsExampleTest.class,
        // Failed on Marionette
        //WindowsExercisesTest.class,
        WindowsManageExampleTest.class,

        // Manipulate
        // Failed on Marionette
        ManipulateExampleSelectDropDownFiveTest.class,
        ManipulateExampleTest.class,
        ManipulateExercisesCheckboxTest.class,
        ManipulateExercisesCommentsTest.class,
        ManipulateExercisesSelectRadio2Test.class,
        ManipulateExercisesSubmitFileTest.class,
        ManipulateExercisesSubmitFormTest.class,
        ManipulatetExampleMultiSelectTest.class,
        ManipulateWhatHappensIfTest.class,
        ManipulationFirstTryExampleTest.class,
        // Failed on Marionette
        //SendKeysExamplesTest.class,

        // Navigation
        NavigationBasicsTest.class,
        NavigationExampleTest.class,

        // cookies
        CookiesExampleTest.class,
        // Failed on Marionette
        //CookiesExercisesTest.class,
        CookiesExercisesTestWorkWithExtraSync.class,

        // data driven tests
        // Failed on Marionette
        BasicDataDrivenTest.class,
        CsvDataDrivenTest.class,

        // drivermanger in DriverSanityCoursePack

        // Javascript
        JavascriptAsyncExecutorTest.class,
        JavascriptExecuteAsyncExerciseTest.class,
        JavascriptExecutorExampleTest.class,
        JavaScriptExecutorTest.class,

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
        // Failed on Marionette
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
        // Failed on Marionette
        ImplicitWaitTest.class,

        //Synchronisation  refactored

        MakeYourWaitsReadableTest.class,

        //Synchronisation  webDriverWaitBasics
        MyFailingWebDriverWaitTest.class,
        // Failed on Marionette
        SynchronisationWithWebDriverWaitTest.class,
        WebDriverWaitExampleTest.class,
        WebDriverWaitExampleTestRefactored.class,
        WebDriverWaitFaqTest.class,

        //userinteractions
        //// Failed on Marionette
        //UserInteractionsExercisesTest.class,
})
public class MarionetteSuite {
}
