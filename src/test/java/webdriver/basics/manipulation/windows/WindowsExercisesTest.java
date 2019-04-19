package webdriver.basics.manipulation.windows;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
/**
 *
 *  WindowsExercisesTest class.
 *  Using http://compendiumdev.co.uk/selenium/frames/index.html
 *  - Amend the example, WindowsExampleTest, to switch back to the frame window and demonstrate that you switched control.
 *  - Two of the links on the content frame can be switched to by name (eviltester.com and Componendium Developement)
 *    0pen both them and switch between all three windows (two of them using switchTo("name))
 *  - Close each window when done with it.
 *
 *  Useful link on window/frame names http://www.webreference.com/js/tutorial1/names.html.
 */
public class WindowsExercisesTest {

    private static WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        //Driver.quit(); // dodgy in a suite - close everything down and start again
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
        wait = new WebDriverWait(driver, Driver.DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     *  switchBetweenWindows method.
     *  Switch back to the frame window and demonstrate that you switched control.
     *  Two of the links on the content frame can be switched to by name (eviltester.com and Componendium Developement)
     *  Open both them and switch between all three windows (two of them using switchTo("name))
     *  Close each window when done with it
     */
    @Test
    public void switchBetweenWindows(){

        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        String newWindowHandle = framesWindowHandle;

        // Find the handle for the new window
        Iterator<String> aHandle = driver.getWindowHandles().iterator();
        while(newWindowHandle.equals(framesWindowHandle) && aHandle.hasNext()){
            newWindowHandle = aHandle.next();
        }

        driver.switchTo().window(newWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        driver.switchTo().defaultContent();
        // added to see if this fixes synch problem when run in suite
        wait.until(titleContains("Selenium Simplified"));
        assertTrue("Expected Selenium Simplified site", driver.getTitle().contains("Selenium Simplified"));

        driver.switchTo().window(framesWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        //driver.switchTo().defaultContent();

        assertTrue("Expected Frames site", driver.getTitle().contains("Frameset Example"));
        driver.switchTo().window(newWindowHandle);
        driver.close();
        driver.switchTo().window(framesWindowHandle);
    }

    /**
     *  switchToByName method. This fails on IE>
     *  Switch back to the frame window and demonstrate that you switched control.
     *  Two of the links on the content frame can be switched to by name (eviltester.com and Componendium Developement)
     *  Open both them and switch between all three windows (two of them using switchTo("name))
     *  Close each window when done with it
     */
    @Test
    public void switchToByName(){

        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[id='goevil']")).click();

        // Firefox started failing here - need to sync on the link being available
        wait.until(elementToBeClickable(By.cssSelector("a[target='compdev']")));

        // IE Fails at this point, suspect I need to change context back to allow a re-click
        driver.findElement(By.cssSelector("a[target='compdev']")).click();

        driver.switchTo().window("compdev");
        assertTrue("Expected Software Testing", driver.getTitle().contains("Software Testing"));

        driver.switchTo().window("evil");
        assertTrue("Expected EvilTester.com", driver.getTitle().contains("EvilTester.com"));

        driver.switchTo().window(framesWindowHandle);
        assertTrue("Expected Frames site", driver.getTitle().contains("Frameset Example"));

        driver.switchTo().window("compdev");
        driver.close();
        assertEquals("Expected 2 windows left", 2, driver.getWindowHandles().size());

        driver.switchTo().window("evil");
        driver.close();
        assertEquals("Expected 1 window left", 1, driver.getWindowHandles().size());
    }

    @AfterClass
    public static void quitToClean() {
        driver.quit();
    }
}
