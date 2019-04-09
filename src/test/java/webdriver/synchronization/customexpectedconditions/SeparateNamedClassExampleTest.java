package webdriver.synchronization.customexpectedconditions;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 *  SeparateNamedClassExampleTest class.
 *  Use a CustomExpectedCondition when ExpectedConditions does not have what you need.
 *  You want to make your tests read well for your usage scenario
 *  You want to pass additional values to the apply method
 *
 *  ...create a Custom ExpectedCondition
 *  One method is a separate named class
 */
public class SeparateNamedClassExampleTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void customSynchronisationWithExpectedCondition(){

            // select Server
            WebElement categorySelect = driver.findElement(By.id("combo1"));
            categorySelect.findElement(By.cssSelector("option[value='3']")).click();

            wait.until(new SelectContainsText(By.id("combo2"),"Java"));

            // then select Java
            WebElement languageSelect = driver.findElement(By.id("combo2"));
            languageSelect.findElement(By.cssSelector("option[value='23']")).click();

            // Submit the form
            WebElement codeInIt = driver.findElement(By.name("submitbutton"));
            codeInIt.click();

            // don't have to synchronise with other browsers but do with GeckoDriver
            //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
            WebElement languageWeUsed = wait.until(elementToBeClickable( By.id("_valuelanguage_id")));
            assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }

    /**
     *  SelectContainsText class.
     *  Make it private it because it is local to the test.
     *  Implements ExpectedCondition. You can return either Boolean or WebElement. I chose Boolean for this.
     */
    private class SelectContainsText implements ExpectedCondition<Boolean> {

        private String textToFind;
        private By findBy;

        // Pass in whatever you need in the constructor.
        private SelectContainsText(final By comboFindBy, final String textToFind) {

            this.findBy = comboFindBy;
            this.textToFind = textToFind;
        }

        /**
         *  Override apply. This is called by WebDriverWait.
         * @param webDriver a WebDriver.
         * @return a boolean.
         */
        @Override
        public Boolean apply(WebDriver webDriver) {

            WebElement combo = webDriver.findElement(this.findBy);
            List<WebElement> options = combo.findElements(By.tagName("option"));

            // Implement your checking code using the passed in WebDriver.
            for(WebElement anOption : options){
                try{
                    if (anOption.getText().equals(this.textToFind))
                        return true;
                } catch(StaleElementReferenceException e){
                    // too fast, need to refresh the list so trigger a poll and wait for next time
                    return false;
                }
            }
            return false;
        }

        /**
         *  Convert object to a string.
         * @return a string.
         */
        @Override
        public String toString() {

            return "select " + this.findBy + " to contain " + this.textToFind;
        }
    }
}
