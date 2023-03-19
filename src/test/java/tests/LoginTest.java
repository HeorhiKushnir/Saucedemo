package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(description = "Check if user can login")
    public void successfulLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String title = loginPage.getTitleProducts();
        assertEquals(title, "Products", "Пользователь не залогинился");
    }

    @DataProvider(name = "Входящие данные для негативных тестов на логин")
    public Object[][] getDataForLogin() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"123", "123", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
        };
    }

    @Test(description = "Negative login", dataProvider = "Входящие данные для негативных тестов на логин")
    public void negativeTestLogin(String username, String password, String expectedError) {
        loginPage.open();
        loginPage.login(username, password);
        String error = loginPage.getErrorMessage();
        assertEquals(error, expectedError, "Wrong error message");
    }
}
