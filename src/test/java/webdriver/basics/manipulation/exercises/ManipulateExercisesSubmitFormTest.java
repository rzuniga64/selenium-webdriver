package webdriver.basics.manipulation.exercises;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  using http://compendiumdev.co.uk/selenium/basic_html_form.html
 *
 *  1. Submits form with button click and assert page title changes.
 *  2. Submits form with submit and assert page title changes.
 *  3. Submits form with form submit and assert page title changes.
 *  4. Submits form with input of type 'password' and assert page title changes.
 *     Test that you can submit on any form element.
 *  5. Submits form with keyboard key ENTER and assert page title changes.
 */
public class ManipulateExercisesSubmitFormTest {

    private static WebDriver driver;

    @Before
    public void setup(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  Submits form with button click and assert page title changes.
     */
    @Test
    public void submitFormWithButtonClick(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    /**
     *  Submits form with submit and assert page title changes.
     */
    @Test
         public void submitFormWithButtonSubmit(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    /**
     *  Submits form with form submit and assert page title changes.
     */
    @Test
    public void submitFormWithFormSubmit(){

        WebElement submitForm = driver.findElement(By.id("HTMLFormElements"));
        submitForm.submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    /**
     *  Submits form with input of type 'password' and assert page title changes.
     *  Test that you can submit on any form element.
     */
    @Test
    public void iCanActuallySubmitOnAnyFormElement(){

        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    /**
     *  Submits form with keyboard key ENTER and assert page title changes.
     */
    @Test
    public void submitButtonWithKeyPress(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.sendKeys(Keys.ENTER);
        // if enter does not work then try SPACE
        //submitButton.sendKeys(Keys.SPACE);

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }
}
