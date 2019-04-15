package webdriver.pageobjects.loadablecomponent.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcessedFormPage extends LoadableComponent<ProcessedFormPage>{

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     *  Constructor
     *  @param aDriver the WebDriver for the browser under test.
     */
    public ProcessedFormPage(WebDriver aDriver) {

        driver = aDriver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     *  Wait until process form page is loaded.
     */
    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    /**
     *  Get the value of a WebElement give the id on the process form page.
     *  @param valueID the id of the WebElement.
     *  @return the value of the WebElement.
     */
    public String getValueFor(String valueID) {
        WebElement fieldValueElement = driver.findElement(By.id("_value" + valueID));
        return fieldValueElement.getText();
    }

    @Override
    protected void load() {
        // We will never load a processed form, it will always be a result of another action
    }

    /**
     *  Check if page is loaded.
     *  @throws Error error.
     */
    @Override
    protected void isLoaded() throws Error {
        if(!driver.getTitle().contentEquals("Processed Form Details")){
            throw new Error("Title was not 'Processed Form Details' it was " + driver.getTitle());
            //or
            //fail("Title was not 'Processed Form Details' it was " + driver.getTitle());
        }
    }
}
