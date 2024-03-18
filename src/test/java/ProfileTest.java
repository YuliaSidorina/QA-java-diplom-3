import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import api.Api;
import model.User;
import pages.Login;
import pages.HomePage;
import pages.Profile;
import utils.ChooseDriver;
import utils.Generator;

public class ProfileTest {

    @Rule
    public ChooseDriver chooseDriver = new ChooseDriver();
    private Login login;
    private HomePage homePage;
    private Profile profile;
    private Api api;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriver webDriver = chooseDriver.getWebDriver();
        login = new Login(webDriver);
        homePage = new HomePage(webDriver);
        profile = new Profile(webDriver);
        api = new Api();
        user = Generator.createRandomUser();
        accessToken = api.createUser(user);
    }

    @Test
    @DisplayName("Проверка кнопки - лк")
    public void testProfileButton() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterProfileLink();
        login.waitForLoad();

        Assert.assertTrue("Profile link is not working", login.isEnterHeaderVisible());
    }

    @Test
    @DisplayName("")
    public void testLogoButton() throws InterruptedException {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterProfileLink();
        login.waitForLoad();
        Thread.sleep(10000);
        login.clickLogoButton();
        homePage.waitForLoad();
        Assert.assertTrue("Logo button is not working", homePage.isCreateOrderHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки - Конструктор")
    public void testConstructorButton() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterProfileLink();
        login.waitForLoad();

        login.clickConstructorButton();
        homePage.waitForLoad();
        Assert.assertTrue("Constructor button is not working", homePage.isCreateOrderHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки - Выход")
    public void testLogoutButton() {
        homePage.open();
        homePage.waitForLoad();
        homePage.clickEnterAccountButton();
        login.waitForLoad();
        login.enterLoginData(user.getEmail(), user.getPassword());
        login.clickEnterButton();
        homePage.waitForLoad();
        homePage.isCreateOrderHeaderVisible();
        homePage.clickEnterProfileLink();
        profile.waitForLoad();
        profile.clickLogoutButton();

        login.waitForLoad();
        Assert.assertTrue("Logout button is not working", login.isEnterHeaderVisible());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
    }
}