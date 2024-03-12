package model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Objects;

public class LoginPage extends Header {

    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement emailInputField;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInputField;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement passwordRecoveryLink;

    public void clickRegisterLink() {
        registerLink.click();
    }

    public void setEmail(String email) {
        while (!Objects.equals(emailInputField.getValue(), email)) {
            emailInputField.shouldBe(Condition.editable).setValue(email);
        }
    }

    public void setPassword(String password) {
        passwordInputField.setValue(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    @Step("Логин пользователя")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickSignInButton();
    }

    @Step("Переход на страницу восстановления пароля")
    public void clickPasswordRecoveryLink() {
        passwordRecoveryLink.click();
    }
}
