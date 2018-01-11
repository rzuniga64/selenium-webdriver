package webdriver.manipulation;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  using http://compendiumdev.co.uk/selenium/basic_html_form.html
 *
 *  1. Submit form and assert page title changes
 *  2. Clear, then type comments, submit form and check output
 *  3. Submit form with radio 2 selected
 *  4. Submit form with only checkbox 1 selected
 *  5. Submit form with drop down item 5 selected
 *  6. Submit form with multiple select 1, 2, & 3
 *  7  Submit with a file, and check name on output
 */
public class ManipulateExercisesSubmitFormTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void submitFormWithButtonClick(){

        WebElement submitButton;
        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));

        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
         public void submitFormWithButtonSubmit(){

        WebElement submitButton;
        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));

        submitButton.submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
    public void submitFormWithFormSubmit(){

        WebElement submitForm;
        submitForm = driver.findElement(By.id("HTMLFormElements"));

        submitForm.submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
    public void iCanActuallySubmitOnAnyFormElement(){

        WebElement passwordInput;
        passwordInput = driver.findElement(By.cssSelector("input[type='password']"));

        passwordInput.submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
    public void submitButtonWithKeyPress(){

        WebElement submitButton;
        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));

        submitButton.sendKeys(Keys.ENTER);
        // if enter does not work then try SPACE
        //submitButton.sendKeys(Keys.SPACE);

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }
}
