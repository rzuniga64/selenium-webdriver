package webdriver.basics.manipulation.exercises;

import org.junit.After;
import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 *  ManipulateClearTest class. Try to clear a checkbox, a multiselect, a dropdown select, and a radio button.
 */
public class ManipulateClearTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }


    @Test
    public void canIClearACheckbox(){

        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        if(!checkBox1.isSelected()) checkBox1.click();

        try{
            checkBox1.clear();

            // HTMLUnit does not throw an exception when trying to clear this element, but other drivers do
            if (Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){
                if (!checkBox1.isSelected()) fail("can now clear a checkbox");
            } else {
                fail("Expected an exception as you can't clear a checkbox");
            }
        } catch(WebDriverException e){
            // expected an exception, you can't clear a multi select you have to click to remove check
        }
    }

    @Test
    public void canIClearAMultiSelect(){

        WebElement multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));

        try{
            multiSelect.clear();

            // HTMLUnit does not throw an exception when trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){

                Select multi = new Select(multiSelect);
                if(multi.getAllSelectedOptions().size() == 0)fail("can now clear a multi select");
            } else{

                fail("Expected an exception as you can't clear a multi select");
            }
        } catch(WebDriverException e){
            // expected an exception, you can't clear a multi select you have to click to remove items
        }
    }

    @Test
    public void canIClearADropDownSelect(){

        WebElement dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));

        try{
            dropDownSelect.clear();

            // neither HTMLUnit throw an exception when trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){

                Select dropDown = new Select(dropDownSelect);
                if(dropDown.getAllSelectedOptions().size() == 0)fail("can now clear a drop down select");
            } else{

                fail("Expected an exception as you can't clear a drop down");
            }
        } catch(WebDriverException e){
            // expected an exception, you can't clear a drop down. You have to click to remove items.
        }
    }

    @Test
    public void canIClearARadiobutton(){

        WebElement radioButton = driver.findElement(By.cssSelector("input[value='rd2']"));

        try{
            radioButton.clear();

            // neither HTMLUnit throw an exception when trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){
                if(!radioButton.isSelected())fail("can now clear a radio button");
            } else{
                fail("Expected an exception as you can't clear a radio button");
            }
        } catch(WebDriverException e){
            // expected an exception, you can't clear a radio button you have to click on another to remove selected
        }
    }

    @After
    public void closeBrowser(){
        driver.close();
    }
}
