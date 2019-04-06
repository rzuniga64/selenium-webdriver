package webdriver.basics.manipulation.exercises;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;


public class ManipulateExercisesSelectRadioTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void submitFormWithOnlyRadioButton2SelectedFindElementException(){

        WebElement radioButton = driver.findElement(By.cssSelector("input[value='rd2']"));

        //radioButton is not selected by default, but we trust nothing
        if(!radioButton.isSelected()){
            radioButton.click();
        }

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        WebElement radioButtonResult = driver.findElement(By.id("_valueradioval"));
        assertEquals("expected to radio button 2", radioButtonResult.getText(), "rd2");
    }
}
