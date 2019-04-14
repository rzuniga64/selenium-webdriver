package webdriver.drivermanager;

/**
 * Created by Alan on 25/07/2016.
 */
public class EnvironmentPropertyReader {

    /**
     *  Allow setting the controls via property or environment variable
     *  property takes precedence, then environment variable, then default
     */
    public static String getPropertyOrEnv(String browserPropertyName, String browser){

        String browserName = System.getProperty(browserPropertyName).toUpperCase();
        if(browserName == null){

            browserName = System.getenv(browserPropertyName);

            if(browserName==null){

                System.out.println("No Environment Variable or Property named [" + browserPropertyName + "] using default value [" + browser + "]");
                browserName = browser;

            } else {
                System.out.println("Using Environment Variable " + browserPropertyName + " with value " + browserName);
            }
        } else {
            System.out.println("Using Property " + browserPropertyName + " with value " + browserName);
        }

        return browserName;
    }
}
