package model;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    @FindBy(how = How.XPATH, using = "//div[span[text()='Булки']]")
    private SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = "//div[span[text()='Соусы']]")
    private SelenideElement sauceTab;

    @FindBy(how = How.XPATH, using = "//div[span[text()='Начинки']]")
    private SelenideElement fillingTab;

    public void clickSauceTab() {
        sauceTab.click();
    }

    public void clickFillingTab() {
        fillingTab.click();
    }

    public boolean checkIsBunTabSelected() {
        return bunTab.getAttribute("class").contains("current");
    }

    public boolean checkIsSauceTabSelected() {
        return sauceTab.getAttribute("class").contains("current");
    }

    public boolean checkIsFillingTabSelected() {
        return fillingTab.getAttribute("class").contains("current");
    }
}
