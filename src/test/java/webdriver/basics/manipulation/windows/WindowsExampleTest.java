package webdriver.basics.manipulation.windows;

import webdriver.drivermanager.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  WindowsExampleTest class.
 *  Windows
 *  Each browser had a unique handler
 *  driver.getWindowhandle() returns handle for current window
 *  driver.getWindowHandles() returns a Set<String> of all window handles
 *  driver.switchTo.window(String handle) switches control to the chosen window
 *
 *  Current bug open with chrome driver
 *  http://code.google.com/p/chromedriver/issues/detail?id=107
 */
public class WindowsExampleTest {

    private static WebDriver driver;

    @Before
    public void quitToRestart() throws IOException {

        driver = Driver.get();
    }

    @Test
    public void switchToNewWindow(){

        // We start with one window open
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");

        // Remember the current window handle
        String framesWindowHandle = driver.getWindowHandle();
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

        driver.switchTo().frame("content");
        // Clicking on link opens a new window
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();
        assertEquals("Expected a New Window opened", 2, driver.getWindowHandles().size());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle="";

        // Find the new window handle
        for(String aHandle : myWindows){
            if(!framesWindowHandle.contentEquals(aHandle)){
                newWindowHandle = aHandle; break;
            }
        }

        // Switch to the new window
        driver.switchTo().window(newWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        driver.switchTo().defaultContent();

        // Driver commands now act on  new window
        assertTrue("Expected Selenium Simplified site", driver.getTitle().contains("Selenium Simplified"));
    }

    @After
    public void quitToClean(){

        // I need to make sure that only one window open for other tests in CI
        driver.quit();
    }
}
