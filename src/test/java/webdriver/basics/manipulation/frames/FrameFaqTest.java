package webdriver.basics.manipulation.frames;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  FrameFaqTest class.
 */
public class FrameFaqTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
    }

    /**
     *  Use switchTo method to switch to a frame before finding an element.
     */
    @Test
    public void whatHappensIfIDoNotSwitchTo(){

        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        // if you remove the switchTo then it won't find the element below
        driver.switchTo().frame("menu");
        try{
            driver.findElement(By.cssSelector("a[href='frames_example_1.html']")).click();
            //fail("I did not expect to find this");

        } catch(NoSuchElementException e){
            // ignore the exception we expected
            e.printStackTrace();
        }
    }

    @After
    public void closebrowser() {
        driver.close();
    }
}