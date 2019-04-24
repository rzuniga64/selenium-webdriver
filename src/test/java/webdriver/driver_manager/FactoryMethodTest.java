package webdriver.driver_manager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FactoryMethodTest {

    DriverManager driverManager;
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        try {
            driverManager = DriverManagerFactory.getManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeMethod() {

        driver = driverManager.getDriver();
    }

    @Test
    public void navigateWithNavigateTo(){

        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/search.php");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }

    @AfterClass
    public void afterClass() {

        driverManager.stopService();
        driverManager.quitDriver();
    }
}
