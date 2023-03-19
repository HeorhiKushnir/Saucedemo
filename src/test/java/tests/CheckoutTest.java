package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest{


    @Test(description = "Succesful checkout")
    public void succesfulCheckout() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("firstName","lastName","zipCode");
        String title = checkoutPage.getTitle();
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

    @Test(description = "Negative checkout", dataProvider = "Data for checkout")
    public void negativeTestCheckout(String firstName, String lastName, String zipCode, String error) {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase(firstName,lastName,zipCode);
        String errorMessage = checkoutPage.getErrorMessage();
        assertEquals(errorMessage, error, "Wrong error message");
    }

    @Test(description = "Equals total value with sum of items price")
    public void equalsTotalPrice() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("firstName","lastName","zipCode");
        double totalPrice = checkoutPage.totalPrice();
        double summaryTotalPrice = checkoutPage.summaryTotalPrice();
        assertEquals(totalPrice, summaryTotalPrice, "Wrong sum of price");
    }

    @Test(description = "Check that cancel button move to cart from checkout page")
    public void cancelButton() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.cancelCheckout();
        String title = checkoutPage.getTitle();
        assertEquals(title, "Your Cart", "Wrong error message");
    }
}
