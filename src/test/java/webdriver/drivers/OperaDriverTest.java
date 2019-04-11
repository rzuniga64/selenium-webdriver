package webdriver.drivers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  OperaDriverTest class.
 *  Cover nuances with the Opera Driver
 *  If we don't tell webdriver where to find the opera driver it will look on the path.
 *
 *  String currentDir = System.getProperty("user.dir");
 *  String chromeDriverLocation = currentDir + "\\src\\test\\resources\\chromedriver.exe";
 *  System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
 *
 *  RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
 *  System.setProperty("webdriver.gecko.driver", RESOURCE_DIR + "geckodriver.exe");
 *
 *  Add the folder of the opera driver to the system path variable (you'll want to restart the IDE)
 *  You can check if it is in the path by running 'operadriver.exe' from any folder
 *  If it runs, then opera driver is on the path. Then run the test.
 */
public class OperaDriverTest {

    private static WebDriver opera;

    @BeforeClass
    public static void setupTheOperaDriverSystemProperty(){

        // I have set the path to operadriver.exe on the PATH.
        // PATH = C:\tools\selenium
        opera = new OperaDriver();
        opera.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    // @Ignore("Test requires chromedriver on the path")
    @Test
    public void basicChromeUsage(){

        assertThat(opera.getTitle(), is("HTML Form Elements"));
    }

    @AfterClass
    public static void closeBrowser() {
//        opera.quit();
    }
}
