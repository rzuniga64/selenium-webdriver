package webdriver.pageobjects.refactorExampleOne.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  Page Object class for http://compendiumdev.co.uk/selenium/basic_ajax.html.
 */
public class BasicAjaxPageObject {

    private static WebDriver driver;
    private static WebDriverWait wait;

    /**
     *  Enum for the category.
     */
    public enum Category {

        WEB(1), DESKTOP(2), SERVER(3);

        private int dropDownValue;

        Category(int value) {

            this.dropDownValue = value;
        }

        public int value(){

            return dropDownValue;
        }
    }

    /**
     *  Constructor
     *  @param newDriver the WebDriver for the browser under test.
     */
    public BasicAjaxPageObject(WebDriver newDriver) {

        driver = newDriver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     *  Navigate to the URL.
     */
    public void get() {

        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    /**
     *  Select category from dropdown.
     *  @param category the category.
     */
    public void selectCategory(Category category) {

        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='" + category.value() + "']")).click();

        // Wait until the option I want to click is present. We could also wait for the contents of the drop down to fill.
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value='23']")));
        wait.until(ajaxActionIsComplete());
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
     *  @param languageValue the programming language.
     */
    public void selectLanguage(int languageValue) {

        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='" + languageValue + "']")).click();
    }

    /**
     *  Click the Submit button on the form.
     */
    public void clickSubmitButton() {

        driver.findElement(By.name("submitbutton")).click();
    }
}
