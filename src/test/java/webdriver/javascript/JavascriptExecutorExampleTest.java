package webdriver.javascript;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JavascriptExecutorExampleTest {

    private static WebDriver driver;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
    }

    @Test
    public void callAJavaScriptFunctionOnThePage(){

        // Cast driver to JavaScriptExecutor to access the JavaScript methods.
        JavascriptExecutor js =(JavascriptExecutor)driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals("By default app has 2 actions listed", 2, actionsCount);

        // Execute the 'draw' function on the page.
        js.executeScript("draw(1, 150, 150,40, '#FF1C0A');");

        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals("Calling draw should add an action", 3, actionsCount);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}