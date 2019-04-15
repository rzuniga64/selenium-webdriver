package webdriver.pageobjects.slowloading;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

/**
 *  IsLoaded class.
 *
 *  To clean it up use a IsLoaded Object for this. It’s a Helper Object.  This is really taking an example from the
 *  WebDriver ExpectedConditions class. That is a support Object which has static methods that return ExpectedConditions
 *  for WebDriverWait. It is a little bit fluent with this form of this method that takes a driver.
 *
 *  You can keep chaining methods on the IsLoaded Object that are in this IsLoaded class.  These methods are pretty much
 *  the same code as we had before but just wrapped up in a nice neat package. The methods are fluent in that they
 *  return ‘this’ if it passed.  So I can chain them. They throw an error if they fail which is captured by the
 *  SlowLoadableComponent.
 */
public class IsLoaded {

    private final WebDriver driver;

    public IsLoaded(WebDriver driver) {

        this.driver = driver;
    }

    public static IsLoaded forThis(WebDriver driver) {

        IsLoaded isLoaded = new IsLoaded(driver);
        return isLoaded;
    }

    public IsLoaded whenElementIsVisible(By usingBy, String description) {

        try {
            if(driver.findElement(usingBy).isDisplayed()){
                return this;
            } else {
                throw new Error(description + " is not visible");
            }
        } catch(WebDriverException e){
            throw new Error(description + " is not visible", e);
        }
    }

    public IsLoaded whenElementIsEnabled(By usingBy, String description) {

        try{
            if(driver.findElement(usingBy).isEnabled()){
                return this;
            } else {
                throw new Error(description + " is not enabled");
            }
        } catch(WebDriverException e){
            throw new Error(description + " is not enabled", e);
        }
    }
}
