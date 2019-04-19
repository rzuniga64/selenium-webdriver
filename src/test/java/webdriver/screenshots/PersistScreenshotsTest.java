package webdriver.screenshots;

import webdriver.drivermanager.Driver;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.fail;

/**
 * Ensure driver has the capability to take a screenshot then
 * - Save screenshot to file with a random name.
 * - Save BASE64 string to file with random name. Hint: Base64 object can decode before writing to a FileOutputStream
 * - Save BYTE to a file with a random name. Hint: FileOutputStream can write a byte[]
 *
 *  Use Output.FILE when we are running locally. When we are running with a remote webdriver we want to use
 *  OutputType.BASE64 or OutputType.BYTES.
 */
public class PersistScreenshotsTest {

    private static WebDriver driver;

    @Before
    public void configureBrowser() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to("http://seleniumsimplified.com");
    }

    /**
     *  persistOutputTypeFile method.
     *  Ensure driver has the capability to take a screenshot then save screenshot to file with a random name.
     *  Use Output.FILE when we are running locally.
     *  @throws IOException
     */
    @Test
    public void persistOutputTypeFile() throws IOException {

        // this works well testing on a local machine
        if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){

            // create screenshot
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // create file and dirs to store for our test locally
            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = "persistOutputTypeFile" +
                                       new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                                        "." +
                                       getExtension(tempImageFile);

            File testTempImage = new File(testTempDir, newImageFileName);

            // move screenshot to our local store
            FileUtils.moveFile(tempImageFile, testTempImage);
            assertThat(testTempImage.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
            Driver.get("File://"+ testTempImage.getAbsolutePath());
        }else{
            fail("Driver did not support screenshots");
        }
    }

    /**
     *  persistOutputTypeBase64 method.
     *  Ensure driver has the capability to take a screenshot then save BASE64 string to file with random name.
     *  Hint: Base64 object can decode before writing to a FileOutputStream
     *
     *  When we are running with a remote webdriver we want to use OutputType.BASE64 or OutputType.BYTES.
     *  Whether use one or the other depends if you want to do any processing on that image beforehand.
     *  @throws IOException
     */
    @Test
    public void persistOutputTypeBase64() throws IOException {

        // this works well testing on remote driver because screenshot returned as a string to local machine
        if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){

            // create screenshot
            TakesScreenshot snapper = (TakesScreenshot)driver;
            String tempImageFileAsBase64 = snapper.getScreenshotAs(OutputType.BASE64);

            assertThat(tempImageFileAsBase64.length(), is(greaterThan(0)));

            // create file and dirs to store for our test locally
            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = "persistOutputTypeBase64" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";

            // WebDriver 3 no longer has a Base64 encoder bundled so use the one in Java 8 since WebDriver needs 1.8
            byte[] imgBytes = Base64.getDecoder().decode(tempImageFileAsBase64);
            File testTempImage = new File(testTempDir, newImageFileName);
            FileOutputStream osf = new FileOutputStream(testTempImage);
            osf.write(imgBytes);
            osf.flush();

            // screenshot in our local store
            assertThat(testTempImage.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
            Driver.get("File://"+ testTempImage.getAbsolutePath());
        } else {
            fail("Driver did not support screenshots");
        }
    }

    /**
     *  persistOutputTypeBytes method.
     *  Ensure driver has the capability to take a screenshot then save BYTE to a file with a random name.
     *  Hint: FileOutputStream can write a byte[]
     *
     *  When we are running with a remote webdriver we want to use OutputType.BASE64 or OutputType.BYTES.
     * @throws IOException
     */
    @Test
    public void persistOutputTypeBytes() throws IOException {

        // this works well testing on remote driver because screenshot returned as a string to local machine
        if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){

            // create screenshot
            TakesScreenshot snapper = (TakesScreenshot)driver;
            byte[] tempImageFileAsBytes = snapper.getScreenshotAs(OutputType.BYTES);

            assertThat(tempImageFileAsBytes.length, is(greaterThan(0)));

            // create file and dirs to store for our test locally
            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = "persistOutputTypeBytes" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";

            File testTempImage = new File(testTempDir, newImageFileName);
            FileOutputStream osf = new FileOutputStream(testTempImage);
            osf.write(tempImageFileAsBytes);
            osf.flush();

            // screenshot in our local store
            assertThat(testTempImage.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
            Driver.get("File://"+ testTempImage.getAbsolutePath());
        } else {
            fail("Driver did not support screenshots");
        }
    }

    /**
     *  getExtension method.
     * @param fileWithExtension file with extension.
     * @return a string with file name and extension.
     */
    private String getExtension(File fileWithExtension) {

        String fileName = fileWithExtension.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Create a temporary directory for screenshots.
     * @return a File.
     */
    private File createATempDirectoryForScreenshots() {

        // If running on Linux or Mac this will get the appropriate file separator in the string.
        String s = File.separator;
        String ourTestTempPathName = System.getProperty("user.dir") +
                String.format("%ssrc%stest%sresources%sscreenshots",s,s,s,s);

        File testTempDir = new File(ourTestTempPathName);
        if(testTempDir.exists()){
            if(!testTempDir.isDirectory()){
                fail("Test path exists but is not a directory");
            }
        } else {
            testTempDir.mkdirs();
        }

        return testTempDir;
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
