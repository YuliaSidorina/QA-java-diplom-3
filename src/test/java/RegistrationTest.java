import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import api.Api;
import model.Authorization;
import model.User;
import pages.Login;
import pages.HomePage;
import pages.Registration;
import utils.ChooseDriver;
import utils.Generator;

public class RegistrationTest {

    @Rule
    public ChooseDriver chooseDriver = new ChooseDriver();
    private Api api;
    private User user;
    private HomePage homePage;
    private Login login;
    private Registration registration;

    @Before
    public void setUp() {
        WebDriver webDriver = chooseDriver.getWebDriver();

        homePage = new HomePage(webDriver);
        login = new Login(webDriver);
        registration = new Registration(webDriver);

        api = new Api();
        user = Generator.createRandomUser();
    }

    @Test
    @DisplayName("Проверка успешноц регистрации")
    public void testSuccessfulRegistration() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterAccountButton();

        login.clickRegisterLink();
        registration.waitForLoad();
        registration.enterNewAccountData(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        registration.clickRegistrationButton();
        login.waitForLoad();
        login.enterLoginData(user.getEmail(), user.getPassword());
        login.clickEnterButton();
        homePage.waitForLoad();

        Assert.assertTrue("Successful registration failed", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации с невалидным паролем")
    public void testRegistrationWithInvalidPassword() {
        api.createUser(user);
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterAccountButton();

        login.clickRegisterLink();
        registration.waitForLoad();
        int maxInvalidPasswordLength = 5;
        registration.enterNewAccountData(
                user.getName(),
                user.getEmail(),
                Generator.randomPassword(maxInvalidPasswordLength)
        );
        registration.clickRegistrationButton();
        Assert.assertTrue("There is no invalid password error",
                registration.isIncorrectPasswordVisible());
    }

    @After
    public void tearDown() {
        Authorization authorization = new Authorization(
                user.getEmail(),
                user.getPassword());
        String accessToken = api.loginUser(authorization);
        api.deleteUser(accessToken);
    }
}