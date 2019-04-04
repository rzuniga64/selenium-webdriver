package webdriver.basics.navigation;

import webdriver.drivermanager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 *  NavigationExampleTest class.
 *  We are using a factory class to get an instance of a WebDriver.
 *  The driver.quit() is removed because the static class uses some Java functionality to create a JVM shutdown hook.
 *  So when the JVM stops, the driver stops automatically. 
 */
public class NavigationExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver(){

        driver = Driver.get("webdriver.gecko.driver","FIREFOX" );
    }

    @Test
    public void navigateWithNavigateTo(){

        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/search.php");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }

    @AfterClass
    public static void quitDriver(){
        //driver.quit();
    }
}
