import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import model.LoginPage;
import model.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static browser.Browser.browserChoice;
import static browser.Browser.closeNotChromeBrowser;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Тесты переходов на страницы")
public class GoToPageTest {

    MainPage mainPage;
    LoginPage loginPage;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        loginPage = page(LoginPage.class);
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        Assert.assertEquals(WebDriverRunner.url(), LoginPage.LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToConstructorFromPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickConstructorLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromPersonalAccountByLogo() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickLogoLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
    }
}
