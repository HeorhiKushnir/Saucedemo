package tests;

import net.bytebuddy.utility.nullability.NeverNull;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PurchaseTest extends BaseTest {

    @Test
    public void successfulPurchase() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinuePurchase("Heorhi", "Kushnir", "12345");
        driver.findElement(By.id("finish")).click();
        String succesfulMessage = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(succesfulMessage, "Checkout: Complete!", "Purchase not completed");
    }
}
