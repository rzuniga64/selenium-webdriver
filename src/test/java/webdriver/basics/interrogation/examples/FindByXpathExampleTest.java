package webdriver.basics.interrogation.examples;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 *  FindByXpathExampleTest class.
 *  Xpath language spec: https://www.w3.org/TR/xpath/all/.
 *  XPath has the ability to use functions.
 *  Xpath can check the content of a field.
 *  CSS can’t do any queries on the actual paragraph text but you can do that with XPath.
 *  XPath is essentially like a directory structure.
 *  XPath is a programming language.
 *  XPath has more functions than CSS so it may be more flexible
 *
 *  //                          Match anywhere
 *  /                           Match from root
 *  //*                         Any element
 *  //tag                       Named element
 *  //*[@attribute]             Match it has attribute
 *  //*[*attribute='value']     Attribute with value
 *  .                           For content matching
 *  ..                          For traveersing back up the path e.g. //div/p/a ..
 *  Operators: and, or, etc.    w3schools.com/xpath/xpath_operators.asp
 *  Xpath functions
 *  e.g. contains, starts-with  w3schools.com/xpath/xpath_functions.asp
 */
public class FindByXpathExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  assertNumberOfParagraphs method.
     *  Look for any paragraph and count the number of nested paragraphs and paragraphs.
     */
    @Test
    public void assertNumberOfParagraphs(){

        List<WebElement> elements = driver.findElements(By.xpath("//p"));

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
     *  findSpecificPara method.
     *  Look for a web element that is a paragraph with name equal to pName5.
     */
    @Test
    public void findSpecificPara(){

        WebElement element = driver.findElement(By.xpath("//p[@name='pName5']"));
        assertThat("Expected matching id", "p5", is(element.getAttribute("id")));
    }

    /**
     *  pathNavigation method.
     *  Find an anchor with a name of aName26 underneath a div with an id of div 18.
     */
    @Test
    public void pathNavigation(){

        WebElement element = driver.findElement(By.xpath("//div[@id='div18']//a[@name='aName26']"));
        assertThat("Expected matching id", "a26", is(element.getAttribute("id")));
    }

    /**
     *  conditionalAttributes method.
     *  Find an anchor with name aName26 and class equal to 'normal'.
     */
    @Test
    public void conditionalAttributes(){

        WebElement element = driver.findElement(By.xpath("//a[@name='aName26' and @class='normal']"));
        assertThat("Expected matching id", "a26", is(element.getAttribute("id")));
    }


    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
