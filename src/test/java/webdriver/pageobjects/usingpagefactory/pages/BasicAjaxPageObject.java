package webdriver.pageobjects.usingpagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  BasicAjaxPageObject class.
 *  Page Object class for "http://compendiumdev.co.uk/selenium/basic_ajax.html".
 *
 *  In the Java version of WebDriver we have some helper classes that we can use to code the Page Factory.
 *  Instead of doing findElement within the code. We define WebElements as fields in our Page Object and annotate them
 *  with @FindBy and explain how we can find the WebElement. In the constructor use a page factory to initialize the
 *  WebElements for this driver on this Page Object: PageFactory(initElements(driver.this).
 *
 *  What this does it looks through the object and finds any fields that are annotated with @FindBy then puts a proxy in
 *  place so that later on when I do a language select it will lazily load this WebElement. So I don't have to do a
 *  findElement anymore because I annotated the WebElement. This makes the test class a little bit neater. If the
 *  WebElement is not loaded then a NoSuchElementException will be thrown by the proxy object.
 */
public class BasicAjaxPageObject extends LoadableComponent<BasicAjaxPageObject> {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.ID, using="combo1")
    private WebElement categorySelect;

    @FindBy(how = How.ID, using="combo2")
    private WebElement languageSelect;

    @FindBy(how= How.NAME, using="submitbutton")
    private WebElement submitButton;

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

    /**
     *  Constructor
     *  @param webDriver the WebDriver for the browser under test.
     */
    public BasicAjaxPageObject(WebDriver webDriver) {

        driver = webDriver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    /**
     *  Navigate to the URL.
     */
    @Override
    protected void load() {

        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    /**
     *  Is category selected loaded.
     * @throws Error error.
     */
    @Override
    protected void isLoaded() throws Error {

        try{
            categorySelect.isDisplayed();
        } catch(NoSuchElementException e) {
            throw new Error("basic_ajax page not loaded");
        }
    }

    /**
     *  Select category from dropdown.
     *  @param category the category.
     */
    public void selectCategory(Category category) {

        categorySelect.findElement(By.cssSelector("option[value='" + category.value() + "']")).click();
        wait.until(ajaxActionIsComplete());
    }

    /**
     *  Wait until the ajax symbol has gone because then the drop down has populated.
     *  @return an ExpectedCondition.
     */
    private ExpectedCondition<Boolean> ajaxActionIsComplete() {

        return ExpectedConditions.invisibilityOfElementLocated(
                By.id("ajaxBusy"));
    }

    /**
     *  Select the programming language.
     *  @param language the programming language.
     */
    public void selectLanguage(Language language) {
        languageSelect.findElement(By.cssSelector("option[value='" + language.value() + "']")).click();
    }

    public void clickSubmitButton() {

        submitButton.click();
    }

}
