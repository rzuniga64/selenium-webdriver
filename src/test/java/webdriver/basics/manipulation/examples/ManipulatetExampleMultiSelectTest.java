package webdriver.basics.manipulation.examples;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  ManipulatetExampleMultiSelectTest class.
 *  An example of selecting/unselecting options in a drop down list, submitting the form, and checking the results
 *  on the next page.
 */
public class ManipulatetExampleMultiSelectTest {

    private static WebDriver driver;

    @Before
    public void setup(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  submitFormWithMultiSelectChainOfFindElements method.
     *  Selects the first three options and unselect the last option in a drop down list.
     *  Submits the form and then checks the output on the next page.
     */
    @Test
    public void submitFormWithMultiSelectChainOfFindElements(){

        WebElement multiSelect;
        WebElement dropDownOption;

        multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));
        List<WebElement> multiSelectOptions = multiSelect.findElements(By.tagName("option"));

        // note that if we did this in real life we would not select them as each click unselects anything else selected
        multiSelectOptions.get(0).click();
        multiSelectOptions.get(1).click();
        multiSelectOptions.get(2).click();

        // 4 is normally set by default
        if(multiSelectOptions.get(3).isSelected()){
            multiSelectOptions.get(3).click();
        }

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertEquals("ms1", driver.findElement(By.id("_valuemultipleselect0")).getText());
        assertEquals("ms2", driver.findElement(By.id("_valuemultipleselect1")).getText());
        assertEquals("ms3", driver.findElement(By.id("_valuemultipleselect2")).getText());
        assertTrue("Expected no 4th element", driver.findElements(By.id("_valuemultipleselect3")).isEmpty());
    }

    private void clickSubmitButton() {

        WebElement submitButton;
        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }
}
