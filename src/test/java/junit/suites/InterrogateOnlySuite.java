package junit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.basics.interrogation.DriverInterrogateRefactoredTest;
import webdriver.basics.interrogation.DriverInterrogateTest;
import webdriver.basics.interrogation.GetTitleReplacementExerciseTest;
import webdriver.basics.interrogation.WebElementInterrogationTest;
import webdriver.basics.interrogation.examples.*;
import webdriver.basics.interrogation.exercises.*;

/**
 * A simple suite that contains just the Interrogation tests
 * as an example of how to collate specific Test Classes into
 * a Suite
 *
 * Can run the suite with
 *
 * mvn clean -Dtest=InterrogateOnlySuite test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

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
})
public class InterrogateOnlySuite {
}
