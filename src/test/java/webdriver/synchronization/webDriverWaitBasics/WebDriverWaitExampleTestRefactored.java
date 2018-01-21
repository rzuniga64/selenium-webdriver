package webdriver.synchronization.webDriverWaitBasics;

import webdriver.drivermanager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class WebDriverWaitExampleTestRefactored {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void gotoPage(){
        driver = Driver.get("selenium2basics.webdriver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");

        // create a default wait
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void constructWaitWithNoSleepTime(){
        // default sleep time of 500 milliseconds
        wait.until(titleIs("HTML Form Elements"));

        // look no assert, rely on the wait
    }

    @Test
    public void constructWaitWithSleepTimeOf50Milliseconds(){
        // use a sleep time of 50 milliseconds
        wait = new WebDriverWait(driver,10,50);

        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));
    }
}

















