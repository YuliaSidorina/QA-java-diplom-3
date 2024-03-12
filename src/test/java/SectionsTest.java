import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import model.ConstructorPage;
import model.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;

import static browser.Browser.browserChoice;
import static browser.Browser.closeNotChromeBrowser;
import static com.codeborne.selenide.Selenide.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Тестирование вкладок конструктора")
public class SectionsTest {

    MainPage mainPage;
    ConstructorPage constructorPage;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        constructorPage = page(ConstructorPage.class);
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
