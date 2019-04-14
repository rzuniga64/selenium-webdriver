package webdriver.screenshots;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 *  ScreenshotsExampleTest class.
 *
 *  Taking Screenshots
 *  - Cast WebDriver to TakesScreenshot
 *    - getScreenshotAs(OutputType…)
 *      - OutputType.FILE	// creates a temp file
 *      - OutputType.BASE64  // return a String in Base64 format
 *      - OutputType.BYTES	// good for remote driver usage
 *
 *  A file is a binary object which we can then use. So if you are working remotely, getting a BASE64 String back or an
 *  array of bytes is useful because then you can write it locally, Even though it’s come from a remove server.
 *
 *  Check for capability
 *  - If ((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)) {...}
 *  OR cast with Exception
 *  - try {
 *          TakesScreenshot snapper = (TakesScreenshot) driver;
 *    } catch(ClassCastException e) {...}
 */
public class ScreenshotsExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void configureBrowser(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://seleniumsimplified.com");
    }

    /**
     *  Navigate to a page and take a screenshot.
     * @throws IOException IOException.
     */
    @Test
    public void takeAScreenshot() throws IOException {

        // You must cast a driver to TakesScreenshot.
        TakesScreenshot snapper = (TakesScreenshot)driver;

        // Save the screenshot to a file.
        File tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);
        // Proof that it does take a screenshot.
        // System.out.println(tempScreenshot.getAbsolutePath());

        // Since it is a temporary file, we really want to control that screenshot and use it as part of our test
        // reporting. Create a directory for all the screenshots.
        File myScreenshotDirectory = new File("C:\\temp\\screenshots\\");
        myScreenshotDirectory.mkdirs();

        // create a file to move that screenshot into.
        File myScreenshot = new File(myScreenshotDirectory, "gotoPageScreen.png");
        if(myScreenshot.exists()){
            FileUtils.deleteQuietly(myScreenshot);
        }

        // Move the temp file to the new file.
        FileUtils.moveFile(tempScreenshot, myScreenshot);
        // Assert the file is not empty.
        assertThat(myScreenshot.length(), is(greaterThan(0L)));
        // open the file in the browser.
        driver.get("file://" + myScreenshot.getAbsolutePath());
    }

    @AfterClass
    public static void quitDriver(){
        //driver.quit();
    }
}
