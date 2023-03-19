package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
public class CartTest extends BaseTest{

    @Test(description = "Make sure the added items are equal with cart")
    public void theAddedProductsAreCorrect() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        List<String> addedItems = new ArrayList<>();
        addedItems.add("Sauce Labs Backpack");
        addedItems.add("Sauce Labs Bike Light");
        addedItems.add("Sauce Labs Bolt T-Shirt");
        for (String item : addedItems) {
            productsPage.addToCart(item);
        }
        productsPage.openCart();
        List<WebElement> items = cartPage.inventoryItems();
        List<String> itemNamesInCart = new ArrayList<>();
        for (WebElement item : items) {
            itemNamesInCart.add(item.getText());
        }
        assertEquals(addedItems, itemNamesInCart, "Items not equal with cart");
    }

    @Test(description = "Check that item was removed")
    public void removeItemFromCart() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        cartPage.removeItem("Sauce Labs Bike Light");
        int itemCount = cartPage.inventoryItems().size();
        assertEquals(itemCount, 2, "The product has not been removed from the cart");
    }

    @Test(description = "Check that we back to products page")
    public void backToProductsButton() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.continueShopping();
        String title = cartPage.getTitle();
        assertEquals(title, "Products", "Wrong message");
    }
}
