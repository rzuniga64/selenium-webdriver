package webdriver.synchronization.conditions;

import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class WaitingExercisesUsingPredicateTest {

    private static WebDriver driver;


    /*****************************
     * Using a Function
     ****************************/
    /*
     TODO: WebDriver has changed so that it no longer takes a predicate and now takes a Function

     - still need to update the videos
     

     this example shows the 'new' approach using a Function instead of a Predicate
     All that has changed is that instead of:

     `implements Function<WebDriver, Boolean>`

     we now have

     `implements Function<WebDriver, Boolean>`

     And the apply method returns `Boolean` rather than `boolean`

     and the return value from `titleDoesNotContain`  changed to:

     `Function<WebDriver, Boolean>` to match the declaration of the class `TitleDoesNotContain`

    */
    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingClass() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        driver.findElement((By.id("delaygotobasic"))).click();

        new WebDriverWait(driver,8).until( new TitleDoesNotContain("Redirects"));

        assertEquals("Basic Web Page Title", driver.getTitle());
    }




    private class TitleDoesNotContain implements Function<WebDriver, Boolean> {

        private String titleExcludes;

        public TitleDoesNotContain(String notContainThisString) {
            this.titleExcludes = notContainThisString;
        }

        @Override
        public Boolean apply(WebDriver webDriver) {
            return !webDriver.getTitle().contains(this.titleExcludes);
        }
    }


    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingMethod() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_redirect.html");

        driver.findElement((By.id("delaygotobasic"))).click();

        new WebDriverWait(driver,8).until(titleDoesNotContain("Redirects"));

        assertEquals("Basic Web Page Title", driver.getTitle());
    }

    private Function<WebDriver, Boolean> titleDoesNotContain(String stringNotInTitle) {
        return new TitleDoesNotContain(stringNotInTitle);
    }

}
