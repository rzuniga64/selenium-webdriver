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

public class BasicAjaxPageObject extends LoadableComponent<BasicAjaxPageObject> {

    WebDriver driver;
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

    public static enum Language {

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
    public ExpectedCondition<Boolean> ajaxActionIsComplete() {

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
