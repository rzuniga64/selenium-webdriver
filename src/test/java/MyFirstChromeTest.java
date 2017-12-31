import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

@RunWith(BlockJUnit4ClassRunner.class)
public class MyFirstChromeTest extends TestCase {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() {
        service = new ChromeDriverService.Builder()

            // You need to download the ChromeDriver executable: https://sites.google.com/a/chromium.org/chromedriver/
            .usingDriverExecutable(new File("C://webdrivers/chromedriver.exe"))
            .usingAnyFreePort()
            .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }


    @Test
    public void startWebDriver(){

        driver.navigate().to("http://seleniumsimplified.com");

        Assert.assertTrue("title should start differently",
                            driver.getTitle().startsWith("Selenium Simplified"));

        driver.close();
        driver.quit();
    }
}
