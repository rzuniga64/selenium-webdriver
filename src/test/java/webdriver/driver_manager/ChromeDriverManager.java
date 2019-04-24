package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private static ChromeDriverService service = null;

    @Override
    protected WebDriver createDriver() {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-plugins");
        options.addArguments("disable-extensions");
        options.addArguments("test-type");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new RemoteWebDriver(service.getUrl(), capabilities);
    }

    @Override
    protected void startService() {

        RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";

        if (service == null) {
            try {
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(RESOURCE_DIR + "chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                service.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {

        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
