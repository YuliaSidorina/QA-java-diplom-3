package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPassword extends Page {

    private final By forgotPasswordLabel = By.xpath("//h2[text() = 'Восстановление пароля']");
    private final By enterButton = By.xpath("//a[text() = 'Войти']");

    public ForgotPassword(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости")
    public void waitForLoad() {
        waitForVisibility(forgotPasswordLabel);
    }

    @Step("Нажатие кнопки Войти")
    public void clickEnterButton() {
        webDriver.findElement(enterButton).click();
    }
}
