package webdriver.synchronization.implicitWait;

import webdriver.drivermanager.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  ImplicitWaitTest class.
 *
 *  Implicit wait
 *  - .findElement has an implicit wait
 *  - Default is 0
 *  - Can amend the global default
 *    driver.manage.timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 *
 *  Implicit or Explicit?
 *  - Implicity can make initial tests faster to write
 *    - You don’t have to worry about synchronization when writing tests
 *  - It can be harder to add synchronization later
 *    - You have to identify a source of intermittency
 *  - If you start with implicit then you can expose synchronization problems by gradually reducing the implicit wait time.
 */
public class ImplicitWaitTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setup() throws IOException {

        driver = Driver.get();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        wait = new WebDriverWait(driver,10);
    }

    /**
     *  implicitWaitSynchronisationExample method.
     *
     *  - Implicit wait can make initial tests faster to write.
     *    - You don’t have to worry about synchronization when writing tests
     *  - It can be harder to add synchronization later
     *    - You have to identify a source of intermittency
     *  - If you start with implicit then you can expose synchronization problems by gradually reducing the implicit
     *    wait time.
     */
    @Test
    public void implicitWaitSynchronisationExample(){

        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");

        // This is really not a good way to synchronize.
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        driver.findElement(By.name("submitbutton")).click();

        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }

    /**
     *  implicitWaitOfZeroTheMissingElementsAreFastToFind method.
     *  Use implicit wait of 0 s. This is not a good synchronization strategy.
     */
    @Test
    public void implicitWaitOfZeroTheMissingElementsAreFastToFind(){

        fillFormAndSubmit();

        wait.until(ExpectedConditions.titleIs("Processed Form Details"));

        // wait for 0 seconds implicitly
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebElement checkbox1Result;
        WebElement checkbox3Result = null;

        long findTimeStart = 0L;
        long findTimeEnd = 0L;

        findTimeStart = System.currentTimeMillis();
        checkbox1Result = driver.findElement(By.id("_valuecheckboxes0"));
        findTimeEnd = System.currentTimeMillis();

        long totalTimeToFindElement = findTimeEnd - findTimeStart;
        System.out.println("Time to find an element " + totalTimeToFindElement + " ms");

        long nofindTimeStart=0L;
        long nofindTimeend=0L;

        try{
            nofindTimeStart = System.currentTimeMillis();
            checkbox3Result = driver.findElement(By.id("_valuecheckboxes2"));

        } catch (NoSuchElementException e){
            // expected missing element
            nofindTimeend = System.currentTimeMillis();
        }

        long totalTimeToNotFindElement = nofindTimeend - nofindTimeStart;

        assertTrue("expected element not found", checkbox3Result==null);
        System.out.println("Time to not find an element " + totalTimeToNotFindElement + " ms");

        // time to find is variable so this check is not always true
        // assertTrue("expected faster time to not find element", totalTimeToNotFindElement < totalTimeToFindElement);
    }

    /**
     *  implicitWaitOfTenTheMissingElementsTakeAtLeastThatToNotFind methd.
     *  Use implicit wait of 10 s. It will take at least that long to not find an element making tests longer.
     */
    @Test
    public void implicitWaitOfTenTheMissingElementsTakeAtLeastThatToNotFind(){

        fillFormAndSubmit();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        // wait for 10 seconds implicitly
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement checkbox1Result=null;
        WebElement checkbox3Result=null;

        long findTimeStart = 0L;
        long findTimeEnd = 0L;

        findTimeStart = System.currentTimeMillis();
        checkbox1Result = driver.findElement(By.id("_valuecheckboxes0"));
        findTimeEnd = System.currentTimeMillis();

        long totalTimeToFindElement = findTimeEnd - findTimeStart;

        System.out.println("Time to find an element " + totalTimeToFindElement + " ms");

        long nofindTimeStart=0L;
        long nofindTimeend=0L;

        try{
            nofindTimeStart = System.currentTimeMillis();
            checkbox3Result = driver.findElement(By.id("_valuecheckboxes2"));

        }catch(NoSuchElementException e){
            // expected missing element
            nofindTimeend = System.currentTimeMillis();
        }

        long totalTimeToNotFindElement = nofindTimeend - nofindTimeStart;

        System.out.println("Time to not find an element " + totalTimeToNotFindElement + " ms");

        // wait for 0 seconds implicitly by default
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        assertTrue("expected slower time to not find element",
                totalTimeToNotFindElement > totalTimeToFindElement);
        assertTrue("implicit wait time is included in time to not find",
                totalTimeToNotFindElement > 10000L);

    }


    private void fillFormAndSubmit() {

        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        WebElement checkBox3 = driver.findElement(By.cssSelector("input[value='cb3']"));

        if(!checkBox1.isSelected())checkBox1.click();
        if(checkBox3.isSelected()) checkBox3.click();

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }

    @After
    public void resetImplicitWait(){

        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
    }
}
