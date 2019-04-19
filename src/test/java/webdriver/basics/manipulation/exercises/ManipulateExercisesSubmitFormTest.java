package webdriver.basics.manipulation.exercises;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

/**
 *  using http://compendiumdev.co.uk/selenium/basic_html_form.html
 *
 *  1. Submits form with Submit button click and assert page title changes.
 *  2. Submits form with submit call on the Submit button and assert page title changes.
 *  3. Submits form with form submit and assert page title changes.
 *  4. Submits form with by doing a submit on form element. That submit bubbles up. We submit the form on the password
 *     input.
 *  5. Submits form with keyboard key ENTER and assert page title changes.
 */
public class ManipulateExercisesSubmitFormTest {

    private static WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  Submits form with Submit button click and assert page title changes.
     */
    @Test
    public void submitFormWithButtonClick(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    /**
     *  Submits form with submit call on the Submit button and assert page title changes.
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
     *  Submits form with by doing a submit on form element. That submit bubbles up. We submit the form on the password
     *  input.
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

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
