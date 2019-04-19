package webdriver.basics.interrogation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 *  WebElementInterrogationTest class.
 */
public class WebElementInterrogationTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver() throws IOException {

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
    }

    @Test
    public void WebElementInterrogationBasics(){

        final String theTestPageURL = "http://www.compendiumdev.co.uk/selenium/basic_web_page.html";

        driver.navigate().to(theTestPageURL);

        // Find an Element by using a locator strategy, in this case the id of the element.
        // WebElement represents an object in the DOM.
        WebElement para1 = driver.findElement(By.id("para1"));

        // Inspect the WebElement using the exposed methods.
        assertEquals(para1.getText(),"A paragraph of text");
        assertThat(para1.getText(), is("A paragraph of text"));
    }
}
