package webdriver.basics.interrogation.examples;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  FindByCssSelectorExampleTest class.
 *  The CSS selector is a formula or Matcher. If the page changes you can probably get to it using a CSS selector.
 */
public class FindByCssSelectorExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage()  throws IOException {

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  Find an element by using CSS id.
     */
    @Test
    public void findByIdUsingCSSId(){

        WebElement element;
        element = driver.findElement(By.cssSelector("#p3"));

        assertEquals("expected a match on id", "This is c paragraph text", element.getText());
    }

    /**
     *  Find an element by using CSS tag.
     */
    @Test
    public void findElementsUsingCSSTag(){

        List<WebElement> elements;
        elements = driver.findElements(By.cssSelector("p"));

        assertEquals("expected a different number", 41, elements.size());
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
