package webdriver.pageobjects;

import webdriver.drivermanager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  WithoutPageObjectsTest class.
 *
 *  This has local abstractions instead of page objects.
 *  We want to refactor this to page objects.
 *  All these tests do pretty much the same thing so we will change when we refactor.
 */
public class WithoutPageObjectsTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        startBrowserAndSelectServer();

        // wait until the ajax symbol has gone because then the drop down has populated
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy")));
        selectJavaSubmitFormAndCheckResult();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionPresentExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value='23']")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionVisibleExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("option[value='23']")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is visible
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("option[value='23']")));
        // then select Java
        selectJavaSubmitFormAndCheckResult();
    }

    private void startBrowserAndSelectServer() {

        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();
    }

    private void selectJavaSubmitFormAndCheckResult() {

        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        driver.findElement(By.name("submitbutton")).click();
        wait.until(ExpectedConditions.titleIs("Processed Form Details"));

        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }
}
