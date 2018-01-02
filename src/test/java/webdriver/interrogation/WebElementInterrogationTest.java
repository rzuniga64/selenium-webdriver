package webdriver.interrogation;

import webdriver.drivermanager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WebElementInterrogationTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        driver = Driver.get("selenium2basics.webdriver","HTMLUNIT" );
    }

    @Test
    public void WebElementInterrogationBasics(){

        final String theTestPageURL = "http://www.compendiumdev.co.uk/selenium/basic_web_page.html";

        driver.navigate().to(theTestPageURL);
        WebElement para1 = driver.findElement(By.id("para1"));
        assertThat(para1.getText(), is("A paragraph of text"));
    }
}
