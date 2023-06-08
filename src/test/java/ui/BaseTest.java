package ui;

import api.User;
import api.UserClient;
import api.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ui.page.LoginPage;
import ui.page.MainPage;

import java.time.Duration;

import static ui.WebDriverCreator.createWebDriver;


public abstract class BaseTest {
    WebDriver driver;
    String accessToken;
    MainPage mainPage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        User user = UserGenerator.randomUser();
        UserClient.create(user);
        ValidatableResponse loginResponse = UserClient.login(user);
        accessToken = loginResponse.extract().path("accessToken");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        mainPage = loginPage.submitLogin();
    }

    @After
    public void tearDown() {
        UserClient.delete(accessToken);
        driver.close();
    }
}
