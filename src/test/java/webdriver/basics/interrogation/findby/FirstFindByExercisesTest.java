package webdriver.basics.interrogation.findby;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

/**
 *  First find By Exercises
 *
 *  Using http://www.conpendiumdev.co.uk
 *  /selenium/find_by/playground.php: inspect the DOM or look at the page source to identify locator approaches.
 *
 *  findElement and then assert. If multiple elements match then findElement will return the first.
 *  'NoSuchElementException' means that the locator didn't match anything in the DOM.
 *
 *  Create a test for each By
 *  - By.id
 *  - By.linkTest
 *  - By.name
 *  - By.partialLinkText
 *  - By.className
 */
public class FirstFindByExercisesTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  Match by an element's id attribute.
     */
    @Test
    public void findByID(){

        WebElement cParagraph = driver.findElement(By.id("p3"));
        assertThat("This is c paragraph text", is(cParagraph.getText()));
    }

    /**
     *  Look for an <a> via the text (i.e. getText)
     */
    @Test
    public void findByLinkText(){

        WebElement jumpToPara0 = driver.findElement(By.linkText("jump to para 0"));
        assertThat("a26", is(jumpToPara0.getAttribute("id")));
    }

    /**
     *  Match by an element's name attribute.
     */
    @Test
    public void findByName(){

        WebElement aParagraph = driver.findElement(By.name("pName1"));
        assertThat("This is a paragraph text", is(aParagraph.getText()));
    }

    /**
     *  Match an <a> using part of the link text
     */
    @Test
    public void findByPartialLinkText(){

        // match beginning of text
        WebElement jump_to = driver.findElement(By.partialLinkText("jump to"));
        assertThat("jump to para 0", is(jump_to.getText()));

        // match middle of text
        jump_to = null;
        jump_to = driver.findElement(By.partialLinkText("to"));
        assertThat("jump to para 0", is(jump_to.getText()));

        // match at end of text
        jump_to = null;
        jump_to = driver.findElement(By.partialLinkText("7"));
        assertThat("jump to para 7", is(jump_to.getText()));
    }

    /**
     *  Match by an element's class attribute.
     */
    @Test
    public void findByClassName(){

        WebElement aDiv = driver.findElement(By.className("specialDiv"));
        assertThat("mydivname", is(aDiv.getAttribute("name")));
    }

    /**
     *  We wanted to find an exception. Make sure you import the selenium exception
     *  import org.openqa.selenium.NoSuchElementException;
     */
    @Test
    public void noSuchElementException_thrownWhenLocatorWrong(){

        try{
            // id is p3, name is p3Name, this will fail
            WebElement cParagraph = driver.findElement(By.id("p3Name"));
            fail("Expected NoSuchElementException");

        } catch(NoSuchElementException e){
        }
    }

    /**
     *  We wanted to find an exception. id is p3, name is p3Name, this will fail.
     */
    @Test(expected = NoSuchElementException.class)
    public void noSuchElementException_thrownWhenLocatorWrong_Expected(){

        WebElement cParagraph = driver.findElement(By.id("p3Name"));
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}

