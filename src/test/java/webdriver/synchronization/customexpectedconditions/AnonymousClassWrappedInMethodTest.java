package webdriver.synchronization.customexpectedconditions;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 *  AnonymousClassExampleTest class.
 *  Use a CustomExpectedCondition when ExpectedConditions does not have what you need.
 *  You want to make your tests read well for your usage scenario
 *  You want to pass additional values to the apply method
 *
 *  ...create a Custom ExpectedCondition
 *  One method is an anonymous class route, wrapped with a method
 */
public class AnonymousClassWrappedInMethodTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver,10);
    }

    /**
     *  Use a CustomExpectedCondition when ExpectedConditions does not have what you need.
     *  You want to make your tests read well for your usage scenario
     *  You want to pass additional values to the apply method
     *
     *   ...create a Custom ExpectedCondition
     *   One method is an anonymous class route,wrapped with a method
     */
    @Test
    public void methodWrappedCustomExpectedConditionReturningWebElement(){

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        WebElement javaOption = wait.until(optionWithValueDisplayed("23"));

        javaOption.click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = wait.until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());

    }

    /**
     *  optionWithValueDisplayed method that uses a anonymous class wrapped in a method.
     *  Anonymous class wrapped in a method can use final params in the class. You can pass values into an anonymous
     *  class so long as it's wrapped in a method and the parameter is declared as final.
     *  @param value the value looked for in the findElement call.
     *  @return  a ExpectedCondition<WebElement>
     */
    private ExpectedCondition<WebElement> optionWithValueDisplayed(final String value) {

        // Anonymous class
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                // return a WebElement for future use.
                return webDriver.findElement(By.cssSelector("option[value='" + value + "']"));
            }
        };
    }
}
