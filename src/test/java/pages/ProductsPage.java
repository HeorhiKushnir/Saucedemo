package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{

    String addToCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'inventory_item')]//button";
    public static final By CART = By.id("shopping_cart_container");
    public static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");
    public static final By PRODUCT_SORT = By.cssSelector(".product_sort_container");
    public static final By ITEM_PRICE = By.className("inventory_item_price");
    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    @Step("Add item to cart")
    public void addToCart(String product) {
        By addToCartLocator = By.xpath(String.format(addToCartXpath, product));
        driver.findElement(addToCartLocator).click();
    }
    @Step("Opening cart")
    public void openCart() {
        driver.findElement(CART).click();
    }
    @Step("Choice of sorting items")
    public WebElement productSort() {
        return driver.findElement(PRODUCT_SORT);
    }
    @Step("Getting a list of products")
    public List<String> listOfItems() {
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.getText());
        }
        return itemNames;
    }
    @Step("Getting a list of prices")
    public List<Double> listOfPrices() {
        List<WebElement> prices = driver.findElements(ITEM_PRICE);
        List<Double> priceValues = new ArrayList<>();
        for (WebElement price : prices) {
            String priceString = price.getText().substring(1);
            priceValues.add(Double.parseDouble(priceString));
        }
        return priceValues;
    }
    @Step("Getting information about the cart icon")
    public String cartBadge() {
        return driver.findElement(CART_BADGE).getText();
    }
}
