package webdriver.basics.interrogation.exercises;

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
 *  FindElements Exercises
 *
 *  Assert that there are 19 div elements.
 *  Assert that there a re 25 <a> which href to a paragraph. Hint: List has a .size() method
 *  Assert that there are 16 nested paragraphs and 41 paragraphs in total.
 */
public class FindElementsExercisesTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() throws IOException {

        driver = Driver.get("webdriver.chrome.driver","CHROME" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  Count the number of div elements.
     */
    @Test
    public void assertDivElementsCount(){

        List<WebElement> elements = driver.findElements(By.tagName("div"));
        assertEquals(19,elements.size());
    }

    /**
     *  Count the number of href links to paragraphs.
     */
    @Test
    public void assert25LocalHrefLinks(){

        List<WebElement> elements = driver.findElements(By.partialLinkText("jump to para"));
        assertEquals(25,elements.size());
    }

    /**
     *  Count the number of paragraphs and nest paragrapsh.
     */
    @Test
    public void assertNumberOfParagraphs(){

        List<WebElement> elements = driver.findElements(By.tagName("p"));

        int nestedCount = 0;
        for(WebElement e : elements){
            if(e.getText().contains("nested para")){
                nestedCount++;
            }
        }
        assertEquals(16,nestedCount);
        assertEquals(41, elements.size());
    }

    /**
     *  Does findElements throw an exception?
     */
    @Test
    public void doesFindElementsThrowAnExceptionIfNoMatch(){

        List<WebElement> elements = driver.findElements(By.tagName("bob"));
        assertEquals(0,elements.size());
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}