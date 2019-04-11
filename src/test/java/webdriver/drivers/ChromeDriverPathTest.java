package webdriver.drivers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  ChromeDriverPathTest class.
 *  Cover nuances with the Chrome Driver
 *  If we don't tell webdriver where to find the chrome driver it will look on the path
 *
 *  String currentDir = System.getProperty("user.dir");
 *  String chromeDriverLocation = currentDir + "\\src\\test\\resources\\chromedriver.exe";
 *  System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
 *
 *  Add the folder of the chrome driver to the system path variable (you'll want to restart the IDE)
 *  You can check if it is in the path by running 'chromedriver.exe' from any folder
 *  If it runs, then chrome driver is on the path. Then run the test.
 */
public class ChromeDriverPathTest {

    private static WebDriver chrome;

    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        // I have set the path to chromedriver.exe on the PATH.
        // PATH = C:\tools\selenium
        chrome = new ChromeDriver();
        chrome.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    // @Ignore("Test requires chromedriver on the path")
    @Test
    public void basicChromeUsage(){

        assertThat(chrome.getTitle(), is("HTML Form Elements"));
        chrome.quit();
    }

    @AfterClass
    public static void closeBrowser() {
        chrome.quit();
    }
}
