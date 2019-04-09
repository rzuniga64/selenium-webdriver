package webdriver.synchronization.webDriverWaitBasics;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 *  exampleUsingExpectedConditions class
 *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html
 *  1. Select “Server” from combo1
 *  2. Select “Java” from combo2
 *  3. Click “Code In It” button
 *  4. Assert that the loaded page has “_valuelanguage_id” text of 23
 */
public class SynchronisationWithWebDriverWaitTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setup(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver,10);
    }

    /**
     *  chooseToCodeInJavaOnTheServerFromCombosNoSynchronisationExample method.
     *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html
     *  1. Select “Server” from combo1
     *  2. Select “Java” from combo2
     *  3. Click “Code In It” button
     *  4. Assert that the loaded page has “_valuelanguage_id” text of 23
     *
     *  NEVER DO THIS. We wrapped a failing test in a try/catch and expect it to fail.
     */
    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosNoSynchronisationExample(){

        try{
            // select Server from combo1
            WebElement categorySelect = driver.findElement(By.id("combo1"));
            categorySelect.findElement(By.cssSelector("option[value='3']")).click();

            // then select Java from combo2
            WebElement languageSelect = driver.findElement(By.id("combo2"));
            languageSelect.findElement(By.cssSelector("option[value='23']")).click();

            // click "Code In It" button
            WebElement codeInIt = driver.findElement(By.name("submitbutton"));
            codeInIt.click();

            // Assert that the loaded page has “_valuelanguage_id” text of 23
            WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
            assertEquals("Expected Java code", "23",languageWeUsed.getText());

        } catch(Exception e){
            assertTrue("Expected some sort of exception thrown",true);
        }
    }

    /**
     *  chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample method.
     *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html
     *  1. Select “Server” from combo1
     *  2. Select “Java” from combo2
     *  3. Click “Code In It” button
     *  4. Assert that the loaded page has “_valuelanguage_id” text of 23
     */
    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        // select Server from combo1
        selectServer();
        // wait until the ajax symbol has gone because then the drop down has populated
        wait.until(invisibilityOfElementLocated(By.id("ajaxBusy")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    /**
     *  chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionPresentExample method.
     *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html
     *  1. Select “Server” from combo1
     *  2. Select “Java” from combo2
     *  3. Click “Code In It” button
     *  4. Assert that the loaded page has “_valuelanguage_id” text of 23
     */
    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionPresentExample(){

        // select Server from combo1
        selectServer();
        // wait until the option I want to click is present
        wait.until(presenceOfElementLocated(By.cssSelector("option[value='23']")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    /**
     *  chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionVisibleExample method.
     *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html
     *  1. Select “Server” from combo1
     *  2. Select “Java” from combo2
     *  3. Click “Code In It” button
     *  4. Assert that the loaded page has “_valuelanguage_id” text of 23
     */
    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionVisibleExample(){

        // select Server from combo1
        selectServer();
        // wait until the option I want to click is visible
        wait.until(visibilityOfElementLocated(By.cssSelector("option[value='23']")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    /**
     *  chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample method.
     *  Write a test which navigates to compendiumdev.co.uk/selenium/basic_ajax.html
     *  1. Select “Server” from combo1
     *  2. Select “Java” from combo2
     *  3. Click “Code In It” button
     *  4. Assert that the loaded page has “_valuelanguage_id” text of 23
     */
    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample(){

        // select Server from combo1
        selectServer();
        // wait until the option I want to click is clickable
        wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    private void selectServer() {

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();
    }

    private void selectJavaSubmitFormAndCheckResult() {

        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        wait.until(titleIs("Processed Form Details"));

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = wait.until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }
}
