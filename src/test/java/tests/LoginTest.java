package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Products", "Пользователь не залогинился");
    }

    @Test
    public void userNameIsRequired() {
        loginPage.open();
        loginPage.login("", "");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Username is required", "Wrong error message");
    }

    @Test
    public void passwordIsRequired() {
        loginPage.open();
        loginPage.login("standard_user", "");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Password is required", "Wrong error message");
    }

    @Test
    public void wrongUsernameOrPassword() {
        loginPage.open();
        loginPage.login("123", "123");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Username and password do not match any user in this service",
                "Wrong error message");
    }

    @Test
    public void lockedUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Sorry, this user has been locked out.", "Wrong error message");
    }
}
