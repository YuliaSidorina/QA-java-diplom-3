import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import model.*;
import org.junit.*;
import org.junit.runner.RunWith;

import static browser.Browser.*;
import static com.codeborne.selenide.Selenide.*;
import static generator.UserGenerator.*;
import static io.restassured.RestAssured.given;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Тесты авторизации")
public class AuthorizationTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;
    PersonalAccountPage personalAccountPage;
    String userId;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
    }

    @After
    public void tearDown() {
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @After
    public void cleanUp() {
        if (userId != null) {
            LoginPage.deleteUser(userId);
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    public void signInBySignInButtonOnMainPage() {
        userId = LoginPage.createUser(WORKING_EMAIL, DEFAULT_PASSWORD, "Test User");
        mainPage.clickSignInButton();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void signInByPersonalAccountLink() {
        userId = LoginPage.createUser(WORKING_EMAIL, DEFAULT_PASSWORD, "Test User");
        mainPage.clickPersonalAccountLink();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void signInBySignInButtonOnRegistrationPage() {
        userId = LoginPage.createUser(WORKING_EMAIL, DEFAULT_PASSWORD, "Test User");
        mainPage.clickSignInButton();
        loginPage.clickRegisterLink();
        registrationPage.clickSignInLink();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void signInBySignInButtonOnPasswordRecoveryPage() {
        userId = LoginPage.createUser(WORKING_EMAIL, DEFAULT_PASSWORD, "Test User");
        mainPage.clickSignInButton();
        loginPage.clickPasswordRecoveryLink();
        passwordRecoveryPage.clickSignInLink();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void signOut() {
        userId = LoginPage.createUser(WORKING_EMAIL, DEFAULT_PASSWORD, "Test User");
        mainPage.clickSignInButton();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        mainPage.clickPersonalAccountLink();
        personalAccountPage.clickSignOutButton();
        loginPage.clickLogoLink();
        Assert.assertTrue(mainPage.checkIsSignInButtonEnabled());
    }
}
