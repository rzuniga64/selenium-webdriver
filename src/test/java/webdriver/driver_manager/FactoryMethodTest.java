package webdriver.driver_manager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FactoryMethodTest {

    private static DriverManager driverManager;
    private WebDriver driver;

    @BeforeClass
    public static void beforeClass() {

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
        assertThat(driver.getTitle(), is("Selenium Simplified Search Engine"));
    }

    @AfterClass
    public static void afterClass() {

        driverManager.quitDriver();
    }
}
