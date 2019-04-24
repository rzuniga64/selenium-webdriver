package webdriver.driver_manager;

import org.openqa.selenium.WebDriver;

/**
 *  A abstract driver manager to maintain Drivers. Since all browsers that most people will use now essentially need an
 *  'external' driver. I am making Chrome the default.
 *
 *  The reason for this class is to limit the time it takes to start the browser. When the tests are run typically all
 *  the tests classes are run. This class allows sharing of the driver between the tests so minimize start up and
 *  shutdown of the browser.
 *
 *   - 19th October 2016
 */
public abstract class DriverManager extends Thread {

    public static final long DEFAULT_TIMEOUT_SECONDS = 10;

    public static String RESOURCE_DIR = "";

    protected static long browserStartTime = 0L;
    protected static long savedTimecount = 0L;
    protected static WebDriver driver = null;

    protected abstract WebDriver createDriver();
    protected abstract void startService();
    protected abstract void stopService();

    /**
     *  getDriver method.
     *  We can put this into continuous integration and call it from system property and we can control it within tests.
     *  Grow the abstraction layers on the needs that we have.
     *  Currently we don't have any capabilities. All the browsers are eventually going to start using capabilities as
     *  our default control mechanism. We can't support that in here. We will have to find a way of doing that.
     *
     *  @return a WebDriver
     */
    public WebDriver getDriver() {

        if (driver == null) {
            startService();
            driver = createDriver();
        }

        return driver;
    }

    /**
     *  If there is a driver, quit it.
     */
    public static void quitDriver() {

        // Java has a thing called shut down hook that when the JVM stops that code will run. All that it does is call
        // the quit method in this particular class. We want to shutdown the shared browser when the tests finish.
        Runtime.getRuntime().addShutdownHook(
                new Thread(DriverManager::quit)
        );
    }

    public static void quit() {

        if (driver != null) {
            try {
                driver.quit();
                driver = null;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
