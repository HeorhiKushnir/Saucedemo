package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends BasePage{

    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By ITEM_PRICE = By.className("inventory_item_price");
    public static final By SUMMARY_PRICE = By.cssSelector(".summary_subtotal_label");
    public static final By BACK_TO_PRODUCTS_BUTTON = By.id("back-to-products");
    public static final By CANCEL_BUTTON = By.id("cancel");
    public static final By FIRST_NAME = By.id("first-name");
    public static final By LAST_NAME = By.id("last-name");
    public static final By POSTAL_CODE = By.id("postal-code");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    public static final By FINISH_BUTTON = By.id("finish");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutContinuePurchase(String name, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(name);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }
    public void cancelCheckout() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void finishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void backToProductsButton() {
        driver.findElement(BACK_TO_PRODUCTS_BUTTON).click();
    }

    public double totalPrice() {
        List<WebElement> prices = driver.findElements(ITEM_PRICE);
        double totalPrice = 0;
        for (WebElement price : prices) {
            totalPrice += Double.parseDouble(price.getText().substring(1));
        }
        return totalPrice;
    }

    public double summaryTotalPrice() {
        WebElement summaryTotal = driver.findElement(SUMMARY_PRICE);
        return Double.parseDouble(summaryTotal.getText().substring(13));
    }
}
