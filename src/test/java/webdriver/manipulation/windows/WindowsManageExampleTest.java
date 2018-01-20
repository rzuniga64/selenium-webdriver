package webdriver.manipulation.windows;

import webdriver.drivermanager.Driver;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class WindowsManageExampleTest {

    private static WebDriver driver;

    @Test
    public void manageWindow(){

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        // moved to 40 for Y to cope with Mac drop down menu
        // Position moves the window around. Position uses a Point object
        driver.manage().window().setPosition(new Point(10,40));
        Point pos = driver.manage().window().getPosition();

        // Positions uses X & Y coordinates
        assertEquals(10, pos.getX());
        assertEquals(40, pos.getY());

        // Size... resiszed the browser window
        driver.manage().window().setSize(new Dimension(350,400));
        // Size uses Dimension i.e. width and height
        Dimension winSizes = driver.manage().window().getSize();

        assertEquals(350, winSizes.getWidth());
        assertEquals(400, winSizes.getHeight());
    }
}
