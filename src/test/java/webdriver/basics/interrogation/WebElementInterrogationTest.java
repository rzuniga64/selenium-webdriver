package webdriver.basics.interrogation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import static org.junit.Assert.assertEquals;

public class WebElementInterrogationTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        driver = Driver.get("webdriver.chrome.driver","CHROME" );
    }

    @Test
    public void WebElementInterrogationBasics(){

        final String theTestPageURL = "http://www.compendiumdev.co.uk/selenium/basic_web_page.html";

        driver.navigate().to(theTestPageURL);
        WebElement para1 = driver.findElement(By.id("para1"));
        assertEquals(para1.getText(),"A paragraph of text");
    }
}
