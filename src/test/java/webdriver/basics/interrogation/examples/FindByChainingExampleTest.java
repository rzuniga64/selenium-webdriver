package webdriver.basics.interrogation.examples;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import webdriver.drivermanager.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  Chaining .findElements(s)
 *  findElement(by.id(".")).findElement(By.name(".")), e.g.
 *  WebElement element = driver.findElement(By.id("div1"))
 *                              .findElement(By.name("pName3"))
 *                              .findElement(By.tagName("a"));
 *  - Can use any By locator strategy
 *  - Cannot Chain .findElements() as it return a List of Web Elements.
 *
 *  Chaining with ByChained
 *  - ByChained is a support class
 *    import org.openqa.selenium.support.pagefactory.ByChained;
 *  - ByChained extends By (it is a By)
 *  - Instantiate it and pass in the By objects
 *
 *  Have to instantiate not use statically, takes list of By objects
 *  element = driver.findElement(
 *              new ByChained(
 *                  By.id("div1"),
 *                  By.name("pName9"),
 *                  By.tagName("a")));
 *
 *  Other By Support Classes
 *  - ByIdOrName("string to match")
 *
 *  @Test
 *  public void findByIdOrNameMatchOnName() {
 *      WebElement element;
 *      element = driver.findElement(new ByIdOrName("pName2"));  //takes a String which could be the ID or the Name
 *      assertEquals("expected a match on name", "This is b paragraph text", element.getText());
 *  }
 *  }
 */
public class FindByChainingExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  Chaining using findElement By method.
     */
    @Test
    public void chainingWithFindElement(){

        WebElement element = driver.findElement(By.id("div1")).
                                    findElement(By.name("pName3")).
                                    findElement(By.tagName("a"));

        assertThat("expected a different id", "a3", is(element.getAttribute("id")));
    }

    /**
     *  Chaining by using ByChained By method. ByChained is a custom By method.
     */
    @Test
    public void chainingWithSupportClassByChained(){

        WebElement element;
        element = driver.findElement(
                             new ByChained(
                                By.id("div1"),
                                By.name("pName9"),
                                By.tagName("a")));

        assertThat("expected a different id", "a9", is(element.getAttribute("id")));
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
