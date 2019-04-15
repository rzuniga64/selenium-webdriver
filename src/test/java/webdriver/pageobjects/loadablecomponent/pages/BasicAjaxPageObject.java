package webdriver.pageobjects.loadablecomponent.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  BasicAjaxPageObject class
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
public class BasicAjaxPageObject extends LoadableComponent<BasicAjaxPageObject> {

    WebDriver driver;
    private WebDriverWait wait;

    public enum Category{

        WEB(1), DESKTOP(2), SERVER(3);

        private int dropDownValue;

        Category(int value){
            this.dropDownValue = value;
        }

        public int value(){
            return dropDownValue;
        }
    }

    public enum Language {

        JAVASCRIPT(0), VBSCRIPT(1), FLASH(2),
        COBOL(20), FORTRAN(21), SERVER_Cpp(22), JAVA(23),
        DESKTOP_Cpp(10), ASSEMBLER(11), C(12), VISUAL_BASIC(13);

        private int dropDownValue;

        Language(int value){
            this.dropDownValue = value;
        }

        public int value(){
            return dropDownValue;
        }
    }

    public BasicAjaxPageObject(WebDriver webDriver) {

        driver = webDriver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     *  Navigate to the URL.
     */
    @Override
    protected void load()
    {
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    /**
     *  Is category selected loaded.
     *  @throws Error error.
     */
    @Override
    protected void isLoaded() throws Error {
        try{
            driver.findElement(By.id("combo1"));
        } catch(NoSuchElementException e){
            throw new Error("basic_ajax page not loaded");
        }
    }

    /**
     *  Select category from dropdown.
     *  @param category the category.
     */
    public void selectCategory(Category category) {

        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='" + category.value() + "']")).click();
        new WebDriverWait(driver,10).until(ajaxActionIsComplete());
    }

    /**
     *  Wait until the ajax symbol has gone because then the drop down has populated.
     *  @return an ExpectedCondition.
     */
    public ExpectedCondition<Boolean> ajaxActionIsComplete() {

        return ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy"));
    }

    /**
     *  Select the programming language.
     *  @param language the programming language.
     */
    public void selectLanguage(Language language) {

        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='" + language.value() + "']")).click();
    }

    /**
     *  Click the Submit button
     */
    public void clickSubmitButton() {

        driver.findElement(By.name("submitbutton")).click();
    }
}
