package webdriver.basics.manipulation.exercises;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  ManipulateExercisesSelectRadioTest class.
 *  Submit form with radio 2 selected.
 */
public class ManipulateExercisesSelectRadioTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  submitFormWithOnlyRadioButton2SelectedFindElementException method.
     *  Submit form with radio 2 selected.
     */
    @Test
    public void submitFormWithOnlyRadioButton2SelectedFindElementException(){

        WebElement radioButton = driver.findElement(By.cssSelector("input[value='rd2']"));

        if(!radioButton.isSelected())radioButton.click();

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        WebElement radioButtonResult = driver.findElement(By.id("_valueradioval"));
        assertEquals("expected to radio button 2", radioButtonResult.getText(), "rd2");
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
