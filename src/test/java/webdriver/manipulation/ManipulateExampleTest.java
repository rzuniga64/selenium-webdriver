package webdriver.manipulation;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManipulateExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){
        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void simpleInteraction(){

        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        assertFalse("Starts not selected", checkBox1.isSelected());

        checkBox1.click();
        assertTrue("Click selects", checkBox1.isSelected());
    }


    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
