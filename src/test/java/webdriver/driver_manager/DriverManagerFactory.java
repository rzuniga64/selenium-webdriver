package webdriver.driver_manager;

import java.io.*;
import java.util.Properties;

import static webdriver.driver_manager.EnvironmentPropertyReader.getPropertyOrEnv;

public class DriverManagerFactory {

    private static final String BROWSER_PROPERTY_NAME = "selenium2basics.webdriver";
    private static final String DEFAULT_BROWSER = "CHROME";

    private static String BROWSER = DEFAULT_BROWSER;
    private static String PROXYHOST = "localhost";
    private static String PROXYPORT = "8888";
    private static String PROXY = PROXYHOST + ":" + PROXYPORT;

    public static DriverManager getManager() throws IOException {

        DriverManager driverManager = null;

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

        if (driverManager == null) {

            // to allow setting the browser as a property or an environment variable
            DriverType defaultBrowser = getPropertyOrEnv(BROWSER_PROPERTY_NAME, BROWSER);

            switch (defaultBrowser) {
                case CHROME:
                    driverManager = new ChromeDriverManager();
                    break;
                case FIREFOX:
                    driverManager = new FireFoxDriverManager();
                    break;
                case HTMLUNIT:
                    driverManager = new HtmlUnitDriverManager();
                    break;
                case EDGE:
                    driverManager = new EdgeDriverManager();
                    break;
                case IE:
                    driverManager =  new IEDriverManager();
                    break;
                case OPERA:
                    driverManager = new OperaDriverManager();
                    break;
                case FIREFOXPORTABLE:
                    driverManager = new FirefoxPortableDriverManager();
                    break;
                case SAUCELABS:
                    break;
                case GRID:
                    break;
                case APPIUM:
                    break;
                default:
                    throw new RuntimeException("Unknown Browser in " + BROWSER_PROPERTY_NAME + ":" + defaultBrowser);
            }
        }

        return driverManager;
    }
}
