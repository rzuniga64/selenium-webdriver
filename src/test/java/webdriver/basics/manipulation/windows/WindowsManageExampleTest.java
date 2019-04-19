package webdriver.basics.manipulation.windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  WindowsManageExampleTest class.
 *  Manage current window.
 *  Driver.manage.window()
 *  .maximise
 *  .getSize
 *  .setSize: xSize use Dimensions
 *  .getPosition
 *  .setPosition: xPosition use Point
 */
public class WindowsManageExampleTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
    }

    /**
     *  manageWindow method.
     *  Manage current window.
     *  Driver.manage.window()
     *  *  .getSize
     *  *  .setSize: xSize use Dimensions
     *  *  .getPosition
     *  *  .setPosition: xPosition use Point
     */
    @Test
    public void manageWindow(){

        // Moved to 40 for Y to cope with Mac drop down menu.
        // Position moves the window around. Position uses a Point object
        driver.manage().window().setPosition(new Point(10,40));
        Point pos = driver.manage().window().getPosition();

        // Positions uses X & Y coordinates
        assertEquals(10, pos.getX());
        assertEquals(40, pos.getY());

        // Size... resized the browser window
        driver.manage().window().setSize(new Dimension(350,400));
        // Size uses Dimension i.e. width and height
        Dimension winSizes = driver.manage().window().getSize();

        assertEquals(513, winSizes.getWidth());
        assertEquals(400, winSizes.getHeight());
    }

    @After
    public void quitToClean() {
        //driver.quit();
    }
}
