import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PurchaseTest extends BaseTest {

    @Test
    public void successfulPurchase() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector("[data-test=checkout]")).click();
        driver.findElement(By.cssSelector("[data-test=firstName]")).sendKeys("Heorhi");
        driver.findElement(By.cssSelector("[data-test=lastName]")).sendKeys("Kushnir");
        driver.findElement(By.cssSelector("[data-test=postalCode]")).sendKeys("123456");
        driver.findElement(By.cssSelector("[data-test=continue]")).click();
        driver.findElement(By.cssSelector("[data-test=finish]")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Checkout: Complete!", "Error message");
    }
}
