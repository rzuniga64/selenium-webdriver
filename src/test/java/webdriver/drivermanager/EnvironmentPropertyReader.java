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

        String theValue = System.getProperty(browserPropertyName);
        if(theValue == null){

            // this message just confuses people
            //System.out.println("Could not find Property " + name);
            theValue = System.getenv(browserPropertyName);

            if(theValue==null){

                System.out.println("No Environment Variable or Property named [" + browserPropertyName + "] using default value [" + browser + "]");
                theValue = browser;

            }else{
                System.out.println("Using Environment Variable " + browserPropertyName + " with value " + theValue);
            }
        }else{
            System.out.println("Using Property " + browserPropertyName + " with value " + theValue);
        }

        return theValue;
    }
}
