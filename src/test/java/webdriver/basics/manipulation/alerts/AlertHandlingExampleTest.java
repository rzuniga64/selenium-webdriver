package webdriver.basics.manipulation.alerts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  AlertHandlingExampleTest class.
 *  Handle Aerts with
 *  driver.switchTo().alert
 *  .getText()
 *  .dismiss()
 *  .accept()
 *  .sendKeys(String)
 *
 *  .alert() returns an Alert object
 */
public class AlertHandlingExampleTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/alert.html");
    }

    @Test
    public void basicAlertHandlingExample(){

        WebElement alertButton = driver.findElement(By.id("alertexamples"));
        alertButton.click();

        String alertMessage = "I am an alert box!";
        assertEquals(alertMessage,driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }

    @After
    public void closeBrowser(){
        //driver.quit();
    }
}

