package model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Header {

    @FindBy(how = How.XPATH, using = "//a[@class='AppHeader_header__link__3D_hX' and @href='/']")
    protected SelenideElement constructorLink;

    @FindBy(how = How.CSS, using = "div.AppHeader_header__logo__2D0X2")
    protected SelenideElement logoLink;

    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    protected SelenideElement personalAccountLink;

    @Step("Переход на страницу конструктора")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Переход на главную страницу")
    public void clickLogoLink() {
        logoLink.click();
    }

    @Step("Переход на страницу логина через ссылку 'Личный кабинет'")
    public void clickPersonalAccountLink() {
        personalAccountLink.click();
    }
}
