package webdriver.basics.manipulation.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 *  ManipulateExercisesCheckboxTest class.
 *  Submit form with only checkbox 1 selected.
 */
public class ManipulateExercisesCheckboxTest {

    private static WebDriver driver;

    @Before
    public void setup(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  submitFormWithOnlyCheckbox1Selected method.
     *  Click on checkbox 1 to enable it and check box 3 to disable it and verify that they are enabled/disabled.
     */
    @Test
    public void submitFormWithOnlyCheckbox1Selected(){

        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        WebElement checkBox3 = driver.findElement(By.cssSelector("input[value='cb3']"));

        if(!checkBox1.isSelected()) checkBox1.click();
        //checkBox3 is selected by default, but we trust nothing;
        if(checkBox3.isSelected())checkBox3.click();

        clickSubmitButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertCheckBoxResults();
    }

    /**
     * This example is the same as above but uses findElements instead of individual findElement
     */
    @Test
    public void submitFormWithOnlyCheckbox1SelectedFindElements(){

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input[name='checkboxes[]']"));

        new WebDriverWait(driver,10).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input[name='checkboxes[]']"), 3));
        checkBoxes.get(0).click();

        if( checkBoxes.get(2).isSelected()) checkBoxes.get(2).click();

        clickSubmitButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertCheckBoxResults();
    }

    /**
     *  assertCheckBoxResults method.
     *  Verify that check box 1 is checked.
     *  Verify that check box 3 is not checked.
     */
    private void assertCheckBoxResults() {

        WebElement checkbox1Result;
        WebElement checkbox3Result = null;

        checkbox1Result = driver.findElement(By.id("_valuecheckboxes0"));

        try{

            checkbox3Result = driver.findElement(By.id("_valuecheckboxes2"));
        } catch(NoSuchElementException e){
            // expected missing element
        }

        assertTrue("expected to find checkbox 1", checkbox1Result != null);
        assertTrue("expected not to find checkbox 3", checkbox3Result == null);
    }

    /**
     *  Submits form by clicking the submit button.
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
