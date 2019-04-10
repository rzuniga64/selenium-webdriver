package webdriver.synchronization.refactored;

import org.junit.AfterClass;
import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 *  MakeYourWaitsReadableTest class.
 *
 *  - Wrap an inline wait in a method
 *    - e.g. ExpectedConditions
 *  - Wrap an ExpectedCondition in a method
 *  - Override toString
 *  - Create a ‘wait’ variable
 *    - Assign new WebDriverWait(...) in a @Before
 *  - Import Expected Conditions statically
 *    - Refactor to static import
 *  - Wait for a ‘thing’ then use it
 */
public class MakeYourWaitsReadableTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setupForTest(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        // instantiate your waits at the highest level you can, to reuse in the test
        wait = new WebDriverWait(driver,10);
    }

    /**
     *  wrapAnonymousClassesInMethods method.
     *  - Wrap an inline wait in a method
     *    - e.g. ExpectedConditions
     *  - Wait for a ‘thing’ then use it
     */
    @Test
    public void wrapAnonymousClassesInMethods(){

        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // wait for a thing, then use it
        WebElement javaOption = wait.until(optionWithValueDisplayed("23"));
        javaOption.click();

        WebElement submitButton = driver.findElement(By.name("submitbutton"));
        submitButton.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = wait.until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }

    /**
     *  - Wrap an inline wait in a method
     *    - e.g. ExpectedConditions
     *  - Override toString
     * @param value the String to be searched for
     * @return a WebElement
     */
    private ExpectedCondition<WebElement> optionWithValueDisplayed(final String value) {

        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("option[value='" + value + "']"));
            }

            @Override
            public String toString() {
                return "option with value " + value + " displayed";
            }
        };
    }


    /**
     *  importExpectedConditionMethodsStatically
     *  - Import Expected Conditions statically
     *    - Refactor to static import
     */
    @Test
    public void importExpectedConditionMethodsStatically(){

        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // wait for a thing, then use it
        // static import for the elementToBeClickable method
        WebElement javaOption = wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
        javaOption.click();

        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        // WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = wait.until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }

    @AfterClass
    public static void closeBrowser() {
        //driver.quit();
    }
}
