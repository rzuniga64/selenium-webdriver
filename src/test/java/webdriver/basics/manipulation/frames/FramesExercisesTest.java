package webdriver.basics.manipulation.frames;

import org.junit.After;
import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  FramesExercisesTest class.
 *  1. In the content click on “Load green page” link.
 *  2. On “Green Page” click on “Back to original page” link.
 *  3. Assert for the presence of “<h1>Content</h1>”
 *
 *  1. Click on the “iFrames Example” in the 'menu' frame.
 *  2. In the iframe click on “Example 5”.
 *  3. In the content click on “Load Main Frames Page”.
 *  4. Assert.
 */
public class FramesExercisesTest {

    private WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
    }

    /**
     *  loadTheGreenPage method.
     *  1. In the content click on “Load green page” link.
     *  2. On “Green Page” click on “Back to original page” link.
     *  3. Assert for the presence of “<h1>Content</h1>”
     */
    @Test
    public void loadTheGreenPage(){

        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        // load the green page
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='green.html']")).click();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1[id='green']")));

        // click on "Back to original page"
        driver.findElement(By.cssSelector("a[href='content.html']")).click();

        // assert for presence of "<h1>Content</h1>"
        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[.='Content']")));

        assertEquals("Content",driver.findElement(By.tagName("h1")).getText());
    }

    /**
     *  workWithTheIFrame method.
     *  1. Click on the “iFrames Example” in the 'menu' frame.
     *  2. In the iframe click on “Example 5”.
     *  3. In the content click on “Load Main Frames Page”.
     *  4. Assert.
     */
    @Test
    public void workWithTheIFrame(){

        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        // click on "menu"."iFrames Example"
        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='iframe.html']")).click();

        // with Marionette Driver each time we take an action that we know moves us out of the frame we need to switch
        // to defaultContent does not impact any other browser driver, but is needed for Marionette only if we want to
        // synchronise on title - not needed for any other synchronisation
        driver.switchTo().defaultContent();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs("HTML Frames Example - iFrame Contents"));

        // click on Example 5 in the iFrame
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("a[href='frames_example_5.html']")).click();

        driver.switchTo().defaultContent();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs("Frameset Example Title (Example 5)"));

        // then content.load main frames page
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='index.html']")).click();

        driver.switchTo().defaultContent();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
    }

    @After
    public void closeBrowser(){
        driver.close();
    }
}
