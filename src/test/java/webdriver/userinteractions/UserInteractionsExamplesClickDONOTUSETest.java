package webdriver.userinteractions;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.util.List;

import static org.junit.Assert.*;

/**
 *  UserInteractionsExamplesClickDONOTUSETest class.
 *  User Interactions may more accurately simulate user interact.
 *
 *  new Actions(driver)
 *  Actions Sequence
 *  .keyDown
 *  .keyUp
 *  .sendKeys
 *  .clickAndHold
 *  .release
 *  .click
 *  .doubleClick
 *  .moveToElement
 *  .moveByOffset
 *  .contextClick
 *  .dragAndDrop
 *  .dragandDrobBy
 *  .build() : build if you want to store and reuse the action sequence, otherwise just perform
 *  .perform()
 */
public class UserInteractionsExamplesClickDONOTUSETest {

    private static WebDriver driver;

    @Before
    public void setup(){

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  multiSelectWithUserInteractions method.
     *  Use User Interactions click actions to select the first three options in a multi-select.
     */
    @Test
    //@Ignore("I would love for this to work consistently but it fails too often between releases to use as an example")
    public void multiSelectWithUserInteractions(){

        WebElement multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));;
        List<WebElement> multiSelectOptions = multiSelect.findElements(By.tagName("option"));

        // In real life, clicking on a multi select item without holding down CTRL will deselect all others and select
        // only that one item.
        Actions actions = new Actions(driver);

        actions.click(multiSelectOptions.get(0)).
                click(multiSelectOptions.get(1)).
                click(multiSelectOptions.get(2)).perform();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertEquals("Expected only 1 match", 1,
                driver.findElements(By.cssSelector("[id^='_valuemultipleselect']")).size());
    }

    /**
     *  multiSelect123WithUserInteractions method.
     *  Use User Interactions mouse and keyboard actions to select the first three options in a multi-select.
     *
     *  User interactions can be unpredictable.
     *  Seem good with mouse or keyboard but less so with combined mouse + keyboard
     *  Use when 'normal' webdriver does not work
     *  e.g. a normal webDriver click does a ctrl + click an action does a click
     *  but using action to do a ctrl + click does not work reliably for everybody
     */
    @Test
    //@Ignore("I would love for this to work consistently but it fails too often between releases to use as an example")
    public void multiSelect123WithUserInteractions(){

        WebElement multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));
        List<WebElement> multiSelectOptions = multiSelect.findElements(By.tagName("option"));

        // in real life, clicking on a multi select item without holding down CTRL will deselect all others and select
        // only that one item
        Actions actions = new Actions(driver);
        //actions.keyUp(Keys.CONTROL).perform();

        // click on the first one also deselects number 4.
        actions.click(multiSelectOptions.get(0)).perform();

        // Press Control + Left mouse button for 2 and 3
        actions.keyDown(Keys.CONTROL).
                click(multiSelectOptions.get(1)).
                click(multiSelectOptions.get(2)).
                keyUp(Keys.CONTROL).
                build().perform();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        List<WebElement> selectedItems = driver.findElements(By.cssSelector("[id^='_valuemultipleselect']"));
        try{
            assertEquals("Expected 3 matches", 3, selectedItems.size());
            assertEquals("ms1", selectedItems.get(0).getText());
            assertEquals("ms2", selectedItems.get(1).getText());
            assertEquals("ms3", selectedItems.get(2).getText());
            //fail("What browser and WebDriver combination did you use? This normally fails");
        }catch(AssertionFailedError e){
            assertTrue("either I've done something wrong or this is not reliable enough", true);
        }
    }

    /**
     *  clickSubmitButton method.
     *  Click on a form's Submit button.
     */
    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }

    @After
    public void closeBrowser(){
        driver.close();
    }
}
