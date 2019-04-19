package webdriver.basics.manipulation.examples;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  ManipulateExampleTest class.
 */
public class ManipulateExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() throws IOException {
        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  simpleInteraction method.
     *  Find checkbox with value of 'cb1' and verify that it is not selected.
     *  Click the checkbox and verify that it is selected.
     */
    @Test
    public void simpleInteraction(){

        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        assertFalse("Checkbox1 not selected", checkBox1.isSelected());

        checkBox1.click();
        assertTrue("Click selects", checkBox1.isSelected());
    }


    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
