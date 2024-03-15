import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import model.ConstructorPage;
import model.LoginPage;
import model.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import static browser.Browser.browserChoice;
import static browser.Browser.closeNotChromeBrowser;
import static com.codeborne.selenide.Selenide.*;
import static generator.UserGenerator.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Тестирование вкладок конструктора")
public class SectionsTest {

    MainPage mainPage;
    ConstructorPage constructorPage;
    String newEmail;
    String userId;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        constructorPage = page(ConstructorPage.class);
        newEmail = getNewRandomEmail();
        userId = LoginPage.createUser(newEmail, DEFAULT_PASSWORD, DEFAULT_NAME);
    }

    @After
    public void tearDown(){
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
    @DisplayName("Проверка выбора вкладки 'Булки'")
    public void bunTabValidWorking() {
        Assert.assertTrue(constructorPage.checkIsBunTabSelected());
    }

    @Test
    @DisplayName("Проверка выбора вкладки 'Соусы'")
    public void sauceTabValidWorking() {
        constructorPage.clickSauceTab();
        Assert.assertTrue(constructorPage.checkIsSauceTabSelected());
    }

    @Test
    @DisplayName("Проверка выбора вкладки 'Начинки'")
    public void fillingTabValidWorking() {
        constructorPage.clickFillingTab();
        Assert.assertTrue(constructorPage.checkIsFillingTabSelected());
    }
}
