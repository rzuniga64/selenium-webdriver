package webdriver.screenshots;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
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

public class ScreenshotsExampleTest {

    WebDriver driver;

    @Before
    public void configureBrowser(){
        // early versions of these examples used to set the browser to Firefox
        // 20180611 I don't really see the point in that now that most browsers can take screenshots
        // and the tests have a guard to check if the capability is present

        // uncomment this line if you want to use firefox
        //Driver.set(Driver.BrowserName.FIREFOX);
    }

    @Test
    public void gotoPage() throws IOException {

        //driver = new FirefoxDriver();
        //driver.get("http://seleniumsimplified.com");
        driver = Driver.get("http://seleniumsimplified.com");

        TakesScreenshot snapper = (TakesScreenshot)driver;

        File tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);

        System.out.println(tempScreenshot.getAbsolutePath());

        File myScreenshotDirectory = new File("C:\\temp\\screenshots\\");
        myScreenshotDirectory.mkdirs();

        File myScreenshot = new File(myScreenshotDirectory, "gotoPageScreen.png");
        if(myScreenshot.exists()){
            FileUtils.deleteQuietly(myScreenshot);
        }

        FileUtils.moveFile(tempScreenshot, myScreenshot);

        assertThat(myScreenshot.length(), is(greaterThan(0L)));

        driver.get("file://" + myScreenshot.getAbsolutePath());

    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
