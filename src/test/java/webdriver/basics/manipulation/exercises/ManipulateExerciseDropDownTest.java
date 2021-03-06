package webdriver.basics.manipulation.exercises;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  ManipulateExerciseDropDownTest class.
 *  Submit form with drop down item 5 selected.
 */
public class ManipulateExerciseDropDownTest {

    private static WebDriver driver;


    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  submitFormWithDropDownSelectedBySelect method.
     *  Select item five in a drop down menu using CSS tag name 'select' and attribute 'name'..
     *  Click the submit button and check the results on the next page.
     */
    @Test
    public void submitFormWithDropDownSelectedBySelect(){

        WebElement dropDownSelect;
        WebElement dropDownOption;

        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownOption = dropDownSelect.findElement(By.cssSelector("option[value='dd5']"));
        dropDownOption.click();

        clickSubmitButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertDropdownValueIsCorrect();
    }

    /**
     *  submitFormWithDropDownSelectedByOption method.
     *  Select item five in a drop down menu using CSS tag name 'option' and attribute 'value'.
     *  Click the submit button and check the results on the next page.
     */
    @Test
    public void submitFormWithDropDownSelectedByOption(){

        driver.findElement(By.cssSelector("option[value='dd5']")).click();
        clickSubmitButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertDropdownValueIsCorrect();
    }

    /**
     *  submitFormWithDropDownUsingKeyboardFullText method.
     *  Select item five in a drop down menu using the keyboard.
     *  Click the submit button and check the results on the next page.
     */
    @Test
    public void submitFormWithDropDownUsingKeyboardFullText(){

        WebElement dropDownSelect;
        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownSelect.sendKeys("Drop Down Item 5");

        // test intermittent without this wait
        waitForOption5DropDownSelected();
        clickSubmitButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertDropdownValueIsCorrect();

    }

    /**
     *  submitFormWithDropDownFiveUsingKeyboardSpecialKeys method.
     *  Helper function to select option 5 in a drop down using the keyboard.
     */
    @Test
    public void submitFormWithDropDownFiveUsingKeyboardSpecialKeys(){
        WebElement dropDownSelect;
        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownSelect.sendKeys(
                Keys.chord(
                    Keys.HOME,
                    Keys.ARROW_DOWN,
                    Keys.ARROW_DOWN,
                    Keys.ARROW_DOWN,
                    Keys.ARROW_DOWN));

        waitForOption5DropDownSelected();
        clickSubmitButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertDropdownValueIsCorrect();
    }

    /*******************************************************************************************************************
     *  Helper functions.
     ******************************************************************************************************************/

    /**
     *  waitForOption5DropDownSelected method.
     *  Helper function to wait for option 5 in a  drop down to be selected.
     */
    private void waitForOption5DropDownSelected() {
        new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeSelected(
                        By.cssSelector("option[value='dd5']")));
    }

    /**
     *  clickSubmitButton method
     *  Helper function that clicks a Submit button in a form.
     */
    private void clickSubmitButton() {

        WebElement submitButton;
        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }

    /**
     *  assertDropdownValueIsCorrect method
     *  Helper function that asserts that option 5 in a drop down was selected.
     */
    private void assertDropdownValueIsCorrect() {

        WebElement dropDownResult;
        dropDownResult = driver.findElement(By.id("_valuedropdown"));
        assertEquals("expected drop down 5", dropDownResult.getText(), "dd5");
    }
}
