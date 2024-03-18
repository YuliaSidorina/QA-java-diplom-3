package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration extends Page {

    private final By registerLabel = By.xpath("//h2[text()='Регистрация']");
    private final By nameInput = By.xpath("//label[text()='Имя']/../input");
    private final By emailInput = By.xpath("//label[text()='Email']/../input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/../input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordLabel = By.xpath("//p[text() = 'Некорректный пароль']");
    private final By enterButton = By.xpath("//a[text() = 'Войти']");

    public Registration(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента - Регистрация")
    public void waitForLoad() {
        waitForVisibility(registerLabel);
    }

    @Step("Создание нового аккаунта")
    public void enterNewAccountData(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Клик по элементу - Зарегистрироваться")
    public void clickRegistrationButton() {
        webDriver.findElement(registerButton).click();
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод имени")
    public void enterName(String name) {
        webDriver.findElement(nameInput).sendKeys(name);
    }

    @Step("Проверка видимости элемента - Некорректный пароль")
    public boolean isIncorrectPasswordVisible() {
        return webDriver.findElement(incorrectPasswordLabel).isDisplayed();
    }

    @Step("Клик по элементу - Войти")
    public void clickLogin() {
        webDriver.findElement(enterButton).click();
    }
}
