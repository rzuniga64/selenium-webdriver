package webdriver.synchronization.customexpectedconditions;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 *  WaitingExercisesTest class.
 *  Create WebDriverWait with an inline ExpectedCondition that returns a WebElement not a Boolean, assert on the text
 *  of that WebElement.
 *
 *  Create a custom ExpectedCondition using a separate named class.
 *  - For titleDoesNotContain(<String>)
 *  - Use /selenium/basic_redirect.html.
 */
public class WaitingExercisesTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver,10);
    }

    /**
     *  canReturnAWebElementUsingAnonymousClass method.
     *  Create WebDriverWait with an inline ExpectedCondition that returns a WebElement not a Boolean
     *  Assert on the text of that WebElement.
     */
    @Test
    public void canReturnAWebElementUsingAnonymousClass(){

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // Wait for Java to be available to select
        WebElement webelement = wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver webDriver) {

                    WebElement element = webDriver.findElement(By.cssSelector("option[value='23']"));

                    if(element.isDisplayed()){
                        return element;
                    } else {
                        return null;
                    }
                }
            }
        );

        // select java
        webelement.click();
        assertEquals("Expected Java", "Java", webelement.getText());

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = wait.until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }


    /**
     *  customExpectedConditionForTitleDoesNotContainUsingClass
     *  Create a custom ExpectedCondition using a separate named class.
     *  - For titleDoesNotContain(<String>)
     *  - Use /selenium/basic_redirect.html.
     */
    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingNamedClass(){

        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        driver.findElement((By.id("delaygotobasic"))).click();
        assertEquals("Basic Web Page Title", wait.until(new TitleDoesNotContain("Redirects")));
    }


    private class TitleDoesNotContain implements ExpectedCondition<String> {

        private String titleExcludes;

        public TitleDoesNotContain(String notContainThisString) {
            this.titleExcludes = notContainThisString;
        }

        @Override
        public String apply(WebDriver webDriver) {

            String title = webDriver.getTitle();

            if(title.contains(this.titleExcludes)){
                return null;
            } else {
                return title;
            }
        }
    }

    /**
     *  customExpectedConditionForTitleDoesNotContainUsingClass
     *  Create a custom ExpectedCondition using private method wrapping the separate named class above.
     *  - For titleDoesNotContain(<String>)
     *  - Use /selenium/basic_redirect.html.
     */
    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingMethod(){

        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        driver.findElement((By.id("delaygotobasic"))).click();
        assertEquals("Basic Web Page Title", wait.until(titleDoesNotContainF("Redirects")));
    }

    private ExpectedCondition<String> titleDoesNotContainF(String stringNotInTitle) {
        return new TitleDoesNotContain(stringNotInTitle);
    }

    /**
     *  customExpectedConditionForTitleDoesNotContainUsingClass
     *  Create a custom ExpectedCondition using an anonymous class wrapped in a method.
     *  - For titleDoesNotContain(<String>)
     *  - Use /selenium/basic_redirect.html.
     */
    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingMethodAC(){

        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        driver.findElement((By.id("delaygotobasic"))).click();
        assertEquals("Basic Web Page Title", wait.until(titleDoesNotContainAC("Redirects")));
    }

    private ExpectedCondition<String> titleDoesNotContainAC(final String stringNotInTitle) {

        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver webDriver) {
                String title = webDriver.getTitle();

                if(title.contains(stringNotInTitle)){
                    return null;
                } else {
                    return title;
                }
            }
        };
    }
}
