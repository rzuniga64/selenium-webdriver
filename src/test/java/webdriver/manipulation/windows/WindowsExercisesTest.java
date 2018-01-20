package webdriver.manipulation.windows;

import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Using http://compendiumdev.co.uk/selenium/frames/index.html
 *  - Amend the example, WindowsExampleTest, to switch back to the frame window and demonstrate that you switched control.
 *  - Two of the links on the content frame can be switched to by name (eviltester.com and Componendium Developement)
 *    0pen both them and switch between all three windows (two of them using switchTo("name))
 *  - Close each window when done with it.
 */
public class WindowsExercisesTest {

    private static WebDriver driver;
    // useful link on window/frame names http://www.webreference.com/js/tutorial1/names.html

    @Test
    public void switchBetweenWindows(){

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        //Driver.quit(); // dodgy in a suite - close everything down and start again
        WebDriver driver = Driver.get(
                    "http://www.compendiumdev.co.uk/selenium/frames");

        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        String newWindowHandle=framesWindowHandle;

        Iterator<String> aHandle = driver.getWindowHandles().iterator();
        // forgot to add hasNext, could lead to an infinite loop
        while(newWindowHandle.equals(framesWindowHandle) && aHandle.hasNext()){
            newWindowHandle = aHandle.next();
        }

        driver.switchTo().window(newWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        driver.switchTo().defaultContent();
        // added to see if this fixes synch problem when run in suite
        new WebDriverWait(driver,5L).until(ExpectedConditions.titleContains("Selenium Simplified"));

        assertTrue("Expected Selenium Simplified site",
                driver.getTitle().contains("Selenium Simplified"));

        driver.switchTo().window(framesWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        //driver.switchTo().defaultContent();

        assertTrue("Expected Frames site",
                driver.getTitle().contains("Frameset Example"));

        driver.switchTo().window(newWindowHandle);
        driver.close();
        driver.switchTo().window(framesWindowHandle);

    }

    // This fails on IE
    @Test
    public void switchToByName(){

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        //Driver.quit(); // dodgy in a suite - close everything down and start again
        WebDriver driver = Driver.get(
                "http://www.compendiumdev.co.uk/selenium/frames");

        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[id='goevil']")).click();

        // Firefox started failing here - need to sync on the linnk being available
        new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[target='compdev']")));

        // IE Fails at this point, suspect I need to change context back to allow a reclick
        driver.findElement(By.cssSelector("a[target='compdev']")).click();

        driver.switchTo().window("compdev");
        assertTrue("Expected Software Testing", driver.getTitle().contains("Software Testing"));

        driver.switchTo().window("evil");
        assertTrue("Expected EvilTester.com", driver.getTitle().contains("EvilTester.com"));

        driver.switchTo().window(framesWindowHandle);
        assertTrue("Expected Frames site",
                driver.getTitle().contains("Frameset Example"));

        driver.switchTo().window("compdev");
        driver.close();
        assertEquals("Expected 2 windows left", 2, driver.getWindowHandles().size());

        driver.switchTo().window("evil");
        driver.close();
        assertEquals("Expected 1 window left", 1, driver.getWindowHandles().size());
    }
}
