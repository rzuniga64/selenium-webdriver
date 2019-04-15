package webdriver.pageobjects.refactorExampleOneExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ProcessedFormPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     *  Construction
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

        wait.until(titleIs("Processed Form Details"));
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
}
