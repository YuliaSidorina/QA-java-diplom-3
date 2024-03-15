import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import model.LoginPage;
import model.MainPage;
import model.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static browser.Browser.browserChoice;
import static browser.Browser.closeNotChromeBrowser;
import static generator.UserGenerator.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Тесты регистрации пользователя")
public class RegisterUserTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String newEmail;
    String userId;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSignInButton();

        loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        registrationPage = page(RegistrationPage.class);
        newEmail = getNewRandomEmail();
    }

    @After
    public void tearDown() {
        clearBrowserLocalStorage();
        if (userId != null) {
            LoginPage.deleteUser(userId);
        }
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerUserSuccessfully() {
        userId = LoginPage.createUser(newEmail, DEFAULT_PASSWORD, DEFAULT_NAME);
        registrationPage.register(DEFAULT_NAME, newEmail, DEFAULT_PASSWORD);
        loginPage.login(newEmail, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Регистрация со слишком коротким паролем")
    public void registerUserWithShortPassword() {
        userId = LoginPage.createUser(newEmail, DEFAULT_PASSWORD, DEFAULT_NAME);
        registrationPage.register(DEFAULT_NAME, newEmail, SHORT_PASSWORD);
        Assert.assertTrue(registrationPage.checkIsIncorrectPasswordTextVisible());
    }
}
