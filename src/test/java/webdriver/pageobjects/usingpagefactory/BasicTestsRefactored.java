package webdriver.pageobjects.usingpagefactory;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.pageobjects.usingpagefactory.pages.BasicAjaxPageObject;
import webdriver.pageobjects.usingpagefactory.pages.ProcessedFormPage;

import static org.junit.Assert.assertEquals;
import static webdriver.pageobjects.usingpagefactory.pages.BasicAjaxPageObject.Category;
import static webdriver.pageobjects.usingpagefactory.pages.BasicAjaxPageObject.Language;

/**
 *  BasicTestsRefactored class.
 *
 *  Refactor to Page Objects
 *  - Convert local abstractions to Page Abstractions
 *  - Replace comments with abstractions
 *  - Make decisions about the model based on the test readability and method reuse
 *  - Build only what you need as you need it.
 */
public class BasicTestsRefactored {

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @Before
    public void setupTest(){

        //driver = Driver.get();
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

    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);

        basicAjaxPage.selectLanguage(Language.DESKTOP_Cpp);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.waitUntilPageIsLoaded();

        assertEquals("Expected Desktop CPP code", String.valueOf(Language.DESKTOP_Cpp.value()), processedForm.getValueFor("language_id"));
    }
}
