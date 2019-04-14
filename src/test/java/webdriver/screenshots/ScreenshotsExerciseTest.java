package webdriver.screenshots;

import webdriver.drivermanager.Driver;
import org.junit.*;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 *  ScreenshotsExerciseTest class.
 *
 *  Can we take a screenshot?
 *  - Create a firefox driver
 *    - Use getCapabilities to check that it can create a screenshot
 *    - Cast directly to snapper and catch an ClassCastException
 *  Create an HtmlUnitDriver
 *    - Use getCapabilities to check that it can not create a screenshot
 *    - Cast directly to snapper and catch an ClassCastException.
 */
public class ScreenshotsExerciseTest {

    private static WebDriver driver;

    /**
     *  canWeTakeAScreenshotCapabilitiesStyle method.
     *  Create a firefox driver and use getCapabilities to check that it can create a screenshot.
     */
    @Test
    public void canWeTakeAScreenshotCapabilitiesStyle(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://seleniumsimplified.com");

        if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            // You must cast a driver to TakesScreenshot.
            TakesScreenshot snapper = (TakesScreenshot)driver;
            // Save the screenshot to a file.
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            // Assert the file is not empty.
            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // Use these lines in debug mode.
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            // Open the file in the browser.
            Driver.get("File://"+ tempImageFile.getAbsolutePath());
        } else {
            fail("Driver did not support screenshots");
        }
    }

    /**
     *  canWeTakeAScreenshotExceptionStyle method.
     *  Create a firefox driver and cast directly to snapper and catch an ClassCastException. Check that it can take a
     *  screenshot.
     */
    @Test
    public void canWeTakeAScreenshotExceptionStyle(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://seleniumsimplified.com");

        try {
            // You must cast a driver to TakesScreenshot.
            TakesScreenshot snapper = (TakesScreenshot)driver;
            // Save the screenshot to a file.
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            // Assert the file is not empty.
            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            // Open the file in the browser.
            Driver.get("File://"+ tempImageFile.getAbsolutePath());

        } catch(ClassCastException e) {
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            e.printStackTrace();
            fail("Driver did not support screenshots");
        }
    }

    /**
     *  htmlUnitDoesNotDoScreenshotsViaCapabilities method.
     *  Create an HtmlUnitDriver and use getCapabilities to check that it can not create a screenshot.
     */
    @Test
    public void htmlUnitDoesNotDoScreenshotsViaCapabilities(){

        driver = Driver.get("webdriver.htmlunit.driver", "HTMLUNIT");
        //Driver.set(Driver.BrowserName.HTMLUNIT);
        // using a different page because sometimes HTMLUnit driver doesn't like the javascript
        driver = Driver.get("http://compendiumdev.co.uk/selenium/testpages/find_by_playground.php");

        HasCapabilities capabilityDriver = (HasCapabilities)driver;

        if(capabilityDriver.getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            fail("Expected htmlunit to report false for takes_screenshot");
        }
    }

    /**
     *  htmlUnitDoesNotDoScreenshotsViaException method.
     *  Create an HtmlUnitDriver and cast directly to TakesScreenshot and catch an ClassCastException. Check that it can
     *  not create a screenshot.
     */
    @Test
    public void htmlUnitDoesNotDoScreenshotsViaException(){

        driver = Driver.get("webdriver.htmlunit.driver", "HTMLUNIT");
        //Driver.set(Driver.BrowserName.HTMLUNIT);
        // using a different page because sometimes HTMLUnit driver doesn't like the javascript
        driver = Driver.get("http://compendiumdev.co.uk/selenium/testpages/find_by_playground.php");

        try {
            // You must cast a driver to TakesScreenshot.
            TakesScreenshot snapper = (TakesScreenshot)driver;
            fail("Expected htmlunit to not cast to takes_screenshot");

        } catch (ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            e.printStackTrace();
        }
    }

    @After
    public void quitDriver(){
        Driver.quit();
    }

    /**
     *  Because these tests change the driver, when run from IDE.
     *  We want to remember the current driver and restore after all tests are run.
     */
    private static Driver.BrowserName rememberDriver;

    @BeforeClass
    public static void storeCurrentBrowser(){
        rememberDriver = Driver.currentDriver;
    }

    @AfterClass
    public static void restoreDriver(){
        Driver.set(rememberDriver);
    }

}
