package webdriver.basics.interrogation.examples;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  FindByExampleTest class.
 *  Example of using findelement(By) to find an element.
 */
public class FindByExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() throws IOException {

        driver = Driver.get("webdriver.chrome.webdriver","CHROME" );
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    /**
     *  Use By.id to find element by id.
     */
    @Test
    public void findByID(){

        WebElement cParagraph = driver.findElement(By.id("p3"));
        assertThat(cParagraph.getText(), is("This is c paragraph text"));
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
