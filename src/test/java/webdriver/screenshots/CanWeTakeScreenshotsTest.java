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

public class CanWeTakeScreenshotsTest {

    @Before
    public void configureBrowser(){
        // early versions of these examples used to set the browser to Firefox
        // 20180611 I don't really see the point in that now that most browsers can take screenshots
        // and the tests have a guard to check if the capability is present

        // uncomment this line if you want to use firefox
        //Driver.set(Driver.BrowserName.FIREFOX);
    }

    @Test
    public void canWeTakeAScreenshotCapabilitiesStyle(){

        WebDriver driver = Driver.get("http://seleniumsimplified.com");

        if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            Driver.get("File://"+ tempImageFile.getAbsolutePath());
        }else{
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void canWeTakeAScreenshotExceptionStyle(){

        WebDriver driver = Driver.get("http://seleniumsimplified.com");

        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            Driver.get("File://"+ tempImageFile.getAbsolutePath());

        }catch(ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void htmlUnitDoesNotDoScreenshotsViaCapabilities(){
        Driver.set(Driver.BrowserName.HTMLUNIT);
        // using a different page because sometimes HTMLUnit driver doesn't like the javascript
        WebDriver driver = Driver.get("http://compendiumdev.co.uk/selenium/testpages/find_by_playground.php");

        HasCapabilities capabilityDriver = (HasCapabilities)driver;

        if(capabilityDriver.getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            fail("Expected htmlunit to report false for takes_screenshot");
        }
    }

    @Test
    public void htmlUnitDoesNotDoScreenshotsViaException(){
        Driver.set(Driver.BrowserName.HTMLUNIT);
        // using a different page because sometimes HTMLUnit driver doesn't like the javascript
        WebDriver driver = Driver.get("http://compendiumdev.co.uk/selenium/testpages/find_by_playground.php");

        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            fail("Expected htmlunit to not cast to takes_screenshot");

        }catch(ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
        }
    }

    @After
    public void quitDriver(){
        Driver.quit();
    }

    /*
    Because these tests change the driver, when run from IDE
    We want to remember the current driver and restore after all tests are run
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
