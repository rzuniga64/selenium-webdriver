package webdriver.datadriven;

import webdriver.drivermanager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *  CsvDataDrivenTest class.
 *
 *  Data driven testing is not a functionality of the WebDriver but of the test runner. We are using JUnit. You can get
 *  similar functionality from TestNG or using a BDD framework.
 *
 *  We create a test class that is annotated with @RunWith(Parameterized.class). It’s important that we are using a
 *  slightly different JUnit runner at this point using the Parameterized.class runner.  The other thing that is
 *  different is that the class that we’re using must have a constructor because we construct the test class with the
 *  data that we are going to use.
 *
 *  What makes this important is that we have a method in the class which is annotated with @Parameters. That comes from
 *  the org.junit.runners.Parameterized.Parameters. This is where the data comes from. Then everything else is the same.
 *
 *  In the test use the values that have been set up in the constructor. Because what happens is JUnit when it
 *  encounters this test with the  @RunWith(Parameterized.class)runner it calls the parameterized method. It receives a
 *  collection of Object arrays. This is what JUnit uses with Reflection to pass into our class. Then JUnit iterates
 *  through this collection and for every entry in this collection instantiates the class with the arguments. Then it
 *  will run all the test methods in the class for that instantiation. So all the tests are run in the class every time
 *  it’s instantiated.
 *
 *  When the test is run you will see the test name with an array entry against it.  So you will have a single
 *  functional path but what is going to vary is the data scope.That’s the point at which we want to start doing data
 *  driven testing.
 *
 *  If you want to see an example using Excel then visit
 *  http://weblogs.java.net/blog/johnsmart/archive/2009/11/28/data-driven-tests-junit-4-and-excel
 */
@RunWith(Parameterized.class)
public class CsvDataDrivenTest {

    private final static String url = "http://www.compendiumdev.co.uk/selenium/calculate.php";
    private static WebDriver driver;
    private static WebDriverWait wait;

    private String number1;
    private String function;
    private String number2;
    private String answer;

    public CsvDataDrivenTest(
            String num1, String function, String num2, String answer)
    {
        this.number1 = num1;
        this.function = function;
        this.number2 = num2;
        this.answer = answer;
    }

    /**
     *  A collection of Object arrays that will be used in the constructor to initialize the class variables which will
     *  be used as inputs to the tests. Since the method is static JUnit can call it without instantiating this class.
     *  JUNit iterates through the Collection to instantiate the class each time. Then once the class is instantiated it
     *  calls  every test method on the class.
     *
     *  The only difference between reading from a file and from an array is that we call a method to return the
     *  collection of String arrays which make up the data. The actual method called, or class, can do whatever work it
     *  needs to provided it returns a collection that can be parsed into the constructor e.g. you could use POI to read
     *  an excel file or call a web service etc.
     */
    @Parameters
    public static Collection data() {

        return csvFileAsCollectionOfStringArrays(
                System.getProperty("user.dir") + "/src/test/resources/data_driven.csv");
    }

    private static Collection<String[]> csvFileAsCollectionOfStringArrays(String csvFileName) {

        List<String[]> csvRows = new ArrayList<>();
        String rawCSVRow;
        BufferedReader csvFileReader=null;
        String delimiter=",";

        System.out.println("Reading data from " + csvFileName);

        try {
            csvFileReader = new BufferedReader(new FileReader(csvFileName));

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + csvFileName);
            e.printStackTrace();
        }

        int rowNumber=1;
        try {

            assert csvFileReader != null;
            while ((rawCSVRow = csvFileReader.readLine())!=null) {
                String[] delimitedItems = rawCSVRow.split(delimiter);
                csvRows.add(delimitedItems);
                rowNumber++;
            }

        } catch (IOException e) {
            System.out.println("Error reading row number " + rowNumber);
            e.printStackTrace();
        }

        try {
            csvFileReader.close();
        } catch (IOException e) {
            System.out.println("Error closing file " + e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return csvRows;
    }

    /**
     * Everything after this is the same as the ObjectArrayDataDrivenTest
     */

    @BeforeClass
    static public void startServer() throws IOException {

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        driver.navigate().to(url);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test_calculate_two_values(){
        driver.get(url);

        WebElement number1 = driver.findElement(By.id("number1"));
        number1.sendKeys(this.number1);

        WebElement number2 = driver.findElement(By.id("number2"));
        number2.sendKeys(this.number2);

        WebElement functionList = driver.findElement(By.id("function"));
        functionList.findElement(
                        By.cssSelector(
                                "option[value='" + this.function + "']"))
                    .click();

        WebElement calculateButton = driver.findElement(By.id("calculate"));
        if(Driver.currentBrowser() == Driver.BrowserName.APPIUM) {
            calculateButton.submit();
        }else{
            calculateButton.click();
        }

        WebElement answer = new WebDriverWait(driver,10).
                                    until(ExpectedConditions.
                                            visibilityOfElementLocated(
                                                    By.id("answer")));

        assertThat(answer.getText(),is(equalTo(this.answer)));

    }
}
