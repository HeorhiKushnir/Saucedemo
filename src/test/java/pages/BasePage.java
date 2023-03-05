package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract class BasePage {

    WebDriver driver;
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final By TITLE = By.cssSelector(".title");

    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(TITLE).getText();
    }
}
