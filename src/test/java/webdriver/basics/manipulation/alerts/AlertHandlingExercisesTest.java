package webdriver.basics.manipulation.alerts;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *  AlertHandlingExercisesTest class.
 *  For each alert, assert against getText()
 *  Accept and dismiss alert.
 *  Accept and dismiss confirm (check text on page changes).
 *  Accept and dismiss prompt (check text on page changes).
 *  Change text on prompt and accept and dismiss.
 */
public class AlertHandlingExercisesTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/alerts.html");
    }

    /**
     *  basicAlertHandlingTest method.
     *  Find the button that triggered the alert.
     *  Click on it.
     *  Check that the text in the alert is what we are expecting.
     */
    @Test
    public void basicAlertHandlingTest(){

        WebElement alertButton = driver.findElement(By.id("alertexamples"));
        alertButton.click();
        String alertMessage = "I am an alert box!";

        assertEquals(alertMessage, driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }

    /**
     *  basicAlertHandlingDismissTest method..
     *  Find the button that triggered the alert.
     *  Dismiss it.
     *  Check that the text in the alert is what we are expecting.
     */
    @Test
    public void basicAlertHandlingDismissTest(){

        WebElement alertButton = driver.findElement(By.id("alertexamples"));
        alertButton.click();
        String alertMessage = "I am an alert box!";

        assertEquals(alertMessage, driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
    }

    /**
     *  basicConfirmHandlingAcceptTest method..
     *  Find the button that triggered the alert.
     *  Accept the alert.
     *  Check that the text in the alert is changed and is what we expect.
     */
    @Test
    public void basicConfirmHandlingAcceptTest(){

        WebElement confirmButton = driver.findElement(By.id("confirmexample"));
        WebElement confirmResult = driver.findElement(By.id("confirmreturn"));

        assertEquals("cret", confirmResult.getText());
        confirmButton.click();

        String alertMessage = "I am a confirm alert";
        Alert confirmAlert = driver.switchTo().alert();
        assertEquals(alertMessage,confirmAlert.getText());
        confirmAlert.accept();
        assertEquals("true", confirmResult.getText());
    }

    /**
     *  basicConfirmHandlingDismissTest method..
     *  Find the button that triggered the alert.
     *  Confirm the alert.
     *  Check that the text in the alert is changed and is what we expect.
     *  Dismiss the alert.
     *  Check that the text in the alert is what we expect.
     */
    @Test
    public void basicConfirmHandlingDismissTest(){

        WebElement confirmButton = driver.findElement(By.id("confirmexample"));
        WebElement confirmResult = driver.findElement(By.id("confirmreturn"));

        assertEquals("cret", confirmResult.getText());
        confirmButton.click();

        String alertMessage = "I am a confirm alert";
        Alert confirmAlert = driver.switchTo().alert();
        assertEquals(alertMessage,confirmAlert.getText());
        confirmAlert.dismiss();
        assertEquals("false", confirmResult.getText());
    }

    /**
     *  basicConfirmHandlingDismissTest method..
     *  Click on the prompt alert button.
     *  Check that the text in the alert is changed and is what we expect.
     *  Accept the alert.
     *  Check that the text in the alert is what we expect.
     */
    @Test
    public void basicPromptConfirmHandlingAcceptTest(){

        WebElement promptButton = driver.findElement(By.id("promptexample"));
        WebElement promptResult = driver.findElement(By.id("promptreturn"));

        assertEquals("pret", promptResult.getText());
        promptButton.click();
        String alertMessage = "I prompt you";
        Alert promptAlert = driver.switchTo().alert();

        // IE prompt now shows the correct message so no need for any caveat
        assertEquals(alertMessage,promptAlert.getText());
        promptAlert.accept();
        assertEquals("change me", promptResult.getText());
    }

    /**
     *  basicPromptConfirmHandlingDismissTest method..
     *  Click on the prompt alert button.
     *  Check that the text in the alert is changed and is what we expect.
     *  Dismiss the alert.
     *  Check that the text in the alert is what we expect.
     */
    @Test
    public void basicPromptConfirmHandlingDismissTest(){

        WebElement promptButton = driver.findElement(By.id("promptexample"));
        WebElement promptResult = driver.findElement(By.id("promptreturn"));

        assertEquals("pret", promptResult.getText());
        promptButton.click();

        String alertMessage = "I prompt you";
        Alert promptAlert = driver.switchTo().alert();

        // In IE the alert always used to return "Script Prompt:" and not the
        // actual prompt text but now it works fine
        assertEquals(alertMessage,promptAlert.getText());

        promptAlert.dismiss();
        assertEquals("pret", promptResult.getText());
    }

    /**
     *  basicPromptConfirmHandlingChangeAndAcceptTest method. Send text to prompt.
     *  Click on the prompt alert button.
     *  Check that the text in the alert is changed and is what we expect.
     *  Send text to the alert.
     *  Check that the text in the alert is what we expect.
     */
    @Test
    public void basicPromptConfirmHandlingChangeAndAcceptTest(){

        WebElement promptButton = driver.findElement(By.id("promptexample"));
        WebElement promptResult = driver.findElement(By.id("promptexample"));

        assertEquals("pret", promptResult.getText());
        promptButton.click();

        String alertMessage = "I prompt you";
        Alert promptAlert = driver.switchTo().alert();
        assertEquals(alertMessage,promptAlert.getText());

        promptAlert.sendKeys("Hello");
        promptAlert.accept();
        assertEquals("Hello", promptResult.getText());
    }

    /**
     *  basicPromptConfirmHandlingChangeAndDismissTest method. Send text to prompt and then dismiss the dialog.
     *  Click on the prompt alert button.
     *  Check that the text in the alert is changed and is what we expect.
     *  Send text to the alert.
     *  Dismiss the dialog.
     *  Check that the text in the alert is what we expect.
     */
    @Test
    public void basicPromptConfirmHandlingChangeAndDismissTest(){

        WebElement promptButton = driver.findElement(By.id("promptexample"));
        WebElement promptResult = driver.findElement(By.id("promptreturn"));

        assertEquals("pret", promptResult.getText());
        promptButton.click();

        String alertMessage = "I prompt you";
        Alert promptAlert = driver.switchTo().alert();
        assertEquals(alertMessage,promptAlert.getText());

        promptAlert.sendKeys("Hello");
        promptAlert.dismiss();
        assertEquals("pret", promptResult.getText());
    }

    /**
     *  basicAlertHandlingKeysTest method.
     *  Q: what happens if I send text to alert?
     *  A: ElementNotVisibleException  in Firefox
     *  A: in ChromeDriver v24 the text is sent, in ChromeDriver 2.32 an exception is thrown
     */
    @Test
    public void basicAlertHandlingKeysTest(){

        WebElement alertButton = driver.findElement(By.id("alertexamples"));
        alertButton.click();

        String alertMessage = "I am an alert box!";

        assertEquals(alertMessage, driver.switchTo().alert().getText());

        if(Driver.currentBrowser() == Driver.BrowserName.FIREFOX){
            try{
                driver.switchTo().alert().sendKeys("Hello keys Accept");
                fail("expected a ElementNotVisibleException thrown");
            } catch(ElementNotVisibleException e){
                assertTrue("Expected to get an exception", true);
            }
        }

        /**
         *
         *  As of approx ChromeDriver 2.32 and Chrome 61.0.3163.100
         *  Chrome no longer allows you to send keys to an alert that does not have a text box and
         *  ElementNotSelectableException is thrown
         *  if(Driver.currentBrowser() == Driver.BrowserName.GOOGLECHROME){
         *      chrome seems happy to send in text to an alert
         *      driver.switchTo().alert().sendKeys("Hello keys Accept");
         *  }
         */
        driver.switchTo().alert().accept();
    }
}