package pages;

import constants.Path;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Page {

    private final By createBurgerHeader = By.xpath("//h1[text() = 'Соберите бургер']");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath("//button[text() = 'Оформить заказ']");
    private final By enterAccountButton = By.xpath("//button[text() = 'Войти в аккаунт']");
    private final By sauceButton = By.xpath("//span[text() = 'Соусы']");
    private final By bunButton = By.xpath("//span[text() = 'Булки']");
    private final By fillingButton = By.xpath("//span[text() = 'Начинки']");
    private final By currentSection = By.className("tab_tab_type_current__2BEPc");
    private final By childSpanNode = By.tagName("span");

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента - Соберите бургер")
    public void waitForLoad() {
        waitForVisibility(createBurgerHeader);
    }

    @Step("Открыть страницу")
    public void open() {
        webDriver.get(Path.BASE_URL);
    }

    @Step("Нажать кнопку - Войти в аккаунт")
    public void clickEnterAccountButton() {
        webDriver.findElement(enterAccountButton).click();
    }

    @Step("Нажать кнопку - Личный Кабинет")
    public void clickEnterProfileLink() {
        webDriver.findElement(profileButton).click();
    }

    @Step("Проверить видимость элемента - Оформить заказ")
    public boolean isOrderButtonVisible() {
        return webDriver.findElement(orderButton).isDisplayed();
    }

    @Step("Проверить видимость элемента - Соберите бургер")
    public boolean isCreateOrderHeaderVisible() {
        return webDriver.findElement(createBurgerHeader).isDisplayed();
    }

    @Step("Нажать кнопку - Булки")
    public void clickBunButton() {
        webDriver.findElement(bunButton).click();
    }

    @Step("Нажать кнопку - Начинки")
    public void clickFillingButton() {
        webDriver.findElement(fillingButton).click();
    }

    @Step("Нажать кнопку - Соусы")
    public void clickSauceButton() {
        webDriver.findElement(sauceButton).click();
    }

    @Step("Проверить выбранную секцию - Соусы")
    public boolean isSauceSectionSelected() {
        return isCorrectSectionSelected(sauceButton);
    }

    @Step("Проверить выбранную секцию - Булки")
    public boolean isBunSectionSelected() {
        return isCorrectSectionSelected(bunButton);
    }

    @Step("Проверить выбранную секцию - Начинки")
    public boolean isFillingSectionSelected() {
        return isCorrectSectionSelected(fillingButton);
    }

    private boolean isCorrectSectionSelected(By currentButtonLocator) {
        WebElement currentSectionElement = webDriver.findElement(this.currentSection);
        WebElement currentButton = webDriver.findElement(currentButtonLocator);
        return currentButton.getText().equals(currentSectionElement.findElement(childSpanNode).getText());
    }
}
