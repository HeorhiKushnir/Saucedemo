package pages;

import io.qameta.allure.Step;
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
    @Step("Open cart page by URL")
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }
    @Step("Go to the payment page")
    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
    @Step("Removing item from the cart")
    public void removeItem(String item) {
        By removeItemLocator = By.xpath(String.format(removeItemXpath, item));
        driver.findElement(removeItemLocator).click();
    }
    @Step("Back to products")
    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
    @Step("Getting items list")
    public List<WebElement> inventoryItems() {
        return  driver.findElements(INVENTORY_ITEM);
    }
}
