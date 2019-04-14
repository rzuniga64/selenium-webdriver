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

//import org.bouncycastle.util.encoders.Base64;

public class PersistScreenshotsTest {

    @Before
    public void configureBrowser(){
        // early versions of these examples used to set the browser to Firefox
        // 20180611 I don't really see the point in that now that most browsers can take screenshots
        // and the tests have a guard to check if the capability is present

        // uncomment this line if you want to use firefox
        //Driver.set(Driver.BrowserName.FIREFOX);
    }

    @Test
    public void persistOutputTypeFile() throws IOException {
        // this works well testing on a local machine

        WebDriver driver = Driver.get("http://seleniumsimplified.com");

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

    @Test
    public void persistOutputTypeBase64() throws IOException {
        // this works well testing on remote driver because
        // screenshot returned as a string to local machine

        WebDriver driver = Driver.get("http://seleniumsimplified.com");

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
            //Base64 decoder = new Base64();
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
        }else{
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void persistOutputTypeBytes() throws IOException {
        // this works well testing on remote driver because
        // screenshot returned as a string to local machine

        WebDriver driver = Driver.get("http://seleniumsimplified.com");

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

            File testTempImage = new File(testTempDir,newImageFileName);
            FileOutputStream osf = new FileOutputStream(testTempImage);
            osf.write(tempImageFileAsBytes);
            osf.flush();

            // screenshot in our local store
            assertThat(testTempImage.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
            Driver.get("File://"+ testTempImage.getAbsolutePath());
        }else{
            fail("Driver did not support screenshots");
        }
    }

    private String getExtension(File fileWithExtension) {
        String fileName = fileWithExtension.getName();
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    private File createATempDirectoryForScreenshots() {
        String s = File.separator;
        String ourTestTempPathName = System.getProperty("user.dir") +
                String.format("%ssrc%stest%sresources%stemp%sscreenshots",s,s,s,s,s);

        File testTempDir = new File(ourTestTempPathName);
        if(testTempDir.exists()){
            if(!testTempDir.isDirectory()){
                fail("Test path exists but is not a directory");
            }
        }else{
            testTempDir.mkdirs();
        }

        return testTempDir;
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
