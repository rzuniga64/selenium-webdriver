package webdriver.driver_manager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import static webdriver.drivermanager.EnvironmentPropertyReader.getPropertyOrEnv;

/**
 *  Factory class which returns a Grid driver.
 */
public class GridDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {

        WebDriver driver = null;

        String gridBrowser = getPropertyOrEnv(  "WEBDRIVER_GRID_BROWSER", "firefox");
        String gridBrowserVersion = getPropertyOrEnv("WEBDRIVER_GRID_BROWSER_VERSION", "");
        String gridBrowserPlatform = getPropertyOrEnv("WEBDRIVER_GRID_BROWSER_PLATFORM", "");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // The only capability that needs to be present is the browser name. Everything else has defaults in Grid.
        capabilities.setBrowserName(gridBrowser);
        if(gridBrowserVersion.length() > 0)
            capabilities.setVersion(gridBrowserVersion);
        if(gridBrowserPlatform.length() > 0)
            capabilities.setPlatform(Platform.fromString(gridBrowserPlatform));

        // Allow adding any capability defined as an environment variable
        // extra environment capabilities start with "WEBDRIVER_GRID_CAP_X_"
        // e.g. WEBDRIVER_GRID_CAP_X_os_version XP
        // e.g. WEBDRIVER_GRID_CAP_X_browserstack.debug true

        Map<String, String> anyExtraCapabilities = System.getenv();
        addAnyValidExtraCapabilityTo(capabilities, anyExtraCapabilities.keySet());

        try {
            // add url to environment variables to avoid releasing with source
            String gridURL = getPropertyOrEnv("WEBDRIVER_GRID_URL",
                                                   "http://localhost:4444/wd/hub");
            driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    @Override
    protected void startService() {}

    @Override
    protected void stopService() {}

    /**
     *  addAnyValidExtraCapabilityTo method.
     *  @param capabilities grid capabilities.
     *  @param possibleCapabilityKeys capability keys.
     */
    private static void addAnyValidExtraCapabilityTo(DesiredCapabilities capabilities,
                                                     Set<String> possibleCapabilityKeys) {

        String extraCapabilityPrefix = "WEBDRIVER_GRID_CAP_X_";

        for(String capabilityName : possibleCapabilityKeys){

            if(capabilityName.startsWith(extraCapabilityPrefix)){

                String capabilityValue = getPropertyOrEnv(capabilityName, "");

                if(capabilityValue.length() > 0){
                    String capability = capabilityName.replaceFirst(extraCapabilityPrefix,"");
                    System.out.println("To Set Capability " + capability + " with value " + capabilityValue);
                    capabilities.setCapability(capability, capabilityValue);
                }
            }
        }
    }
}
