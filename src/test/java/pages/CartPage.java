package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage{

    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    public static final By INVENTORY_ITEM = By.cssSelector(".inventory_item_name");

    String removeItemXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//button";

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }
    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void removeItem(String item) {
        By removeItemLocator = By.xpath(String.format(removeItemXpath, item));
        driver.findElement(removeItemLocator).click();
    }

    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public List<WebElement> inventoryItems() {
        return  driver.findElements(INVENTORY_ITEM);
    }
}
