package webdriver.remote;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 *  SaucelabsTest class.
 *
 *  Saucelabs is a grid in the cloud that we can run. You can use remote drivers without the necessary hassle of setting
 *  up a grid.  The Free Plan gives you a hundred minutes across these different browsers and 30 manual minutes per
 *  month.
 *
 *  - I can run my tests on their cloud using multiple versions of browsers across different versions of operating
 *    systems.
 *  - We get a video of every test that we run that you can download.
 *  - I can do manual testing using their girds set up so i can connect to their machines on different versions of
 *    Firefox.
 *  - It is slower than executing tests locally because you are issuing tests off to the cloud.
 *
 *  We instantiate a RemoteWebDriver with the URL of the server. Everything else is controlled through the capabilities.
 *  Then our tests should be as normal.
 */
public class SaucelabsTest {

    public static WebDriver driver = null;

    @BeforeClass
    public static void setupSauce(){

        // I want to use Firefox browser on a Mac.
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("platform", Platform.MAC);

        try {
            // add url to environment variables to avoid releasing with source
            String sauceURL = System.getenv("SAUCELABS_URL");
            driver = new RemoteWebDriver(new URL(sauceURL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleInteraction(){

        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        assertFalse("Starts not selected", checkBox1.isSelected());
        checkBox1.click();
        assertTrue("Click selects", checkBox1.isSelected());
    }

    @Test
    public void loadTheGreenPage(){

        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
        WebDriverWait wait = new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS);

        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

        // load the green page
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='green.html']")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));

        // click on "Back to original page"
        driver.findElement(By.cssSelector("a[href='content.html']")).click();

        // assert for presence of "<h1>Content</h1>"
        WebElement h1 = wait.until(presenceOfElementLocated(By.xpath("//h1[.='Content']")));

        assertThat(h1.getText(), is("Content"));
    }

    @Test
    public void workWithTheIFrame(){

        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
        WebDriverWait wait = new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS);

        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

        // click on "menu"."iFrames Example"
        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='iframe.html']")).click();

        wait.until(titleIs("HTML Frames Example - iFrame Contents"));

        // click on Example 5 in the iFrame
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("a[href='frames_example_5.html']")).click();

        wait.until(titleIs("Frameset Example Title (Example 5)"));

        // then content.load main frames page
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='index.html']")).click();

        wait.until(titleIs("Frameset Example Title (Example 6)"));
    }

    @AfterClass
    public static void stopSauce(){
        driver.quit();
    }
}
