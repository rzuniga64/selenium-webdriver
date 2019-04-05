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

public class FindByExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){

        driver = Driver.get("webdriver.chrome.webdriver","CHROME" );
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

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
