package webdriver.basics.interrogation.examples;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 *  FindByCSSSelectorPathsExampleTest class.
 *  A > B           B directly under A, e.g. <A><B/></A>
 *  A B             Descendent
 *                  - Selectors separated by space, i.e. "this then that"
 *                  - Any degree of separation.
 *                  - e.g. "div li" would match but "div > li" would not
 *  A + B           B siblings of A
 *  B: first child  Every B which is the first child of something.
 *
 *  For more selectors see the reference
 */
public class FindByCSSSelectorPathsExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){

        driver = Driver.get("selenium2basics.webdriver","HTMLUNIT" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  directDescendant method.
     *  Look for any paragraph that is directly under a div.
     *  It should not return any li directly under a div.
     */
    @Test
    public void directDescendant(){

        assertEquals("div > p", 41, driver.findElements(By.cssSelector("div > p")).size());
        assertEquals("div > li", 0, driver.findElements(By.cssSelector("div > li")).size());

    }

    /**
     *  anyDescendant method.
     *  Look for any paragraph under any div in the hierarchy.
     *  Look for any li under any div in the hierarchy.
     */
    @Test
    public void anyDescendant(){

        assertEquals("div p", 41, driver.findElements(By.cssSelector("div p")).size());
        assertEquals("div li", 25, driver.findElements(By.cssSelector("div li")).size());
    }

    /**
     *  siblingOfPreceding method.
     *  Looks for any li.
     *  Looks for any li that has a li as a sibling. li is in a big list so li + li will skip the first li.
     */
    @Test
    public void siblingOfPreceding(){

        assertEquals("li", 25, driver.findElements(By.cssSelector("li")).size());
        assertEquals("li + li", 24, driver.findElements(By.cssSelector("li + li")).size());
    }

    /**
     *  firstChild method.
     *  Get the first child or the first li that is a child of an WebElement.
     */
    @Test
    public void firstChild(){

        assertEquals("li:first-child", 1,
                driver.findElements(By.cssSelector("li:first-child")).size());
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
