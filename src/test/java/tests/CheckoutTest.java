package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest{


    @Test(description = "Succesful checkout", retryAnalyzer = Retry.class)
    public void succesfulCheckout() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("firstName","lastName","zipCode");
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Checkout: Overview", "Wrong error message");
    }

    @DataProvider(name = "Data for checkout")
    public Object[][] getDataForCheckout() {
        return new Object[][]{
                {"", "", "", "Error: First Name is required"},
                {"test", "", "", "Error: Last Name is required"},
                {"test", "test", "", "Error: Postal Code is required"},
        };
    }

    @Test(description = "Negative checkout", dataProvider = "Data for checkout", retryAnalyzer = Retry.class)
    public void negativeTestCheckout(String firstName, String lastName, String zipCode, String error) {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase(firstName,lastName,zipCode);
        String errorMessage = checkoutPage.getErrorMessage();
        assertEquals(errorMessage, error, "Wrong error message");
    }

    @Test(description = "Equals total value with sum of items price", retryAnalyzer = Retry.class)
    public void equalsTotalPrice() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("firstName","lastName","zipCode");
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        double totalPrice = 0;
        for (WebElement price : prices) {
            totalPrice += Double.parseDouble(price.getText().substring(1));
        }
        WebElement summaryTotal = driver.findElement(By.cssSelector(".summary_subtotal_label"));
        double summaryTotalPrice = Double.parseDouble(summaryTotal.getText().substring(13));
        assertEquals(totalPrice, summaryTotalPrice, "Wrong sum of price");
    }

    @Test(description = "Check that cancel button move to cart from checkout page", retryAnalyzer = Retry.class)
    public void cancelButton() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.cancelCheckout();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Your Cart", "Wrong error message");
    }
}
