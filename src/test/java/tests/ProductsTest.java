package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest{

    @Test(description = "Items sorted by name from A to Z")
    public void sortByNameFromAToZ() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(productsPage.productSort());
        select.selectByVisibleText("Name (A to Z)");
        List<String> itemNames = productsPage.listOfItems();
        List<String> sortedItemNames = new ArrayList<>(productsPage.listOfItems());
        Collections.sort(sortedItemNames);
        assertEquals(itemNames, sortedItemNames, "Wrong sort");
    }

    @Test(description = "Items sorted by name from Z to A")
    public void sortByNameFromZToA() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(productsPage.productSort());
        select.selectByVisibleText("Name (Z to A)");
        List<String> itemNames = productsPage.listOfItems();
        List<String> sortedItemNames = new ArrayList<>(productsPage.listOfItems());
        Collections.sort(sortedItemNames, Collections.reverseOrder());
        assertEquals(itemNames, sortedItemNames, "Wrong sort");
    }

    @Test(description = "Items sorted by price from low to high")
    public void sortByPriceFromLowToHigh() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(productsPage.productSort());
        select.selectByVisibleText("Price (low to high)");
        List<Double> priceValues = productsPage.listOfPrices();
        List<Double> sortedPrices = new ArrayList<>(productsPage.listOfPrices());
        Collections.sort(sortedPrices);
        assertEquals(priceValues, sortedPrices, "Wrong sort");
    }

    @Test(description = "Items sorted by price from high to low")
    public void sortByPriceFromHighToLow() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        select.selectByVisibleText("Price (high to low)");
        List<Double> priceValues = productsPage.listOfPrices();
        List<Double> sortedPrices = new ArrayList<>(productsPage.listOfPrices());
        sortedPrices.sort(Collections.reverseOrder());
        assertEquals(priceValues, sortedPrices, "Wrong sort");
    }

    @Test(description = "Product added to cart")
    public void addedProductToCart() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        String counterOfCart = productsPage.cartBadge();
        assertEquals(counterOfCart,"1", "Item not added");
    }
}
