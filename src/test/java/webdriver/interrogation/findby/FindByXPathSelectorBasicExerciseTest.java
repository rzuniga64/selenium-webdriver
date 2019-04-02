package webdriver.interrogation.findby;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *  XPath Basic Exercises
 *
 *  use By.xpath as replance for By.id, By.name, By.classname, By.tagName
 *
 *  Replace                     Assert
 *  By.id('p.31')               getAttribute('name') == 'pName31'
 *  By.name('ulName1')          getAttribute('id') == 'ul1'
 *  By.className('specialDiv')  getAttribute('id') == 'div1'
 *  By.tagName('li')            getAttribute('name') == 'liName1'
 *
 *  Create test first using By.id(etc.)
 *  Check test works
 *  Replace By.id(etc.) with By.xpath
 */
public class FindByXPathSelectorBasicExerciseTest {

    static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){
        driver = Driver.get("selenium2basics.webdriver","HTMLUNIT" );
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void findByUsingXPathDataDriven(){

        class TestData{
            private String xpath;
            private String name;
            private String value;
            private String alt;

            private TestData(String xpath, String name,
                            String value, String alternativeTo) {
                this.xpath = xpath;
                this.name = name;
                this.value=value;
                this.alt = alternativeTo;
            }
        }

        List<TestData> dataItems = Arrays.asList(
                new TestData("//*[@id='p31']", "name", "pName31", "By.id(\"p31\")"),
                new TestData("//p[@id='p31']", "name", "pName31", "By.id(\"p31\")"),
                new TestData("//*[@name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
                new TestData("//ul[@name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
                new TestData("//*[starts-with(@name,'ulName1') and string-length(@name)=7]", "id", "ul1", "By.name(\"ulName1\")"),
                new TestData("//*[@class='specialDiv']", "id", "div1","By.className(\"specialDiv\")"),
                new TestData("//div[@class='specialDiv']", "id", "div1","By.className(\"specialDiv\")"),
                new TestData("//li", "name", "liName1", "By.tagName(\"li\")")
        );

        WebElement element;

        for(TestData dataItem : dataItems){
            element = driver.findElement(By.xpath(dataItem.xpath));

            System.out.println(
                    String.format(
                            "Instead of %s use By.xpath(\"%s\")",
                            dataItem.alt, dataItem.xpath.replaceAll("\"", "\\\\\"")));


            assertThat(element.getAttribute(dataItem.name), is(dataItem.value));
        }
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
