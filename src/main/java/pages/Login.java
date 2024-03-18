package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends Page {

    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By emailInput = By.xpath("//label[text()='Email']/../input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/../input");
    private final By enterButton = By.xpath("//button[text() = 'Войти']");
    private final By forgotPasswordLink = By.xpath("//a[text() = 'Восстановить пароль']");
    private final By enterHeader = By.xpath("//h2[text() = 'Вход']");
    private final By constructorButton = By.xpath("//p[text() = 'Конструктор']");
    private final By logoButton = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");

    public Login(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента")
    public void waitForLoad() {
        waitForVisibility(registerLink);
    }

    @Step("Нажатие на ссылку - Зарегистрироваться")
    public void clickRegisterLink() {
        webDriver.findElement(registerLink).click();
    }

    @Step("Нажатие на кнопку - Войти")
    public void clickEnterButton() {
        webDriver.findElement(enterButton).click();
    }

    @Step("Нажатие на ссылку - Восстановить пароль")
    public void clickForgotPasswordLink() {
        webDriver.findElement(forgotPasswordLink).click();
    }

    @Step("Ввод email и пароля")
    public void enterLoginData(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }

    @Step("Проверка видимости заголовка")
    public boolean isEnterHeaderVisible() {
        return webDriver.findElement(enterHeader).isDisplayed();
    }

    @Step("Нажатие на кнопку - Конструктор")
    public void clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
    }

    @Step("Нажатие на логотип")
    public void clickLogoButton() {
        webDriver.findElement(logoButton).click();
    }
}
