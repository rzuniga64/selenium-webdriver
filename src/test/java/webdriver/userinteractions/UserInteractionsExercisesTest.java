package webdriver.userinteractions;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 *  public class UserInteractionsExercisesTest class
 *
 *  1. Move draggable1 on to droppable1
 *     a. assert droppable1 text changed to "Dropped!"
 *  2. Drag and Drop draggable2 to droppable1
 *     a. Assert droppable 1 text change to "Get Off Me!"
 *  3. Press control+B and assert for text change on draggable1 to "Bwa! Ha! Ha!"
 *  Optional Challenges:
 *     a. Press control + B and assert for text change on draggable1 to "Bwa! Ha! Ha!"
 *     b. Can you draw something in the canvas?
 */
public class UserInteractionsExercisesTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {
        driver = Driver.get();
        driver = Driver.get("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");
    }

    @Before
    public void resetPage(){

        driver.navigate().refresh();

        // Added additional Synchronisation for Opera as the refresh did not block in any way making the test
        // intermittent, but this is a good example that sometimes extra sync is required, but doesn't impact the speed
        // or readability of the code
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("canvas")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("keyeventslist")));

        // Appium does not support ExpectedConditions.elementToBeClickable @20160608
        // So after checking that the elements are present, we will assume we are synchronised
        if(Driver.currentDriver == Driver.BrowserName.APPIUM){
            return;
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.id("canvas")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("keyeventslist")));

        // user interactions can be intermittent so click on the html to force focus to the page but this workaround no
        // longer works on Chrome in 2.46.0 and driver 2.16
        if(Driver.currentBrowser() != Driver.BrowserName.CHROME) {
            driver.findElement(By.tagName("html")).click();
        }
    }

    /**
     *  Move draggable1 on to droppable1 and assert droppable1 text changed to "Dropped!"
     */
    @Test
    public void moveDraggable1ToDroppable1(){

        WebElement draggable1 = driver.findElement(By.id("draggable1"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(draggable1).moveToElement(droppable1).release().perform();

        assertEquals("Dropped!", droppable1.getText());
    }

    /**
     *  Drag and Drop draggable2 to droppable1 and assert droppable 1 text change to "Get Off Me!"
     */
    @Test
    public void dragAndDropDraggable2ToDroppable1(){

        WebElement draggable2 = driver.findElement(By.id("draggable2"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);

        // old code had a bug where I released after the drag and drop,
        // that now throws an error so removed spurious release
        actions.dragAndDrop(draggable2,droppable1).perform();

        assertEquals("Get Off Me!", droppable1.getText());
    }

    /**
     *  Press control + B and assert for text change on draggable1 to "Bwa! Ha! Ha!"
     */
    @Test
    public void controlAndSpace(){

        // when I press control + space the red squares say "Let GO!!" we can't check this.
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);
        actions.click(droppable1).build().perform();

        // sendkeys does a keydown followed by keyup, so you can't use it for this as keys need to be held down
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).build().perform();
        String dropText = droppable1.getText();
        actions.keyUp(droppable1,Keys.CONTROL).build().perform();

        try{
            assertEquals("Let GO!!", dropText);
            fail("send keys should not be held down long enough to get the text");
        }catch(ComparisonFailure e){
            assertTrue("How can we hold down the keys?",true);
            assertEquals("Drop Here", dropText);
        }
    }

    /**
     *  Optional Challenges: Press control + B and assert for text change on draggable1 to "Bwa! Ha! Ha!"
     */
    @Test
    public void controlSpace(){

        /* when we issue a control + B draggable 1 says "Bwa! Ha! Ha! */
        WebElement draggable1 = driver.findElement(By.id("draggable1"));
        draggable1.click();

        new Actions(driver).keyDown(Keys.CONTROL).
                            sendKeys("b").
                            keyUp(Keys.CONTROL).
                            perform();

        assertEquals("Bwa! Ha! Ha!", draggable1.getText());
        // firefox used to fail on this when it did a keyup after every keyDown
    }

    /**
     *  Optional Challenges: Can you draw something in the canvas?
     */
    @Test
    public void drawSomethingOnCanvas(){

        WebElement canvas = driver.findElement(By.id("canvas"));
        WebElement eventList = driver.findElement(By.id("keyeventslist"));

        int eventCount = eventList.findElements(By.tagName("li")).size();
        new Actions(driver).
                // click doesn't do it, need to click and hold
                clickAndHold(canvas).
                moveByOffset(10,10).
                release().
                perform();

        assertTrue("we should have had some draw events",
                eventCount < eventList.findElements(By.tagName("li")).size());
    }
}