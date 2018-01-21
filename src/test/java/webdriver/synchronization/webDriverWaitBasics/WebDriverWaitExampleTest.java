package webdriver.synchronization.webDriverWaitBasics;

import org.junit.BeforeClass;
import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class WebDriverWaitExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){
        driver = Driver.get("selenium2basics.webdriver", "CHROME");
    }

    @Test
    public void exampleUsingExpectedConditions(){


        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }

    @Test
    public void exampleWithSleepTime(){

        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");

        new WebDriverWait(driver,10,50).until(
                ExpectedConditions.titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }
}
