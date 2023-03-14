package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest{

    @Test(description = "Items sorted by name from A to Z", retryAnalyzer = Retry.class)
    public void sortByNameFromAToZ(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        select.selectByVisibleText("Name (A to Z)");
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.getText());
        }
        List<String> sortedItemNames = new ArrayList<>(itemNames);
        Collections.sort(sortedItemNames);
        assertEquals(itemNames, sortedItemNames, "Wrong sort");
    }

    @Test(description = "Items sorted by name from Z to A", retryAnalyzer = Retry.class)
    public void sortByNameFromZToA(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        select.selectByVisibleText("Name (Z to A)");
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.getText());
        }
        List<String> sortedItemNames = new ArrayList<>(itemNames);
        Collections.sort(sortedItemNames, Collections.reverseOrder());
        assertEquals(itemNames, sortedItemNames, "Wrong sort");
    }

    @Test(description = "Items sorted by price from low to high", retryAnalyzer = Retry.class)
    public void sortByPriceFromLowToHigh(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        select.selectByVisibleText("Price (low to high)");
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> priceValues = new ArrayList<>();
        for (WebElement price : prices) {
            String priceString = price.getText().substring(1);
            priceValues.add(Double.parseDouble(priceString));
        }
        List<Double> sortedPrices = new ArrayList<>(priceValues);
        Collections.sort(sortedPrices);
        assertEquals(priceValues, sortedPrices, "Wrong sort");
    }

    @Test(description = "Items sorted by price from high to low", retryAnalyzer = Retry.class)
    public void sortByPriceFromHighToLow(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        Select select = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        select.selectByVisibleText("Price (high to low)");
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> priceValues = new ArrayList<>();
        for (WebElement price : prices) {
            String priceString = price.getText().substring(1);
            priceValues.add(Double.parseDouble(priceString));
        }
        List<Double> sortedPrices = new ArrayList<>(priceValues);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        assertEquals(priceValues, sortedPrices, "Wrong sort");
    }

    @Test(description = "Product added to cart", retryAnalyzer = Retry.class)
    public void addedProductToCart(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        String counterOfCart = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        assertEquals(counterOfCart,"1", "Item not added");
    }
}
