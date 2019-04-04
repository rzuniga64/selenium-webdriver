package webdriver.basics.interrogation.findby;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class FindByExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){
        driver = Driver.get("selenium2basics.webdriver","HTMLUNIT" );
        driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void findByID(){
        WebElement cParagraph = driver.findElement(By.id("p3"));
        assertEquals("This is c paragraph text", cParagraph.getText());
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
