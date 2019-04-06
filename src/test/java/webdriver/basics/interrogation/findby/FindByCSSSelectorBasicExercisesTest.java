package webdriver.basics.interrogation.findby;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  CSS Basic Exercises
 *  Use By.cssSelector as replacement for By.id, By.name, By.className, By.tagName
 *  Replace                     Assert
 *  By.id("p31")                getAttribute("name") == "pName31"
 *  By.name("ulName1")          getAttribute("id") == "u1"
 *  By.className("specialDiv")  getAttribute("id") == "div1"
 *  By.tagName("li")            getAttribute("name") == "liName1"
 *
 *  - Create test first using By.id(etc.)
 *  - Check test works
 *  - Replace By.id(etc.) with By.cssSelector
 */
public class FindByCSSSelectorBasicExercisesTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  Use By.cssSelector to find element by id and assert that getAttribute("name") == "pName31".
     */
    @Test
    public void findByIdUsingCSS(){

        WebElement element = driver.findElement(By.cssSelector("#p31"));
        assertThat("expected a match on id", "pName31", is(element.getAttribute("name")));
    }

    /**
     *  Use By.cssSelector to find element by name and assert that getAttribute("id") == "u1".
     */
    @Test
    public void findByNameUsingCSS(){

        WebElement element = driver.findElement(By.cssSelector("[name='ulName1']"));
        assertThat("expected a different id","ul1", is(element.getAttribute("id")));
    }

    /**
     *  Use By.cssSelector to find element by className and assert that  getAttribute("id") == "div1".
     */
    @Test
    public void findByClassNameUsingCSS(){

        WebElement element = driver.findElement(By.cssSelector(".specialDiv"));
        assertThat("expected a different id","div1", is(element.getAttribute("id")));
    }

    /**
     *  Use By.cssSelector to find element by tagName and assert that getAttribute("name") == "liName1"
     */
    @Test
    public void findByTagNameUsingCSS(){

        WebElement element = driver.findElement(By.cssSelector("li"));
        assertThat("expected a different name","liName1", is(element.getAttribute("name")));
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
