package webdriver.driver_manager;

/**
 *  EnvironmentPropertyReader class.
 *
 *  We want to make our test configuration as flexible as we can.
 *
 *  PROPERTY VARIABLES
 *  We can also use property files to define variables. We can define variables as System properties. System properties
 *  are local to the JVM that is running and can set those in code. We can set them via them command line using
 *  -D, mv test -DskipTests=true. It's also convenient to use in continuous integration.
 *
 *  ENVIRONMENT VARIABLES
 *  Sometimes we want the configuration to span our entire environment so we use the environment variables. They are
 *  system based and can be slow to amend because we have to restart the IDE or command line to pick up changes
 *
 *  DEFAULT VARIABLES
 *  Default variables are good for in code debugging and experiments.
 *
 *  This class will prioritize how System properties, environment variables and defaults are used:
 *  1. Properties
 *  2. Environment variables
 *  3. Defaults.
 */
public class EnvironmentPropertyReader {

    private static DriverType currentBrowser;

    /**
     *  Allow setting the controls via property or environment variable.
     *  Property takes precedence, then environment variable, then default.
     */
    public static DriverType getPropertyOrEnv(String browserPropertyName, String defaultBrowser){

        // Check for System property first.
        String browserName = System.getProperty(browserPropertyName).toUpperCase();
        if(browserName.isEmpty() || browserName == null){

            // If System property is not set check environment variables
            browserName = System.getenv(browserPropertyName);

            if(browserName == null){

                System.out.println("No Environment Variable or Property named [" + browserPropertyName + "] using default value [" + defaultBrowser + "]");
                // If System property or environment variable is not set use the default that is passed in.
                browserName = defaultBrowser;

            } else {
                System.out.println("Using Environment Variable " + browserPropertyName + " with value " + browserName);
            }
        } else {
            System.out.println("Using Property " + browserPropertyName + " with value " + browserName);
        }

        switch (browserName){
            case "HTMLUNIT":
                currentBrowser = DriverType.HTMLUNIT;
                break;
            case "FIREFOX":
                currentBrowser = DriverType.FIREFOX;
                break;
            case "CHROME":
                currentBrowser = DriverType.CHROME;
                break;
            case "EDGE":
                currentBrowser = DriverType.EDGE;
                break;
            case "IE":
                currentBrowser = DriverType.IE;
                break;
            case "FIREFOXPORTABLE":
                currentBrowser = DriverType.FIREFOXPORTABLE;
                break;
            case "SAUCELABS":
                currentBrowser = DriverType.SAUCELABS;
                break;
            case "GRID":
                currentBrowser = DriverType.GRID;
                break;
            case "APPIUM":
                currentBrowser = DriverType.APPIUM;
                break;
            case "OPERA":
                currentBrowser = DriverType.OPERA;
                break;
            default:
                throw new RuntimeException("Unknown Browser in " + browserPropertyName + ": " + defaultBrowser);
        }

        return currentBrowser;
    }
}
