import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.ChooseDriver;

public class HomePageTest {

    @Rule
    public ChooseDriver chooseDriver = new ChooseDriver();
    private HomePage homePage;

    @Before
    public void setUp() {
        WebDriver webDriver = chooseDriver.getWebDriver();
        homePage = new HomePage(webDriver);
    }

    @Test
    @DisplayName("Проверка кнопки - соусы")
    public void testSauceSectionButton() {
        homePage.open();
        homePage.waitForLoad();

        homePage.clickSauceButton();
        Assert.assertTrue("Sauce selection button is not working", homePage.isSauceSectionSelected());
    }

    @Test
    @DisplayName("Проверка кнопки - булки")
    public void testBunSectionButton() {
        homePage.open();
        homePage.waitForLoad();

        homePage.clickSauceButton();
        homePage.clickBunButton();
        Assert.assertTrue("Bun selection button is not working", homePage.isBunSectionSelected());
    }

    @Test
    @DisplayName("Проверка кнопки - начинки")
    public void testFillingSectionButton() {
        homePage.open();
        homePage.waitForLoad();

        homePage.clickFillingButton();
        Assert.assertTrue("Filling selection button is not working", homePage.isFillingSectionSelected());
    }
}