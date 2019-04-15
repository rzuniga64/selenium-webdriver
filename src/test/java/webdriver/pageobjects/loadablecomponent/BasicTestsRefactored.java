package webdriver.pageobjects.loadablecomponent;

import webdriver.drivermanager.Driver;
import webdriver.pageobjects.loadablecomponent.pages.BasicAjaxPageObject;
import webdriver.pageobjects.loadablecomponent.pages.ProcessedFormPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static webdriver.pageobjects.loadablecomponent.pages.BasicAjaxPageObject.Category;
import static webdriver.pageobjects.loadablecomponent.pages.BasicAjaxPageObject.Language;
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
    public void setupTest(){

        driver = Driver.get("webdriver.chrome.driver", "CHROME");
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

        assertEquals("Expected Java code",
                     Language.JAVA.value() + "", processedForm.getValueFor("language_id"));
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
        assertEquals("Expected JavaScript code",
                String.valueOf(Language.JAVASCRIPT.value()),
                processedForm.getValueFor("language_id"));
    }

    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);

        basicAjaxPage.selectLanguage(Language.DESKTOP_Cpp);
        basicAjaxPage.clickSubmitButton();

        ProcessedFormPage processedForm = new ProcessedFormPage(driver);
        processedForm.waitUntilPageIsLoaded();

        assertEquals("Expected Desktop CPP code",
                String.valueOf(Language.DESKTOP_Cpp.value()),
                processedForm.getValueFor("language_id"));
    }
}
