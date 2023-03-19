package tests;

import net.bytebuddy.utility.nullability.NeverNull;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PurchaseTest extends BaseTest {

    @Test(description = "Succesful purchase")
    public void successfulPurchase() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("Heorhi", "Kushnir", "12345");
        checkoutPage.finishButton();
        String succesfulMessage = checkoutPage.getTitle();
        assertEquals(succesfulMessage, "Checkout: Complete!", "Purchase not completed");
    }

    @Test(description = "Button back home move to products page")
    public void backHomeButton() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("Heorhi", "Kushnir", "12345");
        checkoutPage.finishButton();
        checkoutPage.backToProductsButton();
        String title = checkoutPage.getTitle();
        assertEquals(title, "Products", "Wrong message");
    }
}
