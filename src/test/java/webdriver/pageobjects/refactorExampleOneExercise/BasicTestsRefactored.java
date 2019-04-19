package webdriver.pageobjects.refactorExampleOneExercise;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;
import webdriver.pageobjects.refactorExampleOneExercise.pages.BasicAjaxPageObject;
import webdriver.pageobjects.refactorExampleOneExercise.pages.ProcessedFormPage;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static webdriver.pageobjects.refactorExampleOneExercise.pages.BasicAjaxPageObject.Category;
import static webdriver.pageobjects.refactorExampleOneExercise.pages.BasicAjaxPageObject.Language;

public class BasicTestsRefactored {

    private static WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @BeforeClass
    public static void setupTestClass() throws IOException {

        driver = Driver.get();

        // added when doing demo of the AndroidDriver
        //driver = new AndroidDriver("http://192.168.1.165:8080/wd/hub");
    }

    @Before
    public void setupTest() {

        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectLanguage(Language.JAVA);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.waitUntilPageIsLoaded();

        assertEquals("Expected Java code", Language.JAVA.value() + "", processedForm.getValueFor("language_id"));
    }

    /**
     *  Select the Web category and JavaScript language in the form and submit and assert language is correct.
     */
    @Test
    public void chooseToCodeInJavascriptOnTheWeb(){

        // workaround for the bug
        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectCategory(Category.WEB);

        basicAjaxPage.selectLanguage(Language.JAVASCRIPT);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.waitUntilPageIsLoaded();

        //TODO: this is a known bug, when the page is first created it has JavaScript 1, but server call is JavaScript 0
        assertEquals("Expected JavaScript code", String.valueOf(Language.JAVASCRIPT.value()), processedForm.getValueFor("language_id"));
    }

    /**
     *  Select the Desktop category and C++ language in the form and submit and assert language is correct.
     */
    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);
        basicAjaxPage.selectLanguage(Language.DESKTOP_Cpp);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.waitUntilPageIsLoaded();

        assertEquals("Expected Desktop CPP code", String.valueOf(Language.DESKTOP_Cpp.value()), processedForm.getValueFor("language_id"));
    }

    @AfterClass
    public static void tearDown(){
        // only if using android driver above
        //driver.quit();
    }
}
