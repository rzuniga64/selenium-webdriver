package webdriver.basics.manipulation.windows;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  WindowManageExerciseTest class.
 *  Using http://www.compendiumdev.co.uk/selenium/bounce.html
 *  Maximise the window
 *  Reduces the window to half size
 *  Move the reduced window into the center of the screen
 *  Just for fun, make it smaller and bounce it around the screen
 */
public class WindowManageExerciseTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/bounce.html");
    }

    /**
     *  Maximize the window.
     */
    @Test
    public void maximizeTheWindow(){

        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();

        // the awt dimension checks will fail on grid because it uses local AWT dimensions
        // do not perform the checks if running on GRID
        if(Driver.currentDriver == Driver.BrowserName.GRID || Driver.currentDriver == Driver.BrowserName.SAUCELABS)
            return;

        java.awt.Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        String expected="";

        expected = ((int)screenDimension.getWidth()) + " approx (90% tolerance) " + fullScreenSize.getWidth();
        assertTrue(expected, (screenDimension.getWidth()*0.9) < fullScreenSize.getWidth());
        expected = ((int)screenDimension.getHeight()) + " approx (90% tolerance) " + (fullScreenSize.getHeight()*0.9);
        assertTrue(expected,(screenDimension.getHeight()*0.9) < fullScreenSize.getHeight());
    }

    /**
     *  Reduce the window to half size.
     */
    @Test
    public void halfSizeTheWindow(){

        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();
        driver.manage().window().setSize(new Dimension(fullScreenSize.getWidth()/2, fullScreenSize.getHeight()/2));

        assertEquals("Width Half Equals", fullScreenSize.getWidth()/2, driver.manage().window().getSize().getWidth());
        assertEquals("Height Half Equals", fullScreenSize.getHeight() / 2, driver.manage().window().getSize().getHeight());
    }

    /**
     *  Move the reduced window into the center of the screen.
     */
    @Test
    public void moveHalfSizeToCenterTheWindow(){

        driver.manage().window().maximize();

        Dimension fullScreenSize = driver.manage().window().getSize();
        Point pos = driver.manage().window().getPosition();

        driver.manage().window().setSize(new Dimension(fullScreenSize.getWidth() / 2, fullScreenSize.getHeight() / 2));
        driver.manage().window().setPosition(new Point(fullScreenSize.getWidth()/4, fullScreenSize.getHeight()/4));
    }

    /**
     *  Just for fun, make it smaller and bounce it around the screen.
     */
    @Test
    public void bounceTheWindow(){

        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();

        int changeWidth = fullScreenSize.getWidth();
        int changeHeight = fullScreenSize.getHeight();

        while(changeWidth > 100){
            changeWidth = changeWidth - 50;

            if(changeHeight>200)
                changeHeight = changeHeight - 50;

            driver.manage().window().setSize(new Dimension(changeWidth, changeHeight));
            try {Thread.sleep(50);} catch (InterruptedException e) {}
        }

        int xDir = 10;
        int yDir = 10;

        Point position = driver.manage().window().getPosition();

        // original examples used 1000 for MAX_ITERATIONS
        // 1000 seems too long for CI, so dropped it down to 50 to make it faster
        int MAX_ITERATIONS = 50;

        for(int bounceIterations = 0; bounceIterations < MAX_ITERATIONS; bounceIterations ++){

            position = position.moveBy(xDir,yDir);

            driver.manage().window().setPosition(position);

            if(position.getX()>(fullScreenSize.getWidth() - changeWidth)){
                xDir = -10;
            }
            if(position.getX()<0){
                xDir = 10;
            }

            if(position.getY()>(fullScreenSize.getHeight() - changeHeight)){
                yDir = -10;
            }
            if(position.getY()<0){
                yDir = 10;
            }
        }
    }

    @AfterClass
    public static void quitToClean() {
        driver.quit();
    }
}
