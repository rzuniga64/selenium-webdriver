package webdriver.pageobjects.slowloadablecomponent;

import webdriver.drivermanager.Driver;
import webdriver.pageobjects.slowloadablecomponent.pages.BasicAjaxPageObject;
import webdriver.pageobjects.slowloadablecomponent.pages.ProcessedFormPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static webdriver.pageobjects.slowloadablecomponent.pages.BasicAjaxPageObject.Category;
import static webdriver.pageobjects.slowloadablecomponent.pages.BasicAjaxPageObject.Language;
import static org.junit.Assert.assertEquals;

/**
 *  BasicTestsRefactored class
 *
 *  LoadableComponent
 *  - Extend to create page objects
 *    - Extends LoadableComponent<PageObject>
 *      - E.g. LoadableComponent<BasicAjaxPageObject>
 *    - Implement
 *      - Load
 *        - Do the work to load the page
 *      - IsLoaded
 *        - Throw an error if component/page is not loaded
 *      - Receive ‘get’ for free
 */
public class BasicTestsRefactored {

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @Before
    public void setupTest() throws IOException {

        driver = Driver.get();
        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectLanguage(Language.JAVA);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.get();

        assertEquals("Expected Java code",
                     Language.JAVA.value() + "", processedForm.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInJavascriptOnTheWeb(){

        // workaround for the bug
        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectCategory(Category.WEB);

        basicAjaxPage.selectLanguage(Language.JAVASCRIPT);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.get();

        //TODO: this is a known bug, when the page is first created it has JavaScript 1, but server call is JavaScript 0
        assertEquals("Expected JavaScript code",
                String.valueOf(Language.JAVASCRIPT.value()),
                processedForm.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);

        basicAjaxPage.selectLanguage(Language.DESKTOP_Cpp);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.get();

        assertEquals("Expected Desktop CPP code",
                String.valueOf(Language.DESKTOP_Cpp.value()),
                processedForm.getValueFor("language_id"));
    }

}
