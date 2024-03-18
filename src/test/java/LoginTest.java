import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import api.Api;
import model.User;
import pages.ForgotPassword;
import pages.Login;
import pages.HomePage;
import pages.Registration;
import utils.ChooseDriver;
import utils.Generator;

public class LoginTest {

    @Rule
    public ChooseDriver chooseDriver = new ChooseDriver();
    private Api api;
    private User user;
    private Login login;
    private HomePage homePage;
    private Registration registration;
    private ForgotPassword forgotPassword;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriver webDriver = chooseDriver.getWebDriver();
        api = new Api();
        user = Generator.createRandomUser();
        login = new Login(webDriver);
        homePage = new HomePage(webDriver);
        registration = new Registration(webDriver);
        forgotPassword = new ForgotPassword(webDriver);
        accessToken = api.createUser(user);
    }

    @Test
    @DisplayName("Проверка регистрации через аккаунт")
    public void testLoginByEnterAccountButton() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterAccountButton();
        login.waitForLoad();
        login.enterLoginData(user.getEmail(), user.getPassword());
        login.clickEnterButton();
        homePage.waitForLoad();

        Assert.assertTrue("Login by account button failed", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации через лк")
    public void testLoginByProfileLink() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterProfileLink();
        login.waitForLoad();
        login.enterLoginData(user.getEmail(), user.getPassword());
        login.clickEnterButton();
        homePage.waitForLoad();

        Assert.assertTrue("Login by profile button failed", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации")
    public void testLoginOnRegistrationPage() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterAccountButton();
        login.waitForLoad();
        login.clickRegisterLink();
        registration.waitForLoad();
        registration.clickLogin();
        login.waitForLoad();
        login.enterLoginData(user.getEmail(), user.getPassword());
        login.clickEnterButton();
        homePage.waitForLoad();

        Assert.assertTrue("Login throw register form failed", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации через Восстановить пароль")
    public void testLoginOnPasswordRecoveryPage() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterAccountButton();
        login.waitForLoad();
        login.clickForgotPasswordLink();
        forgotPassword.waitForLoad();
        forgotPassword.clickEnterButton();
        login.waitForLoad();
        login.enterLoginData(user.getEmail(), user.getPassword());
        login.clickEnterButton();
        homePage.waitForLoad();

        Assert.assertTrue("Login throw forgot password page failed", homePage.isOrderButtonVisible());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
    }
}