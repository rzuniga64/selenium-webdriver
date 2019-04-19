package webdriver.basics.manipulation.frames;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  switchToFrameExample class.
 *  A frame is an included HTMl page that is displayed as a frame on that page.
 *  driver.switchTo().
 *  frame(“framename”)
 *  frame(int)
 *  frame(WebElement)
 *  defaultContent()
 *  _top
 *  When you switch your commands act on the new <body>
 */
public class FramesExampleTest {

    private static WebDriver driver;


    @Before
    public void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
    }

    /**
     *  switchToFrameExample method.
     *  Switch to a frame.
     *  Find a link in the frame and click on it. The menu itself is an e-mail page in a frame. It opens up a new page.
     *  Switch to the new page and assert on the title.
     */
    @Test
    public void switchToFrameExample(){

        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='frames_example_1.html']")).click();
        String titleForExample1 = "Frameset Example Title (Example 1)";

        // Added for Marionette Driver to force moving frame.
        // Not needed for other drivers but it does no harm for other drivers.
        // Note - this is only needed if we are checking the title, not for any other action.
        driver.switchTo().defaultContent();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.titleIs(titleForExample1));
        assertEquals(titleForExample1,driver.getTitle());
    }

    @After
    public void closeBrowser(){
        driver.close();
    }
}
