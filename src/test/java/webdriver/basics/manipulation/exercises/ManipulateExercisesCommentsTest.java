package webdriver.basics.manipulation.exercises;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.drivermanager.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *  ManipulateExercisesCommentsTest class.
 *  Clear, then type comments, submit form and check output.
 */
public class ManipulateExercisesCommentsTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    /**
     *  submitFormDefaultComments method.
     *  Submit the default comments in the comments section of a form.
     */
    @Test
    public void submitFormDefaultComments(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        WebElement commentsResults = driver.findElement(By.id("_valuecomments"));
        assertEquals("Expected default comments", "Comments...", commentsResults.getText());
    }

    /**
     *  submitFormWithMyComments method.
     *  Clear, then type comments, submit form and check output.
     */
    @Test
    public void submitFormWithMyComments(){

        WebElement submitButton;
        WebElement commentTextArea;
        WebElement commentsResults;
        String myCommentString = "This is my comment";

        commentTextArea = driver.findElement(By.name("comments"));
        commentTextArea.clear();
        commentTextArea.sendKeys(myCommentString);

        submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        commentsResults = driver.findElement(By.id("_valuecomments"));
        assertEquals("Expected default comments", myCommentString, commentsResults.getText());
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}
