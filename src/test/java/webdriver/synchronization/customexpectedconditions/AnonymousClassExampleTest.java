package webdriver.synchronization.customexpectedconditions;

import org.junit.BeforeClass;
import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 *  AnonymousClassExampleTest class.
 *  Use a CustomExpectedCondition when ExpectedConditions does not have what you need.
 *  You want to make your tests read well for your usage scenario
 *  You want to pass additional values to the apply method
 *
 *  ...create a Custom ExpectedCondition
 *  One method is an anonymous class
 *  One method is an anonymous class route,wrapped with a method
 */
public class AnonymousClassExampleTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup(){

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver,10);
    }

    /**
     *  inlineCustomExpectedCondition method.
     *  Use a CustomExpectedCondition when ExpectedConditions does not have what you need.
     *  You want to make your tests read well for your usage scenario
     *  You want to pass additional values to the apply method
     *
     *  ...create a Custom ExpectedCondition
     *  One method is an anonymous class
     */
    @Test
    public void inlineCustomExpectedCondition(){

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // use an anonymous class directly. Create Expected Condition on the fly and @Override the apply method.
        // Anonymous classes cannot have custom constructors so use for simple wait.
        // If not Boolean then null == false.
        wait.until(new ExpectedCondition<Boolean>() {

                // WebDriver automatically passed through to the apply method.
                // Add your synchronization code in the apply method.
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.cssSelector("option[value='23']")).isDisplayed();
                }
            }
        );

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
}
