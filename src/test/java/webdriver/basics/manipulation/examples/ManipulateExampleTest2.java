package webdriver.basics.manipulation.examples;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  ManipulateExampleTest class.
 */
public class ManipulateExampleTest2 {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage() throws IOException {

        driver = Driver.get();
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    /**
     *  myFirstManipulation method.
     *  Find category dropdown with option with a value of 3 (Server) .
     *  Find language dropdown with option with a value of 23 (Java) .
     *  Click the Submit button.
     *  Verify on the page that the language id is 23.
     */
    @Test
    public void myFirstManipulation(){

        // Select the Server option.
        driver.findElement(By.cssSelector("option[value='3']")).click();

        // An explicit waits is code you define to wait for a certain condition to occur before proceeding further in
        // the code. WebDriver has a helper class WebDriverWait to help us synchronize our tests. Used in
        // conjunction helper class ExpectedConditions.
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("option[value='23']")));

        // Select the Java option.
        driver.findElement(By.cssSelector("option[value='23']")).click();
        driver.findElement(By.name("submitbutton")).click();

        assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(), is("23"));
    }


    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
