package webdriver.drivermanager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static webdriver.drivermanager.EnvironmentPropertyReader.getPropertyOrEnv;

/**
 *  A singleton style drivermanager to maintain Drivers to prevent test slowdown for creating a browser for each class
 *  with tests. Also counts time to start a browser and extrapolates from that how much time you have saved using such
 *  hacky code.
 *
 *   When I started the course, the default browser was Firefox. It was supported without any extra drivers and worked
 *   well. At the point of writing this comment v 3.0.1 the legacyFirefoxDriver still works well, but only on v < 48 of
 *   Firefox. At the time of writing the current version of Firefox is 49.0.1
 *
 *   Since all browsers that most people will use now essentially need an 'external' driver. I am making Chrome the
 *   default.
 *
 *   The reason for this class is to limit the time it takes to start the browser. When the tests are run typically all
 *   the tests classes are run. This class allows sharing of the driver between the tests so minimize start up and
 *   shutodwn of the browser.
 *
 *   - 19th October 2016
 */
public class Driver extends Thread{

    public enum BrowserName{
        HTMLUNIT, FIREFOX, CHROME, GOOGLECHROME, EDGE, IE, FIREFOXPORTABLE, FIREFOXMARIONETTE, SAUCELABS, GRID, APPIUM,
        OPERA
    }

    static final String BROWSER_PROPERTY_NAME = "selenium2basics.webdriver";
    private static final String DEFAULT_BROWSER = "CHROME";
    public static final long DEFAULT_TIMEOUT_SECONDS = 10;

    private static long browserStartTime = 0L;
    private static long savedTimecount = 0L;
    private static boolean avoidRecursiveCall = false;
    private static ChromeDriverService service = null;
    private static WebDriver aDriver = null;
    private static BrowserName useThisDriver = null;

    public static BrowserName currentDriver;
    public static String RESOURCE_DIR = "";
    public static String BROWSER = DEFAULT_BROWSER;
    public static String PROXYHOST = "localhost";
    public static String PROXYPORT = "8888";
    public static String PROXY = PROXYHOST + ":" + PROXYPORT;

    public static void set(BrowserName aBrowser){

        useThisDriver = aBrowser;

        // close any existing driver
        if(aDriver != null){
            aDriver.quit();
            aDriver = null;
        }
    }

