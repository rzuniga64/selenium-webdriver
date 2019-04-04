package webdriver.basics.manipulation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import static junit.framework.TestCase.assertEquals;

public class WebDriverWaitExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() {
        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void exampleUsingExpectedConditions() {

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }

    @Test
    public void exampleWithSleepTime() {

        new WebDriverWait(driver, 10, 50).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }
}
