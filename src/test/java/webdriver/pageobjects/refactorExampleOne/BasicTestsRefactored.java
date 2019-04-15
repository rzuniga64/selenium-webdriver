package webdriver.pageobjects.refactorExampleOne;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.drivermanager.Driver;
import webdriver.pageobjects.refactorExampleOne.pages.BasicAjaxPageObject;
import webdriver.pageobjects.refactorExampleOne.pages.ProcessedFormPage;

import static org.junit.Assert.assertEquals;
import static webdriver.pageobjects.refactorExampleOne.pages.BasicAjaxPageObject.Category;

public class BasicTestsRefactored {

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @Before
    public void setup(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    /**
     *  Select the Server category and Java language in the form and submit and assert language is correct.
     */
    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectLanguage(23);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Java code", "23",processedFormPage.getValueFor("language_id"));
    }

    /**
     *  Select the Web category and JavaScript language in the form and submit and assert language is correct.
     */
    @Test
    public void chooseToCodeInJavascriptOnTheWeb(){

        basicAjaxPage.selectCategory(Category.WEB);
        basicAjaxPage.selectLanguage(0);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Javascript code", "0", processedFormPage.getValueFor("language_id"));
    }

    /**
     *  Select the Desktop category and C++ language in the form and submit and assert language is correct.
     */
    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);
        basicAjaxPage.selectLanguage(10);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Cpp code", "10",processedFormPage.getValueFor("language_id"));
    }
}