    /**
     *  get method.
     *  We can put this into continuous integration and call it from system property and we can control it within tests.
     *  Grow the abstraction layers on the needs that we have.
     *  Currently we don't have any capabilities. All the browsers are eventually going to start using capabilities as
     *  our default control mechanism. We can't support that in here. We will have to find a way of doing that.
     *
     *  @return a WebDriver
     */
    public static WebDriver get() throws IOException {

        // If running on Linux or Mac this will get the appropriate file separator in the string.
        String s = File.separator;
        String seleniumPropertiesFileName = System.getProperty("user.dir") +
                String.format("%ssrc%stest%sresources%sselenium.properties",s,s,s,s);
        File seleniumProperties = new File(seleniumPropertiesFileName);
        if (seleniumProperties.exists()) {
            try {
                InputStream input = new FileInputStream(seleniumPropertiesFileName);
                Properties properties = new Properties();
                // load a properties file
                properties.load(input);
                //get the properties values
                BROWSER = properties.getProperty("browser").toUpperCase();
                PROXYHOST = properties.getProperty("host");
                PROXYPORT = properties.getProperty("port");
                PROXY = PROXYHOST + ":" + PROXYPORT;

                System.setProperty(BROWSER_PROPERTY_NAME, BROWSER);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(useThisDriver == null){

            // to allow setting the browser as a property or an environment variable
            String defaultBrowser = getPropertyOrEnv(BROWSER_PROPERTY_NAME, BROWSER);

            // We could move this out so we don't have a switch statement.
            // Instead, we instantiate a browser supplier object and call it a single time.
            switch (defaultBrowser){
                case "HTMLUNIT":
                    useThisDriver = BrowserName.HTMLUNIT;
                    break;
                case "FIREFOX":
                    useThisDriver = BrowserName.FIREFOX;
                    break;
                case "CHROME":
                    useThisDriver = BrowserName.CHROME;
                    break;
                case "EDGE":
                    useThisDriver = BrowserName.EDGE;
                    break;
                case "IE":
                    useThisDriver = BrowserName.IE;
                    break;
                case "FIREFOXPORTABLE":
                    useThisDriver = BrowserName.FIREFOXPORTABLE;
                    break;
                case "SAUCELABS":
                    useThisDriver = BrowserName.SAUCELABS;
                    break;
                case "GRID":
                    useThisDriver = BrowserName.GRID;
                    break;
                case "APPIUM":
                    useThisDriver = BrowserName.APPIUM;
                    break;
                case "OPERA":
                    useThisDriver = BrowserName.OPERA;
                    break;
                default:
                    throw new RuntimeException("Unknown Browser in " + BROWSER_PROPERTY_NAME + ": " + defaultBrowser);
            }
        }

        if(aDriver==null){

            long startBrowserTime = System.currentTimeMillis();

            // see the \docs\firefox47.pdf for a discussion on why we have Firefox and FirefoxPortable etc.
            // various extensions to allow using of different types of browsers.
            switch (useThisDriver) {

                case HTMLUNIT:

                    // HtmlUnitDriver added as a maven dependency - no paths required
                    aDriver = new HtmlUnitDriver();  // enable javascript
                    currentDriver = BrowserName.HTMLUNIT;
                    break;

                case FIREFOX:

                    RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    System.setProperty("webdriver.gecko.driver", RESOURCE_DIR + "geckodriver.exe");
                    aDriver = new FirefoxDriver();
                    currentDriver = BrowserName.FIREFOX;
                    break;

                case CHROME:
                    RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    service = new ChromeDriverService.Builder()
                            .usingDriverExecutable(new File(RESOURCE_DIR + "chromedriver.exe"))
                            .usingAnyFreePort()
                            .build();
                    try {
                        service.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    aDriver = new RemoteWebDriver(service.getUrl(), new ChromeOptions());
                    currentDriver = BrowserName.CHROME;
                    break;

                case GOOGLECHROME:

                    setDriverPropertyIfNecessary("webdriver.chrome.driver",
                                                 "/../tools/chromedriver/chromedriver.exe",
                                                 "C://webdrivers/chromedriver/chromedriver.exe");

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("disable-plugins");
                    options.addArguments("disable-extensions");


                    // with Chrome v35 it now reports an error on --ignore-certificate-errors
                    // so call with args "test-type"
                    // https://code.google.com/p/chromedriver/issues/detail?id=799
                    options.addArguments("test-type");

                    DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

                    // http://stackoverflow.com/questions/26772793/org-openqa-selenium-unhandledalertexception-unexpected-alert-open
                    //chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

                    // convert theoptions to capabilities
                    chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

                    aDriver = new ChromeDriver(chromeCapabilities);
                    currentDriver = BrowserName.CHROME;
                    break;

                case EDGE:

                    RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    System.setProperty("webdriver.edge.driver", RESOURCE_DIR + "MicrosoftWebDriver.exe");

                    aDriver = new EdgeDriver();
                    currentDriver = BrowserName.EDGE;
                    break;

                case OPERA:

                    RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    System.setProperty("webdriver.opera.driver", RESOURCE_DIR + "operadriver.exe");

                    aDriver = new OperaDriver();
                    currentDriver = BrowserName.OPERA;
                    break;

                case IE:

                    String RESOURCE_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    System.setProperty("webdriver.ie.driver", RESOURCE_DIR + "IEDriverServer.exe");

                    aDriver = new InternetExplorerDriver();
                    currentDriver = BrowserName.IE;
                    break;

                case FIREFOXPORTABLE:

                    setDriverPropertyIfNecessary("seleniumsimplified.firefoxportable",
                                            "/../tools/FirefoxPortable/FirefoxPortable.exe",
                                                "C://webdrivers/FirefoxPortable/FirefoxPortable.exe");

                    //System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "false");

                    // for WebDriver 3 compatibility I need to set the FirefoxDriver to use the legacy driver rather
                    // than marionette
                    DesiredCapabilities portableCapabilities = DesiredCapabilities.firefox();
                    portableCapabilities.setCapability("marionette", false);
                    portableCapabilities.setCapability("firefox_binary",
                                new File(System.getProperty("seleniumsimplified.firefoxportable")).getAbsolutePath());

                    //aDriver = new FirefoxDriver(
                    //                new FirefoxBinary(
                    //                        new File(System.getProperty("seleniumsimplified.firefoxportable"))),
                    //                new FirefoxProfile(),
                    //                portableCapabilities);

                    aDriver = new FirefoxDriver(portableCapabilities);
                    currentDriver = BrowserName.FIREFOX;
                    break;

                case SAUCELABS:

                    DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
                    firefoxCapabilities.setCapability("version", "5");
                    firefoxCapabilities.setCapability("platform", Platform.XP);
                    try {
                        // add url to environment variables to avoid releasing with source
                        String sauceURL = System.getenv("SAUCELABS_URL");
                        aDriver = new RemoteWebDriver(
                                new URL(sauceURL),
                                firefoxCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    currentDriver = BrowserName.SAUCELABS;
                    break;

                case GRID:

                    String gridBrowser = getPropertyOrEnv("WEBDRIVER_GRID_BROWSER",
                                                               "firefox");
                    String gridBrowserVersion = getPropertyOrEnv("WEBDRIVER_GRID_BROWSER_VERSION",
                                                                      "");
                    String gridBrowserPlatform = getPropertyOrEnv("WEBDRIVER_GRID_BROWSER_PLATFORM",
                                                                       "");

                    DesiredCapabilities gridCapabilities = new DesiredCapabilities();
                    // The only capability that needs to be present is the browser name.
                    // Everything else has defaults in Grid.
                    gridCapabilities.setBrowserName(gridBrowser);
                    if(gridBrowserVersion.length() > 0)
                        gridCapabilities.setVersion(gridBrowserVersion);
                    if(gridBrowserPlatform.length() > 0)
                        gridCapabilities.setPlatform(Platform.fromString(gridBrowserPlatform));

                    // Allow adding any capability defined as an environment variable
                    // extra environment capabilities start with "WEBDRIVER_GRID_CAP_X_"
                    // e.g. WEBDRIVER_GRID_CAP_X_os_version XP
                    // e.g. WEBDRIVER_GRID_CAP_X_browserstack.debug true

                    Map<String, String> anyExtraCapabilities = System.getenv();
                    addAnyValidExtraCapabilityTo(gridCapabilities, anyExtraCapabilities.keySet());

                    // Now check properties for extra capabilities
                    Properties anyExtraCapabilityProperties = System.getProperties();
                    addAnyValidExtraCapabilityTo(gridCapabilities, anyExtraCapabilityProperties.stringPropertyNames());

                    try {
                        // add url to environment variables to avoid releasing with source
                        String gridURL = getPropertyOrEnv("WEBDRIVER_GRID_URL",
                                                               "http://localhost:4444/wd/hub");
                        aDriver = new RemoteWebDriver(new URL(gridURL), gridCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    currentDriver = BrowserName.GRID;
                    break;

                case APPIUM:

                    // quick hack code to get appium running
                    // only one env variable, your APPIUM_DEVICE_NAME
                    // so amend code for your local version

                    DesiredCapabilities appiumCapabilities = new DesiredCapabilities();

                    // the device name can be seen when you do "adb devices"
                    appiumCapabilities.setCapability("deviceName",
                            getPropertyOrEnv("APPIUM_DEVICE_NAME", ""));
                    appiumCapabilities.setCapability("platformName", Platform.ANDROID);
                    appiumCapabilities.setCapability("app",
                            getPropertyOrEnv("APPIUM_BROWSER", "browser"));

                    try {
                        // add url to environment variables to avoid releasing with source
                        String appiumURL = "http://127.0.0.1:4723/wd/hub";
                        aDriver = new RemoteWebDriver(
                                        new URL(appiumURL),
                                        appiumCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    currentDriver = BrowserName.APPIUM;
                    break;
            }

            long browserStartedTime = System.currentTimeMillis();
            browserStartTime = browserStartedTime - startBrowserTime;

            // Java has a thing called shut down hook that when the JVM stops that code will run. All that it does is
            // call the quit method in this particular class. We want to shutdown the shared browser when the tests
            // finish
            Runtime.getRuntime().addShutdownHook(
                    new Thread(Driver::quit)
            );

        } else {

            try{
                // is browser still alive
                if(aDriver.getWindowHandle()!=null){
                    // assume it is still alive
                }
            } catch(Exception e){
                if(avoidRecursiveCall){
                    throw new RuntimeException("something has gone wrong as we have been in Driver.get already");
                }

                quit();
                aDriver=null;
                avoidRecursiveCall = true;
                return get();
            }

            savedTimecount += browserStartTime;
            System.out.println("Saved another " + browserStartTime + "ms : total saved " + savedTimecount + "ms");
        }

        avoidRecursiveCall = false;
        return aDriver;
    }


    /**
     *  currentBrowser method.
     *
     *  Initially, the tests didn't really run on Grid, and I have code in the tests, to handle workarounds on different
     *  browsers.
     *
     *  But as I use grid more, my current approach of using currentDriver to code workarounds for specific browsers,
     *  doesn't work because I'll just get GRID when I want to know FIREFOX.
     *
     *  So I added a method called currentBrowser to Driver which returns the Browser in use.
     *
     *  I can still find out what currentDriver is with `Driver.currentDriver`. But if I want to know the browser, I
     *  should use `Driver.currentBrowser()`
     */
    public static BrowserName currentBrowser(){

        if(currentDriver == BrowserName.GRID){
            // Get the current browser from the property or environment. If not set then default to firefox.
            // Make lowercase for consistent comparison.
            String gridBrowser = getPropertyOrEnv("WEBDRIVER_GRID_BROWSER",
                                                       "firefox").toLowerCase();

            if(gridBrowser.contains("firefox")){
                return BrowserName.FIREFOX;
            }

            if(gridBrowser.contains("chrome")){
                return BrowserName.CHROME;
            }

            if(gridBrowser.contains("ie")){
                return BrowserName.IE;
            }

            if(gridBrowser.contains("html")){
                return BrowserName.HTMLUNIT;
            }
        }
        if(currentDriver == BrowserName.SAUCELABS){
            // we hard coded sauce to use firefox
            return BrowserName.FIREFOX;
        }

        return currentDriver;
    }

    /**
     * addAnyValidExtraCapabilityTo method.
     * @param gridCapabilities grid capabilities.
     * @param possibleCapabilityKeys capability keys.
     */
    private static void addAnyValidExtraCapabilityTo(DesiredCapabilities gridCapabilities,
                                                     Set<String> possibleCapabilityKeys) {

        String extraCapabilityPrefix = "WEBDRIVER_GRID_CAP_X_";

        for(String capabilityName : possibleCapabilityKeys){

            if(capabilityName.startsWith(extraCapabilityPrefix)){

                String capabilityValue = getPropertyOrEnv(capabilityName, "");

                if(capabilityValue.length() > 0){
                    String capability = capabilityName.replaceFirst(extraCapabilityPrefix,"");
                    System.out.println("To Set Capability " + capability + " with value " + capabilityValue);
                    gridCapabilities.setCapability(capability, capabilityValue);
                }
            }
        }
    }

    /**
     *  setDriverPropertyIfNecessary method.
     *  @param propertyKey System property key.
     *  @param relativeToUserPath relative path to driver.
     *  @param absolutePath absolute path to driver.
     *
     *  http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
     */
    private static void setDriverPropertyIfNecessary(String propertyKey, String relativeToUserPath, String absolutePath) {

        if(!System.getProperties().containsKey(propertyKey)){

            String currentDir = System.getProperty("user.dir");
            String chromeDriverLocation = currentDir + relativeToUserPath;
            File driverExe = new File(chromeDriverLocation);
            if(driverExe.exists()){
                System.setProperty(propertyKey, chromeDriverLocation);
            } else {
                driverExe = new File(absolutePath);
                if(driverExe.exists()){
                    System.setProperty(propertyKey, absolutePath);
                } else {
                    // expect an error on the follow through when we try to use the driver
                    // unless the user has it in their Path in which case WebDriver will use that
                }
            }
        }
    }

    /**
     *  get method.
     *  @param aURL the URL to navigate to.
     *  @param maximize true if you want to maximize the window; false otherwise.
     *  @return a WebDriver.
     */
    public static WebDriver get(String aURL, boolean maximize){

        //get();
        aDriver.get(aURL);
        if(maximize){
            try{
                aDriver.manage().window().maximize();
            } catch(UnsupportedCommandException e){
                System.out.println("Remote Driver does not support maximize");
            } catch(WebDriverException e){
                if(currentDriver == BrowserName.APPIUM){
                    System.out.println("Appium does not support maximize");
                }else{
                    System.out.println("Unexpected exception on trying to maximize");
                    System.out.println(e.getMessage());
                }
            }
        }
        return aDriver;
    }

    /**
     *  trying to maximise is currently failing for me on Windows 10 with ChromeDriver 2.24 and Chrome 61.0.3163.91
     *  ?... 61.0.3163.100
     *  set maximise default to false instead of true to run with this config
     *  works fine with 2.32 and Chrome Chrome 61.0.3163.91+
     *  - TODO - add this default as a configuration variable
     */
    public static WebDriver get(String aURL){
        return get(aURL,true);
    }

    /**
     *  If we have a driver, then quit it.
     */
    public static void quit(){
        if(aDriver != null){
            System.out.println("total time saved by reusing browsers " + savedTimecount + "ms");
            try{
                aDriver.quit();
                aDriver=null;
            }catch(Exception e){
                // I don't care about errors at this point
            }

        }
        if(service != null){
            try{
                service.stop();
                service = null;
            }catch(Exception e){
                // I don't care about errors at this point
            }
        }
    }
}
